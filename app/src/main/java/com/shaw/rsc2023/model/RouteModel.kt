package com.shaw.rsc2023.model

import com.fasterxml.jackson.annotation.JsonProperty

class RouteModel {
    @JsonProperty("id")
    var id: Int? = null

    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("name")
    var name: String? = null

}

