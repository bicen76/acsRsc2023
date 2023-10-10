package com.shaw.rsc2023.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaw.rsc2023.model.DriverRouteDataState
import com.shaw.rsc2023.model.Result
import com.shaw.rsc2023.model.RouteModel
import com.shaw.rsc2023.repo.RouteDataApiInteractor
import com.shaw.rsc2023.repo.RouteDataStoreInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RouteScreenMainViewModel(
    private val routeDataApiInteractor: RouteDataApiInteractor = RouteDataApiInteractor(),
    private val routeDataStoreInteractor: RouteDataStoreInteractor = RouteDataStoreInteractor()
) : ViewModel() {

    private var _routeList:List<RouteModel>? = arrayListOf()

    val routeList get() = _routeList

    private val _routeDataHolder = MutableLiveData(DriverRouteDataState())
    val routeDataHolder get() = _routeDataHolder

    private val compositeDisposable = CompositeDisposable()

    fun getDriverRouteData() {
        Log.d("RSLogging", "routes-main-getDriverRouteData - start")
        compositeDisposable.add(
            routeDataApiInteractor.getDriverRouteData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<Result>>() {
                    override fun onNext(t: Response<Result>) {
                        Log.d(
                            "RSLogging",
                            "routes-main-getDriverRouteData - onNext - ${
                                t.body()?.toString() ?: "empty"
                            }"
                        )
                        val data = (t.body()) ?: Result()
                        routeDataStoreInteractor.storeData(data)
                        _routeList = data.routeDataList?: arrayListOf()
                        val driverData = routeDataStoreInteractor.getStoredDriverData()

                        val isFailed =
                            (data.driverDataList.isNullOrEmpty() || data.routeDataList.isNullOrEmpty())
                        Log.d("RSLogging", "routes-main-getDriverRouteData - onNext - $isFailed")
                        _routeDataHolder.postValue(
                            DriverRouteDataState(
                                state = if (isFailed) RouteDataState.Called else RouteDataState.Success,
                                driverRouteData = data
                            )
                        )
                    }

                    override fun onError(e: Throwable) {
                        Log.d("RSLogging", "routes-main-getDriverRouteData - onError - $e")
                    }

                    override fun onComplete() {
                        Log.d("RSLogging", "routes-main-getDriverRouteData - onComplete")
                    }

                })
        )
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    fun clear() {
        _routeDataHolder.value = DriverRouteDataState()
    }

    enum class RouteDataState {
        NotCalled, Called, Success, Failure
    }

}