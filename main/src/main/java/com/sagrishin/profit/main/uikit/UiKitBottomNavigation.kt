package com.sagrishin.profit.main.uikit

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UiKitBottomNavigation(
    modifier: Modifier = Modifier,
    state: BottomNavigationState,
) {
    BottomNavigation(
        elevation = 0.dp,
        modifier = modifier,
        backgroundColor = Color.White,
        contentColor = Color.Black,
    ) {
        state.allItems.forEach { item ->
            val isSelected = state.isItemSelected(item)
            BottomNavigationItem(
                modifier = Modifier,
                selected = isSelected,
                onClick = { state.pushSelection(item) },
                icon = { item.getIcon(isSelected).invoke(Modifier) },
                label = { item.getText(isSelected).invoke(Modifier) },
            )
        }
    }
}

@Composable
fun rememberBottomNavigationState(initialItem: BottomNavItem, allItems: List<BottomNavItem>) = remember {
    object : BottomNavigationState {
        override var selectedItem: BottomNavItem by mutableStateOf(initialItem)
            private set
        override val allItems: List<BottomNavItem> = allItems

        override fun isItemSelected(item: BottomNavItem): Boolean {
            return selectedItem == item
        }

        override fun pushSelection(newSelected: BottomNavItem) {
            selectedItem = newSelected
        }
    }
}


interface BottomNavigationState {
    val selectedItem: BottomNavItem
    val allItems: List<BottomNavItem>

    fun isItemSelected(item: BottomNavItem): Boolean
    fun pushSelection(newSelected: BottomNavItem)
}
