package com.shaw.rsc2023.fragments

import com.shaw.rsc2023.model.RouteModel
import java.io.Serializable


data class RouteDataSlice(val routeList: List<RouteModel>?): Serializable