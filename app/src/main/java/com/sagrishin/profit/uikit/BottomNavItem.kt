package com.sagrishin.profit.uikit

import android.os.Parcelable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

interface BottomNavItem : Parcelable {

    @Composable
    fun getIconWith(isSelected: Boolean): ImageVector

    @Composable
    fun getText(): String

    fun getIcon(isSelected: Boolean): @Composable (modifier: Modifier) -> Unit {
        return { modifier -> Icon(getIconWith(isSelected), "", modifier) }
    }

    fun getText(isSelected: Boolean): @Composable (modifier: Modifier) -> Unit {
        val fontWeight = {
            when (isSelected) {
                true -> FontWeight.Medium
                false -> FontWeight.Normal
            }
        }
        return { modifier -> Text(text = getText(), fontWeight = fontWeight(), modifier = modifier) }
    }

}
