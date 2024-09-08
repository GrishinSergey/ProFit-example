package com.sagrishin.profit.navigation

import com.sagrishin.conavigator.library.NavGraph
import com.sagrishin.conavigator.library.NavRouteProvider

interface MainNavGraph : NavGraph {
    companion object : NavRouteProvider {
        override val baseRoute: String = "/"
    }
}
