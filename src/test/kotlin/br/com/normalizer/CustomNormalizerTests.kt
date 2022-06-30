package br.com.normalizer

import br.com.model.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CustomNormalizerTests() {

    private val normalizer: CustomNormalizer = CustomNormalizer()
    private val person: Person = Person()

    @BeforeEach
    fun initEach(){
        person.fullName = PersonObject<String>("João José DO Primeiro NOME de And#rade de Oliveira",
                Validation(validateCompletess = true, level = 150))
        person.civilStatus = PersonObject<Int>(10, Validation(validateCompletess = true, level = 200))

        val address1: Address = Address(principal = false,
                                        purposes = mutableSetOf(1,2,3,4),
                                        street = "Rua Primeiro de Oliveira da ",
                                        number = 5684,
                                        complement = "Segunda casa do lote",
                                        neighborhood = null,
                                        zipCode = null,
                                        postalAreaCode = "",
                                        city = "Mauá",
                                        state = "São   Paulo  ",
                                        country = "BR",
                                        department = "Financeiro")
        val address2: Address = Address(principal = false,
                                        purposes = mutableSetOf(1,2,3,4),
                                        street = "Rua Primeiro de Oliveira da 2443",
                                        number = 3425,
                                        complement = "Segunda   casa  do       6546     lote",
                                        neighborhood = null,
                                        zipCode = null,
                                        postalAreaCode = "",
                                        city = "é s§ao pau%lo",
                                        state = "São Paulo",
                                        country = "BR",
                                        department = "Financeiro")
        person.address = listOf(PersonAddress(address1, Validation(validateCompletess = true, level = 150)),
                                PersonAddress(address2,Validation(validateCompletess = true, level = 150)))
    }

    @Test
    @DisplayName("Should remove all carets")
    fun removeCaretsTest() {
        val fieldToNormalize: String = "áéíóú ÁÉÍÓÚ ãÃ \$%.,'-* de 12 da 34 do 568 das 555 dos üÜ èÈ"
        val fieldNormalized: String = "aeiou AEIOU aA \$%.,'-* de 12 da 34 do 568 das 555 dos uU eE"

        assertEquals(fieldNormalized, normalizer.removeCarets(fieldToNormalize))
    }

    @Test
    @DisplayName("Should remove all special characters")
    fun removeSpecialCharactersTest() {
        val fieldToNormalize: String = "áaeiou AEIOU aA \$%.,'-* de 12 da 34 do 568 das 555 dos uU eE"
        val fieldNormalized: String = "aeiou AEIOU aA  de 12 da 34 do 568 das 555 dos uU eE"

        assertEquals(fieldNormalized, normalizer.removeSpecialCharacters(fieldToNormalize))
    }

    @Test
    @DisplayName("Should remove prepositions")
    fun removePrepositionsTest() {
        val fieldToNormalize: String = "aeiou AEIOU aA  de 12 da 34 do 568 das 555 dos uU eE"
        val fieldNormalized: String = "AEIOU AEIOU AA 12 34 568 555 UU EE"

        assertEquals(fieldNormalized.uppercase(), normalizer.removePrepositions(fieldToNormalize.uppercase()))
    }

    @Test
    @DisplayName("Should remove duplicated spaces")
    fun removeDuplicatedSpacesTest() {
        val fieldToNormalize: String = "aeiou AEIOU aA          uU eE"
        val fieldNormalized: String = "AEIOU AEIOU AA UU EE"

        assertEquals(fieldNormalized, normalizer.removeDuplicatedSpaces(fieldToNormalize.uppercase()))
    }

    @Test
    @DisplayName("Should apply all rules in order to normalize the string")
    fun normalizeUniqueFieldTest() {
        val fieldToNormalize: String = "áéíóú ÁÉÍÓÚ ãÃ \$%.,'-* de 12 da 34 do   568 das  555  dos üÜ èÈ"
        val fieldNormalized: String = "AEIOU AEIOU AA 12 34 568 555 UU EE"

        assertEquals(fieldNormalized, normalizer.normalizeUniqueField(fieldToNormalize))
    }

    @Test
    @DisplayName("Should normalize a complex entity like `Person` with multiple string fields")
    fun normalizeTest() {
        var personNormalized: Person = normalizer.normalize(person)

        assertEquals("JOAO JOSE PRIMEIRO NOME ANDRADE OLIVEIRA", personNormalized.fullName?.value)

        assertEquals("RUA PRIMEIRO OLIVEIRA", personNormalized.address?.get(0)?.value?.street)
        assertEquals("SEGUNDA CASA LOTE", personNormalized.address?.get(0)?.value?.complement)
        assertEquals(null, personNormalized.address?.get(0)?.value?.neighborhood)
        assertEquals("MAUA", personNormalized.address?.get(0)?.value?.city)
        assertEquals("SAO PAULO", personNormalized.address?.get(0)?.value?.state)
        assertEquals("BR", personNormalized.address?.get(0)?.value?.country)

        assertEquals("RUA PRIMEIRO OLIVEIRA 2443", personNormalized.address?.get(1)?.value?.street)
        assertEquals("SEGUNDA CASA 6546 LOTE", personNormalized.address?.get(1)?.value?.complement)
        assertEquals(null, personNormalized.address?.get(1)?.value?.neighborhood)
        assertEquals("E SAO PAULO", personNormalized.address?.get(1)?.value?.city)
        assertEquals("SAO PAULO", personNormalized.address?.get(1)?.value?.state)
        assertEquals("BR", personNormalized.address?.get(1)?.value?.country)
    }
}
