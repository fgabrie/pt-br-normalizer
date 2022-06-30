package br.com.normalizer

import br.com.model.Person
import br.com.model.PersonObject
import java.text.Normalizer
import java.util.regex.Pattern

class CustomNormalizer {

    private val alphaNumericRegex: Regex = "[^a-zA-z\\d\\s]".toRegex()
    private val withoutPrepositionsRegex: Regex = "\\s+DAS\\s+|\\s+DOS\\s+|\\s+DA\\s+|\\s+DO\\s+|\\s+DE\\s+".toRegex()
    private val uniqueSpaceRegex: Regex = "\\s\\s+".toRegex()

    fun normalize(person: Person): Person {

        var personNormalizedToCompare: Person = person.copy()
        var i: Int = 0

        personNormalizedToCompare.fullName?.value = person.fullName?.value?.let { normalizeUniqueField(it) }.toString()

        person.address?.forEach { personAddress ->
            personNormalizedToCompare.address?.get(index = i)?.value?.street = personAddress.value?.street?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.complement = personAddress.value?.complement?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.neighborhood = personAddress.value?.neighborhood?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.zipCode = personAddress.value?.zipCode?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.postalAreaCode = personAddress.value?.postalAreaCode?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.city = personAddress.value?.city?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.state = personAddress.value?.state?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.country = personAddress.value?.country?.let { normalizeUniqueField(it) }
            personNormalizedToCompare.address?.get(index = i)?.value?.department = personAddress.value?.department?.let { normalizeUniqueField(it) }
            i++
        }
        return personNormalizedToCompare
    }

    fun normalizeUniqueField(field: String): String {
        val fieldWithoutCarets = removeCarets(field)
        val fieldWithoutSpecialCaracters = removeSpecialCharacters(fieldWithoutCarets)
        val fieldCapitalized = fieldWithoutSpecialCaracters.uppercase()
        val fieldWithoutPrepositions = removePrepositions(fieldCapitalized)

        return removeDuplicatedSpaces(fieldWithoutPrepositions).trim()
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
