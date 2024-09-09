package com.sagrishin.profit.navigation

import androidx.navigation.NavController
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.profit.exercises.SelectExercisesRoute
import com.sagrishin.profit.main.MainRoute
import com.sagrishin.profit.main.MainScreenArgs
import com.sagrishin.profit.main.contracts.MainNavigator

class AppNavigatorImpl constructor(
    navigator: Navigator,
    private val navController: NavController,
) : Navigator by navigator, MainNavigator {

    fun navigateToMain() {
        val toRoute = MainRoute(MainScreenArgs()).computeRoute()
        val startDestinationRoute = requireNotNull(navController.graph.startDestinationRoute) {
            "There should be a start destination for a graph"
        }

        navController.navigate(toRoute) {
            popUpTo(startDestinationRoute) { inclusive = true }
        }
    }

    override fun openExercisesSelector() {
        navigateTo(SelectExercisesRoute())
    }

}
