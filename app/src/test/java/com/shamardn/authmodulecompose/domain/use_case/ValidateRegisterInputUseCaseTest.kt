package com.shamardn.authmodulecompose.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.shamardn.authmodulecompose.domain.model.RegisterInputValidationType
import org.junit.Test


class ValidateRegisterInputUseCaseTest{
    private val validateRegisterInputUseCase = ValidateRegisterInputUseCase()

    @Test
    fun `test empty email field return validation type empty field`() {
        val result = validateRegisterInputUseCase(email = "" , password = "ValidPassword_1" , passwordRepeated = "ValidPassword_1")
        assertThat(result).isEqualTo(RegisterInputValidationType.EmptyField)
    }

    @Test
    fun `test empty password field return validation type empty field`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "",
            passwordRepeated = "ValidPassword_1"
            )
        assertThat(result).isEqualTo(RegisterInputValidationType.EmptyField)
    }

    @Test
    fun `test empty password repeated field return validation type empty field`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "ValidPassword_1",
            passwordRepeated = ""
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.EmptyField)
    }

    @Test
    fun `test no email return validation type no email`() {
        val result = validateRegisterInputUseCase(email = "onemail.com" , password = "ValidPassword_1" , passwordRepeated = "ValidPassword_1")
        assertThat(result).isEqualTo(RegisterInputValidationType.NoEmail)
    }

    @Test
    fun validateRegisterInputUseCaseTest() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "ValidPassword_1",
            passwordRepeated = "ValidPassword_1"
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.Valid)
    }

    @Test
    fun `test password not repeated correctly return correct validation type PasswordNotMatch`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "ValidPassword_1",
            passwordRepeated = "ValidPassword_2"
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordNotMatch)
    }

    @Test
    fun `test password without upper case letter returns correct validation type PasswordUpperCaseMissing`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "validpassword_1",
            passwordRepeated = "validpassword_1"
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordUpperCaseMissing)
    }

    @Test
    fun `test password without number returns correct validation type PasswordNumberMissing`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "ValidPassword",
            passwordRepeated = "ValidPassword"
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordNumberMissing)
    }

    @Test
    fun `test password without special char returns correct validation type PasswordSpecialCharMissing`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "ValidPassword1",
            passwordRepeated = "ValidPassword1"
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordSpecialCharMissing)
    }

    @Test
    fun `test password too short returns correct validation type PasswordTooShort`() {
        val result = validateRegisterInputUseCase(
            email = "one@mail.com",
            password = "Valid_1",
            passwordRepeated = "Valid_1"
        )
        assertThat(result).isEqualTo(RegisterInputValidationType.PasswordTooShort)
    }
}