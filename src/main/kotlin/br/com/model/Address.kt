package br.com.model

data class Address (
        var principal: Boolean?,
        var purposes: MutableSet<Int>?,
        var street: String?,
        var number: Int?,
        var complement: String?,
        var neighborhood: String? = null,
        var zipCode: String?,
        var postalAreaCode: String?,
        var city: String?,
        var state: String?,
        var country: String?,
        var department: String?
)
