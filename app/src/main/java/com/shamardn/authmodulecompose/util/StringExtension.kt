package com.shamardn.authmodulecompose.util

import android.util.Patterns
import androidx.core.util.PatternsCompat


fun String.containsNumber(): Boolean {
    val regex = Regex("\\d") // Matches any digit (0-9)
    return regex.containsMatchIn(this)
}

fun String.containsSpecialChar(): Boolean {
    val regex = Regex("[^A-Za-z0-9 ]") // Matchesanything not alphanumeric or space
    return regex.containsMatchIn(this)
}

fun String.hasUpperCase(): Boolean {
    val regex = Regex("[A-Z]") // Matches any uppercase letter
    return regex.containsMatchIn(this)
}

fun String.isValidEmail(): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}