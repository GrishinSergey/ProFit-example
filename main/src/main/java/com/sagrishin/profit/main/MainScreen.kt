package com.sagrishin.profit.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sagrishin.conavigator.generator.annotation.Destination
import com.sagrishin.conavigator.library.NavArguments
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.profit.main.navigation.MainNavGraph
import com.sagrishin.profit.main.pages.dashboard.DashboardTabScreen
import com.sagrishin.profit.main.pages.diets.DietsTabScreen
import com.sagrishin.profit.main.pages.plans.PlansTabScreen
import com.sagrishin.profit.main.uikit.BottomNavItem
import com.sagrishin.profit.main.uikit.UiKitBottomNavigation
import com.sagrishin.profit.main.uikit.rememberBottomNavigationState
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainScreenArgs constructor(
    val tabToOpen: BottomNavItem? = null
) : NavArguments

@Composable
@Destination(installIn = MainNavGraph::class)
fun MainScreen(args: MainScreenArgs, navigator: Navigator) {
    val bottomNavigationState = rememberBottomNavigationState(
        initialItem = args.tabToOpen ?: DashboardBottomNavItem,
        allItems = buildList {
            add(DashboardBottomNavItem)
            add(DietsBottomNavItem)
            add(PlansBottomNavItem)
        },
    )

    Scaffold(
        contentWindowInsets = WindowInsets.Companion.navigationBars,
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        scaffoldState = rememberScaffoldState(),
        content = { paddings ->
            Column(modifier = Modifier.padding(paddings).fillMaxSize()) {
                when (bottomNavigationState.selectedItem) {
                    is DashboardBottomNavItem -> DashboardTabScreen(navigator)
                    is DietsBottomNavItem -> DietsTabScreen()
                    is PlansBottomNavItem -> PlansTabScreen()
                }
            }
        },
        bottomBar = {
            UiKitBottomNavigation(
                modifier = Modifier.navigationBarsPadding(),
                state = bottomNavigationState,
            )
        }
    )
}
