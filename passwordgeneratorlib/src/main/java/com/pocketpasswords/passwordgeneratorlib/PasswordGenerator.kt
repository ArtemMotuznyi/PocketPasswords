package com.pocketpasswords.passwordgeneratorlib

import com.pocketpasswords.passwordgeneratorlib.api.PasswordGenerationRule
import com.pocketpasswords.passwordgeneratorlib.api.ValuesRule

class PasswordGenerator private constructor(
    private val rules: Set<PasswordGenerationRule>,
    private val minLength: Int,
    private val maxLength: Int
) {


    class Builder {
        private val rules: MutableSet<PasswordGenerationRule> = mutableSetOf()
        private var minLength: Int = MIN_LENGTH
        private var maxLength: Int = MAX_LENGTH

        private fun setRuleEnabled(isEnabled: Boolean, rule: PasswordGenerationRule) = this.apply {
            if (isEnabled) rules.add(rule) else rules.remove(rule)
        }

        fun addCustomRule(rule: PasswordGenerationRule) = this.apply {
            rules.add(rule)
        }

        fun setSpaceEnable(isEnabled: Boolean) = setRuleEnabled(isEnabled, ValuesRule.Space)

        fun setUpperCaseEnabled(isEnabled: Boolean) =
            setRuleEnabled(isEnabled, ValuesRule.UpperCase)

        fun setLowerCaseEnabled(isEnabled: Boolean) =
            setRuleEnabled(isEnabled, ValuesRule.LowerCase)

        fun setSpecificSymbolsEnabled(isEnabled: Boolean) =
            setRuleEnabled(isEnabled, ValuesRule.SpecificCase)

        fun setNumberEnabled(isEnabled: Boolean) = setRuleEnabled(isEnabled, ValuesRule.Numbers)

        fun setMinLength(min: Int) = this.apply {
            require(min >= MIN_LENGTH) { "\"min\" value has to be greater that 0" }
            minLength = min
        }

        fun setMaxLength(max: Int) = this.apply {
            maxLength = max
        }

        fun build() = PasswordGenerator(
            rules,
            minLength,
            maxLength
        )

        private companion object {
            const val MIN_LENGTH = 1
            const val MAX_LENGTH = 64
        }
    }


}