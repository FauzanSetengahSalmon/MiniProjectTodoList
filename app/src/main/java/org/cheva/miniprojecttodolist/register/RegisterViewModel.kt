package org.cheva.miniprojecttodolist.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object UserData {
    var name: String = ""
    var email: String = ""
    var password: String = ""
}

class RegisterViewModel: ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnNameChanged -> changeName(event.name)
            is RegisterEvent.OnEmailChanged -> changeEmail(event.email)
            is RegisterEvent.OnPasswordChanged -> changePassword(event.password)
            is RegisterEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            RegisterEvent.OnRegisterClicked -> register()
            RegisterEvent.OnDismissDialog -> dismissDialog()
        }
    }

    private fun dismissDialog() {
        _state.update { it.copy(message = "", successRegister = false) }
    }

    private fun changeName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    private fun changeEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    private fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun changePasswordVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(passwordVisible = isVisible)
        }
    }

    private fun register() {
        if (state.value.name.isEmpty()){
            _state.update {
                it.copy(message = "Name is required")
            }
            return
        }

        if (state.value.email.isEmpty()){
            _state.update {
                it.copy(message = "Email is required")
            }
            return
        }

        if (state.value.password.isEmpty()){
            _state.update {
                it.copy(message = "Password is required")
            }
            return
        }

        UserData.name = state.value.name
        UserData.email = state.value.email
        UserData.password = state.value.password

        _state.update {
            it.copy(message = "Register Successful", successRegister = true)
        }

    }

}