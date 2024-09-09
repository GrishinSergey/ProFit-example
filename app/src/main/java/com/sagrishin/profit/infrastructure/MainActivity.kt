package com.sagrishin.profit.infrastructure

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.sagrishin.conavigator.library.NavRoute
import com.sagrishin.conavigator.library.NavigatorImpl
import com.sagrishin.profit.exercises.SelectExercisesScreenNavDestination
import com.sagrishin.profit.infrastructure.MainActivityStartArgs.StartFromPushNotification
import com.sagrishin.profit.login.EnterIdentifierRoute
import com.sagrishin.profit.login.LoginNavGraphImpl
import com.sagrishin.profit.login.LoginNavRoute
import com.sagrishin.profit.main.MainRoute
import com.sagrishin.profit.main.MainScreenArgs
import com.sagrishin.profit.main.navigation.MainNavGraphImpl
import com.sagrishin.profit.main.uikit.BottomNavItem
import com.sagrishin.profit.navigation.AppNavigatorImpl
import com.sagrishin.profit.uikit.ExtendedNavHost
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var intentsHandleDelegate: MainActivityIntentsHandleDelegate
        internal set

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))

        setContent {
            val startArgs by viewModel.startArgs
                .filterIsInstance<MainActivityStartArgs>()
                .collectAsState(initial = null)
                .run { remember { this } }

            val navHostController = rememberNavController()
            ExtendedNavHost(
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController,
                navigator = AppNavigatorImpl(
                    navigator = NavigatorImpl(navHostController),
                    navController = navHostController,
                ),
                startDestinationRoute = viewModel.obtainStartDestination(),
                entries = buildSet {
                    add(LoginNavGraphImpl(EnterIdentifierRoute()))

                    val tabToOpen = startArgs?.fold<StartFromPushNotification>()?.startDestination
                    add(MainNavGraphImpl(MainRoute(MainScreenArgs(tabToOpen))))

                    add(SelectExercisesScreenNavDestination)
                }
            )
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val pushArgs = intentsHandleDelegate.handleOnPushClick(intent) ?: return
        viewModel.handleStartArgs(pushArgs)
    }

}


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val startArgs = MutableStateFlow<MainActivityStartArgs?>(null)

    fun obtainStartDestination(): NavRoute {
        /// this parameter should depends on some DB's or backend data, where
        /// you would store your users sessions. F.Ex.: if you have authorized
        /// user, then here should be MainRoute, otherwise, here should be
        /// Authorization Flow.
        return LoginNavRoute()
    }

    fun handleStartArgs(args: MainActivityStartArgs) {
        viewModelScope.launch { startArgs.emit(args) }
    }
}


sealed class MainActivityStartArgs : Parcelable {
    @Parcelize
    data class StartFromPushNotification constructor(
        val startDestination: BottomNavItem
    ) : MainActivityStartArgs()


    inline fun <reified T : MainActivityStartArgs> fold(): T? {
        return if (this is T) this else null
    }
}
