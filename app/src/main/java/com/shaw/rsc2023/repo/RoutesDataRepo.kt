package com.shaw.rsc2023.repo

import com.shaw.rsc2023.model.Result
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

interface RoutesDataRepo {
    fun getDriverRouteData(): Observable<Response<Result>>
}