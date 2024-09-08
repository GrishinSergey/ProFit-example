package com.sagrishin.profit.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sagrishin.conavigator.generator.annotation.Destination
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.profit.navigation.MainNavGraph

@Composable
@Destination(installIn = MainNavGraph::class)
fun EnterIdentifierScreen(navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var login by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.padding(bottom = 24.dp).padding(horizontal = 16.dp).fillMaxWidth(),
            value = login,
            placeholder = { Text("Enter login", color = Color.Gray.copy(0.7F)) },
            onValueChange = { login = it },
        )

        Button(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            content = { Text(text = "Enter password") },
            onClick = {
                val args = EnterPasswordScreenArgs(login)
                navigator.navigateTo(EnterPasswordRoute(args))
            }
        )
    }
}
