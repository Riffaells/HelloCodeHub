package com.riffaells.hellocodehub.domain.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.riffaells.hellocodehub.domain.components.detailed.DefaultDetailedComponent
import com.riffaells.hellocodehub.domain.components.main.DefaultMainComponent
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.DIAware

interface RootComponent {
    val mainComponent: DefaultMainComponent
    val detailedSlot: Value<ChildSlot<Config, DefaultDetailedComponent>>
    val state: StateFlow<RootStore.State>

    fun onEvent(event: RootStore.Intent)
    fun showDetailed(language: ProgrammingLanguage)
    fun closeDetailed()
}

@Serializable
sealed interface Config {
    @Serializable
    data class Detailed(val language: ProgrammingLanguage) : Config
}

class DefaultRootComponent(
    componentContext: ComponentContext,
    storeFactory: DefaultStoreFactory = DefaultStoreFactory(),
    override val di: DI
) : RootComponent, DIAware, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            RootStoreFactory(
                storeFactory = storeFactory,
                di = di
            ).create()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<RootStore.State> = store.stateFlow

    private val detailedNavigation = SlotNavigation<Config>()

    override val mainComponent =
        DefaultMainComponent(
            componentContext = this,
            onDetailed = ::showDetailed,
            di = di
        )

    override val detailedSlot: Value<ChildSlot<Config, DefaultDetailedComponent>> =
        childSlot(
            source = detailedNavigation,
            key = "DetailedSlot",
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = { config, childComponentContext ->
                when (config) {
                    is Config.Detailed -> DefaultDetailedComponent(
                        componentContext = childComponentContext,
                        onDetailed = ::showDetailed,
                        di = di,
                        lang = config.language,
                        onBack = ::closeDetailed
                    )
                }
            }
        )

    override fun onEvent(event: RootStore.Intent) {
        store.accept(event)
    }

    override fun showDetailed(language: ProgrammingLanguage) {
        detailedNavigation.activate(Config.Detailed(language))
    }

    override fun closeDetailed() {
        detailedNavigation.dismiss()
    }
}
