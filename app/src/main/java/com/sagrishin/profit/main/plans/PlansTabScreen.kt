package com.sagrishin.profit.main.plans

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
fun PlansTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue.copy(alpha = 0.3F)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) { Text(text = "Plans", color = Color.Blue, fontWeight = FontWeight.Medium) }
}
