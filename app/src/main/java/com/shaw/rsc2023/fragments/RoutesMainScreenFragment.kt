package com.shaw.rsc2023.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaw.rsc2023.DriverDetailClickListener
import com.shaw.rsc2023.DriverDataAdapter
import com.shaw.rsc2023.R
import com.shaw.rsc2023.viewmodel.RouteScreenMainViewModel
import com.shaw.rsc2023.databinding.FragmentRouteScreenMainBinding
import com.shaw.rsc2023.model.DriverModel

class RoutesMainScreenFragment : BaseRouteScreenFragment(), DriverDetailClickListener {

    private lateinit var routeScreenMainViewModel: RouteScreenMainViewModel

    private var binding: FragmentRouteScreenMainBinding? = null

    private var adapter: DriverDataAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouteScreenMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("RSLogging", "routes-main-onViewCreated")
        routeScreenMainViewModel = RouteScreenMainViewModel()
        super.onViewCreated(view, savedInstanceState)
        routeScreenMainViewModel.getDriverRouteData()
    }

    override fun onResume() {
        Log.d("RSLogging", "routes-main-onResume")
        super.onResume()
        binding?.driverSort?.setOnClickListener {
            sortDataSet()
        }
    }

    private fun sortDataSet() {
        Log.d("RSLogging", "routes-main-sortDataSet")
        adapter?.sortData()
    }

    override fun onPause() {
        super.onPause()
        binding?.driverSort?.setOnClickListener(null)
    }

    override fun onDestroyView() {
        removeObservers()
        binding = null
        routeScreenMainViewModel.clearDisposable()
        routeScreenMainViewModel.clear()
        super.onDestroyView()
    }

    override fun configureRecycler() {
        val lm = LinearLayoutManager(context)
        lm.orientation= LinearLayoutManager.VERTICAL
        binding?.driverRouteData?.layoutManager = lm
    }

    override fun setupObservers() {
        Log.d("RSLogging", "routes-main-observe-Start")
        routeScreenMainViewModel.routeDataHolder.observe(this.viewLifecycleOwner) {
            Log.d("RSLogging", "routes-main-observe-callback")
            when {
                (it.state == RouteScreenMainViewModel.RouteDataState.Success) -> {
                    Log.d("RSLogging", "routes-main-observe-Success")
                    setAdapter(it.driverRouteData?.driverDataList ?: emptyList())
                }

                (it.state == RouteScreenMainViewModel.RouteDataState.Called) -> {
                    Log.d("RSLogging", "routes-main-observe-Called")
                    setAdapter(emptyList())
                }

                (it.state == RouteScreenMainViewModel.RouteDataState.NotCalled) -> {
                    Log.d("RSLogging", "routes-main-observe-NotCalled")
                }
                else -> {
                    Log.d("RSLogging", "routes-main-observe-Failure")
                }
            }
        }
    }

    private fun setAdapter(driverDataList: List<DriverModel>) {
        Log.d("RSLogging", "routes-main-setAdapter")
        context?.let {
            adapter = DriverDataAdapter(
                it,
                driverDataList.toMutableList(),
                onClick = { id -> driverDetailClick(id) })
            binding?.driverRouteData?.adapter = adapter
        }

    }

    override fun removeObservers() {
        routeScreenMainViewModel.routeDataHolder.removeObservers(this.viewLifecycleOwner)
    }

    override fun driverDetailClick(id: String) {
        Log.d("RSLogging", "routes-main-driverDetailClick")
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, RouteScreenFragment.getInstance(id, RouteDataSlice(routeScreenMainViewModel.routeList)))?.addToBackStack(null)?.commit()
    }

}