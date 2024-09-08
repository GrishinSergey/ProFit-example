package com.sagrishin.profit.navigation

import androidx.navigation.NavController
import com.sagrishin.conavigator.library.NavRoute
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.profit.main.MainRoute
import com.sagrishin.profit.main.MainScreenArgs

class AppNavigatorImpl constructor(
    navigator: Navigator,
    private val navController: NavController,
) : Navigator by navigator {

    fun navigateToMain(args: MainScreenArgs) {
        val toRoute = MainRoute(args).computeRoute()
        val startDestinationRoute = requireNotNull(navController.graph.startDestinationRoute) {
            "There should be a start destination for a graph"
        }

        navController.navigate(toRoute) {
            popUpTo(startDestinationRoute) { inclusive = true }
        }
    }

}
