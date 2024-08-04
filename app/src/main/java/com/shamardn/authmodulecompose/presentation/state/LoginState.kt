package com.shamardn.authmodulecompose.presentation.state

data class LoginState(
    val emailInput : String = "",
    val passwordInput : String = "",
    val isInputVaIid : Boolean = false,
    val isPasswordShown : Boolean = false,
    val errorMessageInput : String? = null,
    val isLoading : Boolean = false,
    val isLoginSuccess : Boolean = false,
    val errorMessageLoginProcess : String? = null,
)