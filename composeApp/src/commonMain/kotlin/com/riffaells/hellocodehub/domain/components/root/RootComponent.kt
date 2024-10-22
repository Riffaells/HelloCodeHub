package com.riffaells.hellocodehub.domain.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
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
    //    val settings: MultiplatformSettings
//
    val childStack: Value<ChildStack<*, Child>>

    val state: StateFlow<RootStore.State>


    fun onEvent(event: RootStore.Intent)

    fun onMainClicked()
    fun onLangDetailedClicked(language: ProgrammingLanguage)

    sealed class Child {
        class MainChild(val component: DefaultMainComponent) : Child()
        class DetailedChild(val component: DefaultDetailedComponent) : Child()

    }


}


@OptIn(ExperimentalDecomposeApi::class)
class DefaultRootComponent(
    componentContext: ComponentContext,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
    storeFactory: DefaultStoreFactory = DefaultStoreFactory(),
    override val di: DI
) : RootComponent, DIAware, ComponentContext by componentContext {

//    override val settings by instance<MultiplatformSettings>()

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = {
            getInitialStack(
                webHistoryPaths = webHistoryController?.historyPaths,
                deepLink = deepLink
            )
        },
        handleBackButton = true,
        childFactory = ::child,
    )
    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack


    private val store =
        instanceKeeper.getStore {
            RootStoreFactory(
                storeFactory = storeFactory,
                di = di
            ).create()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<RootStore.State> = store.stateFlow


    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            getPath = ::getPathForConfig,
            serializer = Config.serializer(),
            getConfiguration = ::getConfigForPath,
        )
    }

    override fun onEvent(event: RootStore.Intent) {
        store.accept(event)
    }

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            Config.Main -> RootComponent.Child.MainChild(mainComponent(componentContext))
            is Config.LangDetailed -> RootComponent.Child.DetailedChild(detailedComponent(componentContext, config))
        }

    private fun mainComponent(componentContext: ComponentContext): DefaultMainComponent =
        DefaultMainComponent(
            componentContext = componentContext,
            onDetailed = ::onLangDetailedClicked,
            di = di,
        )

    private fun detailedComponent(componentContext: ComponentContext, config: Config.LangDetailed): DefaultDetailedComponent =
        DefaultDetailedComponent(
            componentContext = componentContext,
            onDetailed = {},
            di = di,
            lang = config.language,

        )


    override fun onMainClicked() {
        navigation.bringToFront(Config.Main)
    }


    override fun onLangDetailedClicked(language: ProgrammingLanguage) {
        navigation.bringToFront(Config.LangDetailed(language))
    }


    private companion object {
        private const val WEB_PATH_LANG_LIST = "list"
        private const val WEB_PATH_LANG_DETAILED = "lang"



    }

    private fun getInitialStack(webHistoryPaths: List<String>?, deepLink: DeepLink): List<Config> =
        webHistoryPaths
            ?.takeUnless(List<*>::isEmpty)
            ?.map(::getConfigForPath)
            ?: getInitialStack(deepLink)

    private fun getInitialStack(deepLink: DeepLink): List<Config> =
        when (deepLink) {
            is DeepLink.None -> listOf(Config.Main)
            is DeepLink.Web -> listOf(getConfigForPath(deepLink.path))
        }

    private fun getPathForConfig(config: Config): String =
        when (config) {
            Config.Main -> "/$WEB_PATH_LANG_LIST"
            is Config.LangDetailed -> "/$WEB_PATH_LANG_DETAILED"
        }


    private fun getConfigForPath(path: String): Config =
        when (path.removePrefix("/")) {
            WEB_PATH_LANG_DETAILED -> Config.LangDetailed(state.value.languages.first { it.name == path })
            else -> Config.Main
        }


    @Serializable
    private sealed interface Config {


        @Serializable
        data object Main : Config


        @Serializable
        data class LangDetailed(val language: ProgrammingLanguage) : Config

    }

    sealed interface DeepLink {
        data object None : DeepLink
        class Web(val path: String) : DeepLink
    }
}