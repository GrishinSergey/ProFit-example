package com.sagrishin.profit.main.pages.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.profit.main.contracts.MainNavigator

@Composable
fun DashboardTabScreen(navigator: Navigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.3F)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Dashboard", color = Color.Red, fontWeight = FontWeight.Medium)

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red.copy(alpha = 0.5F)),
            onClick = {
                require(navigator is MainNavigator)
                navigator.openExercisesSelector()
            },
            content = {
                Text(
                    text = "Select Exercises",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
            },
        )
    }
}
