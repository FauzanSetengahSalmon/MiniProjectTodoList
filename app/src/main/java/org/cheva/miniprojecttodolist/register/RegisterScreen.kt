package org.cheva.miniprojecttodolist.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.navigation.LoginScreen
import org.cheva.miniprojecttodolist.ui.components.OutlinedTextField
import org.cheva.miniprojecttodolist.ui.components.ResultDialog
import org.cheva.miniprojecttodolist.ui.components.SecureTextField
import org.cheva.miniprojecttodolist.ui.theme.MiniProjectTodoListTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(state.successRegister) {
        if (state.successRegister){
            delay(1000)
            onNavigate(LoginScreen.route)
        }
    }
    Scaffold {
        if (state.message.isNotBlank()) {
            ResultDialog(
                isSuccess = state.successRegister,
                message = state.message,
                onDismiss = { onEvent(RegisterEvent.OnDismissDialog) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.register_headline),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.register_hint),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                label = stringResource(R.string.name_label),
                value = state.name,
                onValueChange = { onEvent(RegisterEvent.OnNameChanged(it)) },
                keyboardType = KeyboardType.Text,
                hint = stringResource(R.string.name_hint)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                label = stringResource(R.string.email_label),
                value = state.email,
                onValueChange = { onEvent(RegisterEvent.OnEmailChanged(it)) },
                keyboardType = KeyboardType.Email,
                hint = stringResource(R.string.email_hint)
            )
            Spacer(modifier = Modifier.height(8.dp))
            SecureTextField(
                label = stringResource(R.string.password_label),
                value = state.password,
                onValueChange = { onEvent(RegisterEvent.OnPasswordChanged(it)) },
                keyboardType = KeyboardType.Password,
                isVisible = state.passwordVisible,
                onVisibilityChange = { onEvent(RegisterEvent.OnPasswordVisibilityChanged(it)) },
                hint = stringResource(R.string.password_hint)
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(RegisterEvent.OnRegisterClicked) }
            ) {
                Text(stringResource(R.string.register_headline))
            }
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onNavigate(LoginScreen.route) }
            ) {
                Text(stringResource(R.string.to_login))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegisterScreenPrev() {
    MiniProjectTodoListTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {},
            onNavigate = {}
        )
    }
}