package com.shaw.rsc2023.model

import com.fasterxml.jackson.annotation.JsonProperty

class DriverModel {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("name")
    var name: String? = null

    val lastName
        get() = if (!name.isNullOrBlank() && name?.contains(" ") == true) {
            name?.split(" ")?.get(1) ?: ""
        } else {
            ""
        }
}
