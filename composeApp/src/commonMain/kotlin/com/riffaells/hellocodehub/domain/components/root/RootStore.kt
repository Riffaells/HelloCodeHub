package com.riffaells.hellocodehub.domain.components.root

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.riffaells.hellocodehub.domain.components.root.RootStore.Intent
import com.riffaells.hellocodehub.domain.model.ProgrammingLanguage
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.DIAware


interface RootStore : Store<Intent, RootStore.State, Nothing> {

    sealed interface Intent {

        data class UpdateLang(val languages: List<ProgrammingLanguage>) : Intent
        data class CurrentLang(val language: ProgrammingLanguage?) : Intent

    }

    @Serializable
    data class State(
        val languages: List<ProgrammingLanguage> = emptyList(),
        val currentLanguage: ProgrammingLanguage? = null,
    )
}

internal class RootStoreFactory(
    private val storeFactory: StoreFactory,
    override val di: DI,
) : DIAware {

//    val settings by instance<MultiplatformSettings>()


    fun create(): RootStore =
        object : RootStore,
            Store<Intent, RootStore.State, Nothing> by storeFactory.create(
                name = "RootStore",
                initialState = RootStore.State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Msg {

        data class UpdateLang(val languages: List<ProgrammingLanguage>) : Msg
        data class CurrentLang(val language: ProgrammingLanguage?) : Msg


    }

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Unit, RootStore.State, Msg, Nothing>(
            Dispatchers.Main
        ) {


        override fun executeAction(action: Unit) {

        }

        override fun executeIntent(intent: Intent): Unit =
            when (intent) {
                is Intent.UpdateLang -> lang(intent.languages)
                is Intent.CurrentLang -> dispatch(Msg.CurrentLang(intent.language))
            }

        private fun lang(languages: List<ProgrammingLanguage>) {
            dispatch(Msg.UpdateLang(languages))
        }


    }


    private object ReducerImpl : Reducer<RootStore.State, Msg> {
        override fun RootStore.State.reduce(msg: Msg): RootStore.State =
            when (msg) {
                is Msg.UpdateLang -> copy(languages = msg.languages)
                is Msg.CurrentLang -> copy(currentLanguage = msg.language)
            }
    }
}