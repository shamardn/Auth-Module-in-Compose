package com.shamardn.authmodulecompose.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.shamardn.authmodulecompose.domain.model.LoginInputValidationType
import org.junit.Test

class ValidateLoginInputUseCaseTest {
    private val validationLoginInputUseCase =  ValidateLoginInputUseCase()

    @Test
    fun `test empty email field return validation type empty field`() {
        val result = validationLoginInputUseCase(email = "" , password = "password")
        assertThat(result).isEqualTo(LoginInputValidationType.EmptyField)
    }

    @Test
    fun `test empty password field return validation type empty field`() {
        val result = validationLoginInputUseCase(email = "one@mail.com" , password = "")
        assertThat(result).isEqualTo(LoginInputValidationType.EmptyField)
    }

    @Test
    fun `test no email return validation type no eamil`() {
        val result = validationLoginInputUseCase(email = "onemail.com" , password = "password")
        assertThat(result).isEqualTo(LoginInputValidationType.NoEmail)
    }

    @Test
    fun `test everything is correct return validation type valid`() {
        val result = validationLoginInputUseCase(email = "one@mail.com" , password = "password")
        assertThat(result).isEqualTo(LoginInputValidationType.Valid)
    }
}