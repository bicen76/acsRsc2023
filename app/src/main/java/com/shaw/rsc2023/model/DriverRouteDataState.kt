package com.shaw.rsc2023.model

import com.shaw.rsc2023.viewmodel.RouteScreenMainViewModel

class DriverRouteDataState(
    val state: RouteScreenMainViewModel.RouteDataState = RouteScreenMainViewModel.RouteDataState.NotCalled,
    val driverRouteData: Result? = null
)
