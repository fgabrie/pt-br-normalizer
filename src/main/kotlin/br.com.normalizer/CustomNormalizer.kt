package br.com.normalizer

import java.text.Normalizer
import java.util.regex.Pattern

class CustomNormalizer {


    fun normalize(field: String): String {
        val fieldWithoutCarets = removeCarets(field)

        return fieldWithoutCarets
    }

    fun removeCarets(field: String): String {
        //return Normalizer.normalize(field, Normalizer.Form.NFD).replace("[^\\p{ASCII}]", "")
        var nfdNormalizedString: String = Normalizer.normalize(field, Normalizer.Form.NFD);
        var pattern: Pattern= Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");

    }
}
