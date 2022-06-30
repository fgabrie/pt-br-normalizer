package br.com.normalizer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CustomNormalizerTests() {

    private val normalizer: CustomNormalizer = CustomNormalizer()

    @Test
    fun removeCaretsTest() {
        val fieldToNormalize: String = "áéíóú ÁÉÍÓÚ ãÃ \$%.,'-* de da do des das dos üÜ èÈ"
        val fieldNormalized: String = "aeiou AEIOU aA \$%.,'-* de da do des das dos uU eE"

        assertEquals(fieldNormalized, normalizer.removeCarets(fieldToNormalize))
    }

    @Test
    fun removeSpecialCaractersTest() {
        val fieldToNormalize: String = "áaeiou AEIOU aA \$%.,'-* de da do des das dos uU eE"
        val fieldNormalized: String = "aeiou AEIOU aA  de da do des das dos uU eE"

        assertEquals(fieldNormalized, normalizer.removeSpecialCaracters(fieldToNormalize))
    }

    @Test
    fun normalizeTest() {
        val fieldToNormalize: String = "áéíóú ÁÉÍÓÚ ãÃ \$%.,'-* de da do des das dos üÜ èÈ"
        val fieldNormalized: String = "aeiou AEIOU aA  de da do des das dos uU eE"

        assertEquals(fieldNormalized, normalizer.normalize(fieldToNormalize))
    }

}
