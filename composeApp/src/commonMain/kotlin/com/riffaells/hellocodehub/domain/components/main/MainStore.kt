package com.riffaells.hellocodehub.domain.components.main

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.DIAware

interface MainStore : Store<MainStore.Intent, MainStore.State, Nothing> {

    sealed interface Intent {

    }

    @Serializable
    data class State(
        val gameId: Long = -1,
    )
}

internal class MainStoreFactory(
    private val storeFactory: StoreFactory,
    override val di: DI,
) : DIAware {


    fun create(): MainStore =
        object : MainStore,
            Store<MainStore.Intent, MainStore.State, Nothing> by storeFactory.create(
                name = "MainStore",
                initialState = MainStore.State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Msg {
        data class IsGameStarted(val gameId: Long) : Msg
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<MainStore.Intent, Unit, MainStore.State, Msg, Nothing>(
            Dispatchers.Main
        ) {

        override fun executeAction(action: Unit) {
        }


        override fun executeIntent(intent: MainStore.Intent): Unit =
            when (intent) {
                else -> TODO()
            }


    }

    private object ReducerImpl : Reducer<MainStore.State, Msg> {
        override fun MainStore.State.reduce(msg: Msg): MainStore.State =
            when (msg) {
                is Msg.IsGameStarted -> copy(gameId = msg.gameId)
            }
    }
}