package com.sagrishin.profit.login

import com.sagrishin.conavigator.library.NavGraph
import com.sagrishin.conavigator.library.NavRouteProvider

interface LoginNavGraph : NavGraph {
    companion object : NavRouteProvider {
        override val baseRoute: String = "/login"
    }
}
