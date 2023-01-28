package com.pocketpasswords.passwordgeneratorlib.api

import com.pocketpasswords.passwordgeneratorlib.api.model.Password
import com.pocketpasswords.passwordgeneratorlib.internal.Generator

interface PasswordGenerator {

    fun generate(): Password

    class Builder {
        private val rules: MutableSet<PasswordGenerationRule> = mutableSetOf()
        private var length: Int = MIN_LENGTH

        private fun setRuleEnabled(isEnabled: Boolean, rule: PasswordGenerationRule) = this.apply {
            if (isEnabled) rules.add(rule) else rules.remove(rule)
        }

        fun addCustomRule(rule: PasswordGenerationRule) = this.apply {
            rules.add(rule)
        }

        fun setSpaceEnable(isEnabled: Boolean) = setRuleEnabled(isEnabled, DefaultRule.SPACE)

        fun setUpperCaseEnabled(isEnabled: Boolean) =
            setRuleEnabled(isEnabled, DefaultRule.UPPER_CASE)

        fun setLowerCaseEnabled(isEnabled: Boolean) =
            setRuleEnabled(isEnabled, DefaultRule.LOWER_CASE)

        fun setSpecificSymbolsEnabled(isEnabled: Boolean) =
            setRuleEnabled(isEnabled, DefaultRule.SPECIFIC_SYMBOLS)

        fun setNumberEnabled(isEnabled: Boolean) = setRuleEnabled(isEnabled, DefaultRule.NUMBERS)

        fun setLength(length: Int) = this.apply {
            require(length in MIN_LENGTH..MAX_LENGTH) {
                "\"length\" value has to be between $MIN_LENGTH and $MAX_LENGTH"
            }

            this.length = length
        }

        fun build(): PasswordGenerator = Generator(
            rules,
            length
        )

        private companion object {
            const val MIN_LENGTH = 8
            const val MAX_LENGTH = 64
        }
    }


}