package com.sagrishin.profit.main.pages.diets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DietsTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta.copy(alpha = 0.3F)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) { Text(text = "Diets", color = Color.Magenta, fontWeight = FontWeight.Medium) }
}
