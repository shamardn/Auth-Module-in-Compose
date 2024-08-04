package com.shamardn.authmodulecompose.domain.use_case

import com.shamardn.authmodulecompose.domain.model.RegisterInputValidationType
import com.shamardn.authmodulecompose.util.containsNumber
import com.shamardn.authmodulecompose.util.containsSpecialChar
import com.shamardn.authmodulecompose.util.hasUpperCase
import com.shamardn.authmodulecompose.util.isValidEmail

class ValidateRegisterInputUseCase {
    operator fun invoke(
        email: String,
        password: String,
        passwordRepeated: String,
    ): RegisterInputValidationType {
        if (email.isBlank() || password.isBlank() || passwordRepeated.isBlank()) {
            return RegisterInputValidationType.EmptyField
        }
        if (!email.isValidEmail()) {
            return RegisterInputValidationType.NoEmail
        }
        if (password != passwordRepeated) {
            return RegisterInputValidationType.PasswordNotMatch
        }
        if(!password.hasUpperCase()){
         return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if (!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if(!password.containsSpecialChar()){
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        if (password.length < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }
        return RegisterInputValidationType.Valid
    }
}