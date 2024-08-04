package com.shamardn.authmodulecompose.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamardn.authmodulecompose.R
import com.shamardn.authmodulecompose.domain.model.RegisterInputValidationType
import com.shamardn.authmodulecompose.domain.repository.AuthRepository
import com.shamardn.authmodulecompose.domain.use_case.ValidateRegisterInputUseCase
import com.shamardn.authmodulecompose.presentation.state.RegisterState
import com.shamardn.authmodulecompose.util.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val stringProvider: StringProvider,
    private val authRepository: AuthRepository,
): ViewModel() {
    var registerState by mutableStateOf(RegisterState())
        private set

    fun onEmailInputChange(input: String) {
        registerState = registerState.copy(emailInput = input)
        checkInputValidation()
    }

    fun onPasswordInputChange(input: String) {
        registerState = registerState.copy(passwordInput = input)
        checkInputValidation()
    }

    fun onPasswordRepeatedInputChange(input: String) {
        registerState = registerState.copy(passwordRepeatedInput = input)
        checkInputValidation()
    }

    fun onTogglePasswordVisibility() {
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordShown)
    }

    fun onTogglePasswordRepeatedVisibility() {
        registerState =
            registerState.copy(isPasswordRepeatedShown = !registerState.isPasswordRepeatedShown)
    }

    fun onRegisterClick() {
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            registerState = try {
                val registerResult = authRepository.register(
                    email = registerState.emailInput,
                    password = registerState.passwordInput
                )
                registerState.copy(isSuccessfullyRegistered = registerResult)

            } catch (e: Exception){
                registerState.copy(errorMessageRegisterProcess = stringProvider.getString(R.string.register_failed))
            } finally {
                registerState = registerState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(
            email = registerState.emailInput,
            password = registerState.passwordInput,
            passwordRepeated = registerState.passwordRepeatedInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType) {
        registerState = when (type) {
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.please_fill_all_fields),
                    isInputVaIid = false,
                )
            }

            RegisterInputValidationType.NoEmail -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.please_enter_a_valid_email),
                    isInputVaIid = false,
                )
            }

            RegisterInputValidationType.PasswordNotMatch -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.passwords_do_not_match),
                    isInputVaIid = false,
                )

            }

            RegisterInputValidationType.PasswordUpperCaseMissing -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.password_must_contain_at_least_one_uppercase_letter),
                    isInputVaIid = false,
                )
            }

            RegisterInputValidationType.PasswordNumberMissing -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.password_must_contain_at_least_one_number),
                    isInputVaIid = false,
                )
            }

            RegisterInputValidationType.PasswordSpecialCharMissing -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.password_must_contain_at_least_one_special_character),
                    isInputVaIid = false,
                )
            }

            RegisterInputValidationType.PasswordTooShort -> {
                registerState.copy(
                    errorMessageInput = stringProvider.getString(R.string.password_must_be_at_least_8_characters_long),
                    isInputVaIid = false,
                )
            }

            RegisterInputValidationType.Valid -> {
                registerState.copy(
                    errorMessageInput = null,
                    isInputVaIid = true,
                )
            }
        }
    }
}