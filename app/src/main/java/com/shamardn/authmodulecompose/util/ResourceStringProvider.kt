package com.shamardn.authmodulecompose.util

import android.content.Context


interface StringProvider {
    fun getString(resId: Int): String
}

class ResourceStringProvider(private val context: Context) : StringProvider {
    override fun getString(resId: Int): String = context.getString(resId)
}