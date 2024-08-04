package com.shamardn.authmodulecompose.domain.use_case

import com.shamardn.authmodulecompose.domain.model.LoginInputValidationType
import com.shamardn.authmodulecompose.util.isValidEmail

class ValidateLoginInputUseCase {
    operator fun invoke(email: String, password: String): LoginInputValidationType{
     if (email.isBlank() || password.isBlank()){
         return LoginInputValidationType.EmptyField
     }
        if (email.isValidEmail()){
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }
}