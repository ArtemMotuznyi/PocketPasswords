package com.pocketpasswords.passwordgeneratorlib.api

interface PasswordGenerationRule {
    fun isRuleFollowed(password: String): Boolean
}