package com.shamardn.authmodulecompose.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.authmodulecompose.R
import com.shamardn.authmodulecompose.domain.model.LoginInputValidationType
import com.shamardn.authmodulecompose.domain.repository.AuthRepository
import com.shamardn.authmodulecompose.domain.use_case.ValidateLoginInputUseCase
import com.shamardn.authmodulecompose.presentation.state.LoginState
import com.shamardn.authmodulecompose.util.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val stringProvider: StringProvider,
    private val authRepository: AuthRepository,
): ViewModel(){
    var loginState by mutableStateOf(LoginState())
        private set

    fun onEmailInputChange(input: String) {
        loginState = loginState.copy(emailInput = input)
        checkInputValidation()
    }

    fun onPasswordInputChange(input: String) {
        loginState = loginState.copy(passwordInput = input)
        checkInputValidation()
    }

    fun onToggleVisualTransformation() {
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    fun onLoginClick() {
        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            loginState = try {
                val loginResult = authRepository.login(
                    email = loginState.emailInput,
                    password = loginState.passwordInput
                )
                loginState.copy(isLoginSuccess = loginResult)

            } catch (e: Exception){
                loginState.copy(errorMessageLoginProcess = stringProvider.getString(R.string.login_failed))
            } finally {
                loginState = loginState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            email = loginState.emailInput,
            password = loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        loginState = when (type) {
            LoginInputValidationType.EmptyField -> {
                loginState.copy(
                    errorMessageInput = stringProvider.getString(R.string.please_fill_all_fields),
                    isInputVaIid = false,
                )
            }

            LoginInputValidationType.NoEmail -> {
                loginState.copy(
                    errorMessageInput = stringProvider.getString(R.string.please_enter_a_valid_email),
                    isInputVaIid = false,
                )
            }

            LoginInputValidationType.Valid -> {
                loginState.copy(
                    errorMessageInput = null,
                    isInputVaIid = true,
                )
            }
        }
    }
}