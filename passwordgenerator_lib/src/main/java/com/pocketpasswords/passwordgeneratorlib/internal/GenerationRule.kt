package com.pocketpasswords.passwordgeneratorlib.internal

sealed interface GenerationRule

sealed class ValuesRule(private val values: CharArray) : GenerationRule {

    object SpecificCase : ValuesRule(
        charArrayOf(
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*',
            '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[',
            '\\', ']', '^', '_', '`', '{', '|', '}', '~'
        )
    )

    object LetterCase : ValuesRule(
        charArrayOf(
            'A', 'B', 'C', 'D', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
    )

    object LowerCase : ValuesRule(
        charArrayOf(
            'a', 'b', 'c', 'd', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )
    )

    object Numbers : ValuesRule(
        charArrayOf(
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
        )
    )

    object Space : ValuesRule(charArrayOf(' '))

    fun containValues(password: String): Boolean =
        password.any(values::contains)

}

