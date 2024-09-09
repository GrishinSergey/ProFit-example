package com.sagrishin.profit.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sagrishin.conavigator.generator.annotation.Destination
import com.sagrishin.conavigator.library.Navigator
import com.sagrishin.profit.login.EnterIdentifierViewModel.IdentifierValidationState.NewUser
import com.sagrishin.profit.login.EnterIdentifierViewModel.IdentifierValidationState.RegisteredUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
@Destination(installIn = LoginNavGraph::class)
fun EnterIdentifierScreen(navigator: Navigator) {
    val viewModel = viewModel<EnterIdentifierViewModel>()
    var login by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.primary,
            ),
            value = login,
            label = { Text("Enter login") },
            onValueChange = { login = it },
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            content = { Text(text = "Continue") },
            onClick = { viewModel.validateIdentifier(login) }
        )
    }

    LaunchedEffect(viewModel) {
        viewModel.identifierValidationState.collectLatest {
            val route = when (it) {
                NewUser -> IdentifierConfirmationRoute(ConfirmationScreenArgs(login))
                RegisteredUser -> EnterPasswordRoute(EnterPasswordScreenArgs(login))
            }

            navigator.navigateTo(route)
        }
    }
}


@HiltViewModel
class EnterIdentifierViewModel @Inject constructor() : ViewModel() {

    sealed interface IdentifierValidationState {
        data object RegisteredUser : IdentifierValidationState

        data object NewUser : IdentifierValidationState
    }


    val identifierValidationState: Flow<IdentifierValidationState>
        get() = _identifierValidationState.filterNotNull()
    private val _identifierValidationState = MutableStateFlow<IdentifierValidationState?>(null)


    fun validateIdentifier(identifier: String) {
        viewModelScope.launch {
            /// let's say that this is new user
            _identifierValidationState.emit(NewUser)
        }
    }
}
