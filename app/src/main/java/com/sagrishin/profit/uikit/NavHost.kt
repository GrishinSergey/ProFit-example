package com.sagrishin.profit.uikit

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sagrishin.conavigator.library.NavEntry
import com.sagrishin.conavigator.library.NavGraph
import com.sagrishin.conavigator.library.NavRoute
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.conavigator.library.entry

typealias NavTransitionScope = AnimatedContentTransitionScope<NavBackStackEntry>
typealias EnterAnimation = NavTransitionScope.() -> EnterTransition
typealias ExitAnimation = NavTransitionScope.() -> ExitTransition

private const val durationMillis = 450

@Composable
fun ExtendedNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigator: Navigator,
    graph: NavGraph,
    enterTransition: EnterAnimation = { slideIntoContainer(Left, tween(durationMillis)) },
    exitTransition: ExitAnimation = { slideOutOfContainer(Left, tween(durationMillis)) },
    popEnterTransition: EnterAnimation = { slideIntoContainer(Right, tween(durationMillis)) },
    popExitTransition: ExitAnimation = { slideOutOfContainer(Right, tween(durationMillis)) },
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = graph.baseRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = { entry(navigator, graph) },
    )
}

@Composable
fun ExtendedNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigator: Navigator,
    startDestinationRoute: NavRoute,
    entries: Set<NavEntry>,
    enterTransition: EnterAnimation = { slideIntoContainer(Left, tween(durationMillis)) },
    exitTransition: ExitAnimation = { slideOutOfContainer(Left, tween(durationMillis)) },
    popEnterTransition: EnterAnimation = { slideIntoContainer(Right, tween(durationMillis)) },
    popExitTransition: ExitAnimation = { slideOutOfContainer(Right, tween(durationMillis)) },
) {
    ExtendedNavHost(
        modifier = modifier,
        navHostController = navHostController,
        navigator = navigator,
        graph = rememberRootNavGraph(startDestinationRoute, entries),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    )
}

@Composable
private fun rememberRootNavGraph(startDestinationRoute: NavRoute, entries: Set<NavEntry>) = remember {
    object : NavGraph {
        override val baseRoute: String
            get() = "/"
        override val entries: Set<NavEntry>
            get() = entries
        override val startRoute: NavRoute
            get() = startDestinationRoute
    }
}
