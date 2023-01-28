package com.pocketpasswords.passwordgeneratorlib.api.model


data class Password(
    val password: String,
    val secureLevel: SecureLevel
)