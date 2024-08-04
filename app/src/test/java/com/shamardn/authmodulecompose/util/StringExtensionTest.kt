package com.shamardn.authmodulecompose.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `test string contains number return true`(){
        val result = "password123".containsNumber()
        assertThat(result).isTrue()
    }

    @Test
    fun `test string contains no number return false`(){
        val result = "password".containsNumber()
        assertThat(result).isFalse()
    }

    @Test
    fun `test string contains no upper case return false`(){
        val result = "password".hasUpperCase()
        assertThat(result).isFalse()
    }

    @Test
    fun `test string contains upper case return true`(){
        val result = "Password".hasUpperCase()
        assertThat(result).isTrue()
    }

    @Test
    fun `test string doesn't contain special char return false`(){
        val result = "password".containsSpecialChar()
        assertThat(result).isFalse()
    }

    @Test
    fun `test string contains special char return true`(){
        val result = "password_".containsSpecialChar()
        assertThat(result).isTrue()
    }

    @Test
    fun `test string is a valid email return true`(){
        val result = "one@mail.com".isValidEmail()
        assertThat(result).isTrue()
    }

    @Test
    fun `test string is not a valid email return false`(){
        val result = "onemail.com".isValidEmail()
        assertThat(result).isFalse()
    }

}