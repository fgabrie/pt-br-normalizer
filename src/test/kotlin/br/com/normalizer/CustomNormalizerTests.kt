package br.com.normalizer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CustomNormalizerTests() {

    private val normalizer: CustomNormalizer = CustomNormalizer()

    @Test
    fun removeCaretsTest() {
        val fieldToNormalize: String = "áéíóú ÁÉÍÓÚ ãÃ \$%.,'-* de da do  das dos üÜ èÈ"
        val fieldNormalized: String = "aeiou AEIOU aA \$%.,'-* de da do  das dos uU eE"

        assertEquals(fieldNormalized, normalizer.removeCarets(fieldToNormalize))
    }

    @Test
    fun removeSpecialCharactersTest() {
        val fieldToNormalize: String = "áaeiou AEIOU aA \$%.,'-* de da do  das dos uU eE"
        val fieldNormalized: String = "aeiou AEIOU aA  de da do  das dos uU eE"

        assertEquals(fieldNormalized, normalizer.removeSpecialCharacters(fieldToNormalize))
    }

    @Test
    fun removePrepositionsTest() {
        val fieldToNormalize: String = "aeiou AEIOU aA  de da do  das dos uU eE"
        val fieldNormalized: String = "aeiou AEIOU aA          uU eE"

        assertEquals(fieldNormalized.uppercase(), normalizer.removePrepositions(fieldToNormalize.uppercase()))
    }

    @Test
    fun removeDuplicatedSpacesTest() {
        val fieldToNormalize: String = "aeiou AEIOU aA          uU eE"
        val fieldNormalized: String = "AEIOU AEIOU AA UU EE"

        assertEquals(fieldNormalized, normalizer.removeDuplicatedSpaces(fieldToNormalize.uppercase()))
    }

    @Test
    fun normalizeTest() {
        val fieldToNormalize: String = "áéíóú ÁÉÍÓÚ ãÃ \$%.,'-* de da do  das dos üÜ èÈ"
        val fieldNormalized: String = "AEIOU AEIOU AA UU EE"

        assertEquals(fieldNormalized, normalizer.normalize(fieldToNormalize))
    }

}
