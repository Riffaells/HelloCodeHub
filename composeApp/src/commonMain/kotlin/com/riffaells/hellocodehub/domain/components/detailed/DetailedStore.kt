package com.riffaells.hellocodehub.domain.components.detailed

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.DIAware

interface DetailedStore : Store<DetailedStore.Intent, DetailedStore.State, Nothing> {

    sealed interface Intent {

    }

    @Serializable
    data class State(
        val gameId: Long = -1,
    )
}

internal class DetailedStoreFactory(
    private val storeFactory: StoreFactory,
    override val di: DI,
) : DIAware {


    fun create(): DetailedStore =
        object : DetailedStore,
            Store<DetailedStore.Intent, DetailedStore.State, Nothing> by storeFactory.create(
                name = "DetailedStore",
                initialState = DetailedStore.State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Msg {
        data class IsGameStarted(val gameId: Long) : Msg
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<DetailedStore.Intent, Unit, DetailedStore.State, Msg, Nothing>(
            Dispatchers.Main
        ) {

        override fun executeAction(action: Unit) {
        }


        override fun executeIntent(intent: DetailedStore.Intent): Unit =
            when (intent) {
                else -> TODO()
            }


    }

    private object ReducerImpl : Reducer<DetailedStore.State, Msg> {
        override fun DetailedStore.State.reduce(msg: Msg): DetailedStore.State =
            when (msg) {
                is Msg.IsGameStarted -> copy(gameId = msg.gameId)
            }
    }
}