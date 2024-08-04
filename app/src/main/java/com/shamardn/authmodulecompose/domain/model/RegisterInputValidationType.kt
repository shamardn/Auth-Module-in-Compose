package com.shamardn.authmodulecompose.domain.model

enum class RegisterInputValidationType {
    EmptyField,
    NoEmail,
    PasswordNotMatch,
    PasswordUpperCaseMissing,
    PasswordNumberMissing,
    PasswordSpecialCharMissing,
    PasswordTooShort,
    Valid,
}