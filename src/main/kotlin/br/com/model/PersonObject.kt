package br.com.model

open class PersonObject<T>(
        var value: T,
        var validation: Validation? = null
)
