package com.sagrishin.profit.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import com.sagrishin.profit.uikit.BottomNavItem
import kotlinx.parcelize.Parcelize

@Parcelize
data object DashboardBottomNavItem : BottomNavItem {
    @Composable
    override fun getIconWith(isSelected: Boolean) = Icons.Default.Home

    @Composable
    override fun getText() = "Dashboard"
}


@Parcelize
data object DietsBottomNavItem : BottomNavItem {
    @Composable
    override fun getIconWith(isSelected: Boolean) = Icons.Default.Face

    @Composable
    override fun getText() = "Diets"
}


@Parcelize
data object PlansBottomNavItem : BottomNavItem {
    @Composable
    override fun getIconWith(isSelected: Boolean) = Icons.Default.ShoppingCart

    @Composable
    override fun getText() = "Plans"
}
