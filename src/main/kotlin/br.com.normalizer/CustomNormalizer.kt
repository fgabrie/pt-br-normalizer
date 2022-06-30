package br.com.normalizer

import java.text.Normalizer
import java.util.regex.Pattern

class CustomNormalizer {

    private val alphaNumericRegex: Regex = "[^a-zA-z\\d\\s]".toRegex()
    private val withoutPrepositionsRegex: Regex = "(?:\\s+DAS|DOS|DA|DO|DE\\s+)".toRegex()
    private val uniqueSpaceRegex: Regex = "\\s\\s+".toRegex()

    fun normalize(field: String): String {
        val fieldWithoutCarets = removeCarets(field)
        val fieldWithoutSpecialCaracters = removeSpecialCharacters(fieldWithoutCarets)
        val fieldCapitalized = fieldWithoutSpecialCaracters.uppercase()
        val fieldWithoutPrepositions = removePrepositions(fieldCapitalized)
        return removeDuplicatedSpaces(fieldWithoutPrepositions)
    }

    fun removeCarets(field: String): String {
        var caretNormalizedString: String = Normalizer.normalize(field, Normalizer.Form.NFD)
        var pattern: Pattern= Pattern.compile("\\p{InCombiningDiacriticalMarks}+")

        return pattern.matcher(caretNormalizedString).replaceAll("")
    }

    fun removeSpecialCharacters(field: String): String {
        return field.replace(alphaNumericRegex, "")
    }

    fun removePrepositions(field: String): String {
        return field.replace(withoutPrepositionsRegex, " ")
    }

    fun removeDuplicatedSpaces(field: String): String {
        return field.replace(uniqueSpaceRegex, " ")
    }
}
