package com.pocketpasswords.passwordgeneratorlib.internal

import com.pocketpasswords.passwordgeneratorlib.api.PasswordGenerationRule
import com.pocketpasswords.passwordgeneratorlib.api.PasswordGenerator
import com.pocketpasswords.passwordgeneratorlib.api.model.Password
import com.pocketpasswords.passwordgeneratorlib.api.model.SecureLevel
import java.security.SecureRandom

internal class Generator constructor(
    private val rules: Set<PasswordGenerationRule>,
    private val length: Int
) : PasswordGenerator {

    override fun generate(): Password {
        val availableChars = rules.flatMap { it.ruleValues.toList() }
        val sr = SecureRandom()

        val passwordBuilder = StringBuilder(length)

        repeat(length) {
            val char = availableChars.get(sr.nextInt(availableChars.size))
            passwordBuilder.append(char)
        }

        val password = passwordBuilder.toString()
        val secureLevel = getSecureLevel()
        return Password(password, secureLevel)
    }

    private fun getSecureLevel(): SecureLevel {
        return when {
            length <= 8 -> SecureLevel.LOW
            length in 10..12 -> SecureLevel.MEDIUM
            else -> SecureLevel.HEIGHT
        }
    }
}