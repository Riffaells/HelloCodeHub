package com.riffaells.hellocodehub.domain.components.main


import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import org.kodein.di.DI
import org.kodein.di.DIAware

interface MainComponent {

    val state: StateFlow<MainStore.State>

    fun onEvent(event: MainStore.Intent)

    fun onLangDetailedClicked(language: ProgrammingLanguage)
}


class DefaultMainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = DefaultStoreFactory(),
    private val onDetailed: (ProgrammingLanguage) -> Unit = {},
    override val di: DI,
) : MainComponent, DIAware, ComponentContext by componentContext {


    private val store =
        instanceKeeper.getStore {
            MainStoreFactory(
                storeFactory = storeFactory,
                di = di
            ).create()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<MainStore.State> = store.stateFlow

    override fun onEvent(event: MainStore.Intent) {
        store.accept(event)
    }

    override fun onLangDetailedClicked(language: ProgrammingLanguage) {
        onDetailed(language)
    }
}