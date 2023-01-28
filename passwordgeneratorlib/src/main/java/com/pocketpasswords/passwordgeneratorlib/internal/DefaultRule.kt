package com.pocketpasswords.passwordgeneratorlib.api


sealed class ValuesRule(private val values: CharArray) : PasswordGenerationRule {

    override fun isRuleFollowed(password: String): Boolean = password.any(values::contains)

    object SpecificCase : ValuesRule(
        charArrayOf(
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*',
            '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[',
            '\\', ']', '^', '_', '`', '{', '|', '}', '~'
        )
    )

    object UpperCase : ValuesRule(
        charArrayOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
    )

    object LowerCase : ValuesRule(
        charArrayOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )
    )

    object Numbers : ValuesRule(
        charArrayOf(
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
        )
    )

    object Space : ValuesRule(charArrayOf(' '))
}