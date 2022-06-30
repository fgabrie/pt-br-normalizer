package br.com.normalizer

import java.text.Normalizer
import java.util.regex.Pattern

class CustomNormalizer {

    fun normalize(field: String): String {
        val fieldWithoutCarets = removeCarets(field)
        val fieldWithoutSpecialCaracters = removeSpecialCaracters(fieldWithoutCarets)

        return fieldWithoutSpecialCaracters
    }

    fun removeCarets(field: String): String {
        var nfdNormalizedString: String = Normalizer.normalize(field, Normalizer.Form.NFD)
        var pattern: Pattern= Pattern.compile("\\p{InCombiningDiacriticalMarks}+")

        return pattern.matcher(nfdNormalizedString).replaceAll("")
    }

    fun removeSpecialCaracters(field: String): String {
        var regex: Regex = "[^a-zA-z\\d\\s]".toRegex()

        return field.replace(regex, "")
    }
}
