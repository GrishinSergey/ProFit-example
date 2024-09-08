package com.sagrishin.profit.infrastructure

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.sagrishin.conavigator.library.NavRoute
import com.sagrishin.conavigator.library.NavigatorImpl
import com.sagrishin.profit.login.LoginRoute
import com.sagrishin.profit.navigation.AppNavigatorImpl
import com.sagrishin.profit.navigation.MainNavGraphImpl
import com.sagrishin.profit.uikit.ExtendedNavHost
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))

        setContent {
            val navHostController = rememberNavController()
            ExtendedNavHost(
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController,
                navigator = AppNavigatorImpl(
                    navigator = NavigatorImpl(navHostController),
                    navController = navHostController,
                ),
                graph = MainNavGraphImpl(viewModel.obtainStartDestination())
            )
        }
    }
}


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun obtainStartDestination(): NavRoute {
        return LoginRoute()
    }

}
