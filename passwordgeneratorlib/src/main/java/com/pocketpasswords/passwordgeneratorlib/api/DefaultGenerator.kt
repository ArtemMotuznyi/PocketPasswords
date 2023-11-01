package com.pocketpasswords.passwordgeneratorlib.api

import com.pocketpasswords.passwordgeneratorlib.api.model.Password
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.security.SecureRandom

class DefaultGenerator : PasswordGenerator {

    private val _rules = MutableStateFlow(
        DefaultCharRule.values().toList() + Rule.LengthRule(DEFAULT_PASSWORD_LENGTH)
    )

    override val rules: StateFlow<List<Rule>>
        get() = _rules.asStateFlow()

    override fun generate(): Password {
        val availableChars = rules.value.filterIsInstance<Rule.CharRule>().flatMap {
            it.ruleValues.toList()
        }
        val sr = SecureRandom()
        val length = getLength()

        val passwordBuilder = StringBuilder(length)

        repeat(length) {
            val char = availableChars.get(sr.nextInt(availableChars.size))
            passwordBuilder.append(char)
        }

        val password = passwordBuilder.toString()
        return Password(password)
    }

    override fun addRule(rule: Rule) {
        _rules.update { currentRules ->
            (currentRules + rule)
        }
    }

    override fun removeRule(rule: Rule) {
        _rules.update { currentRules ->
            (currentRules - rule)
        }
    }

    override fun setLength(length: Int) {
        val rule = firstInstanceOrNull<Rule.LengthRule>()

        if (rule != null) {
            if (rule.length == length) {
                return
            }
            removeRule(rule)
        }
        addRule(Rule.LengthRule(length))
    }

    override fun getLength(): Int =
        firstInstanceOrNull<Rule.LengthRule>()?.length ?: DEFAULT_PASSWORD_LENGTH

    private inline fun <reified R : Rule> firstInstanceOrNull(): R? = rules.value.firstOrNull {
        it is R
    } as R?

    companion object {
        private const val DEFAULT_PASSWORD_LENGTH = 8
    }

}