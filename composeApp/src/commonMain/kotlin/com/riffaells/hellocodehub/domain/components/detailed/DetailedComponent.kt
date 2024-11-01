package com.riffaells.hellocodehub.domain.components.detailed


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

interface DetailedComponent {

    val lang: ProgrammingLanguage

    val state: StateFlow<DetailedStore.State>

    fun onEvent(event: DetailedStore.Intent)

    fun onLangDetailedClicked(lang: ProgrammingLanguage)

    fun onBackClicked()
}


class DefaultDetailedComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = DefaultStoreFactory(),
    private val onDetailed: (ProgrammingLanguage) -> Unit = {},
    private val onBack: () -> Unit,
    override val lang: ProgrammingLanguage,
    override val di: DI,
) : DetailedComponent, DIAware, ComponentContext by componentContext {


    private val store =
        instanceKeeper.getStore {
            DetailedStoreFactory(
                storeFactory = storeFactory,
                di = di
            ).create()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<DetailedStore.State> = store.stateFlow

    override fun onEvent(event: DetailedStore.Intent) {
        store.accept(event)
    }

    override fun onLangDetailedClicked(lang: ProgrammingLanguage) {
        onDetailed(lang)
    }

    override fun onBackClicked() {
        onBack()
    }
}