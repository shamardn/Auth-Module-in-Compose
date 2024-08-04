package com.shamardn.authmodulecompose.presentation.state


data class RegisterState(
    val emailInput : String = "",
    val passwordInput : String = "",
    val passwordRepeatedInput : String = "",
    val isInputVaIid : Boolean = false,
    val isPasswordShown : Boolean = false,
    val isPasswordRepeatedShown:Boolean = false,
    val errorMessageInput : String? = null,
    val isLoading : Boolean = false,
    val isSuccessfullyRegistered : Boolean = false,
    val errorMessageRegisterProcess : String? = null,
)
