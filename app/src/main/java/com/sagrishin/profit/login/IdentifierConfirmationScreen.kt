package com.sagrishin.profit.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import com.sagrishin.conavigator.library.NavArguments
import com.sagrishin.conavigator.library.Navigator
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationScreenArgs constructor(
    val login: String,
) : NavArguments


@Composable
@Destination(installIn = LoginNavGraph::class)
fun IdentifierConfirmationScreen(args: ConfirmationScreenArgs, navigator: Navigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .safeGesturesPadding()
                .fillMaxWidth(),
            text = "You are a new user. Please, confirm your identifier '${args.login}' with OTP:"
        )

        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = password,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            ),
            label = { Text("Enter pin") },
            onValueChange = { password = it },
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            content = { Text(text = "Create a Password") },
            onClick = { navigator.navigateTo(EnterPasswordRoute(EnterPasswordScreenArgs(args.login))) }
        )
    }
}
