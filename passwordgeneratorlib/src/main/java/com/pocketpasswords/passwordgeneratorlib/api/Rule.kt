package com.pocketpasswords.passwordgeneratorlib.api

sealed interface Rule {

    class LengthRule(val length: Int) : Rule

    interface CharRule : Rule {
        val ruleValues: CharArray
    }

    companion object {
        private val DEFAULT_MIN_LENGTH = 8
        private val DEFAULT_MAX_LENGTH = 64
    }

}

enum class DefaultCharRule(override val ruleValues: CharArray) : Rule.CharRule {
    SPECIFIC_SYMBOLS(
        charArrayOf(
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*',
            '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[',
            '\\', ']', '^', '_', '`', '{', '|', '}', '~'
        )
    ),
    UPPER_CASE(
        charArrayOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
    ),
    LOWER_CASE(
        charArrayOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )
    ),
    NUMBERS(charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')),
    SPACE(charArrayOf(' '))
}

