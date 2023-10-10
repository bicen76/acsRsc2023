package com.shaw.rsc2023.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaw.rsc2023.RouteDataAdapter
import com.shaw.rsc2023.viewmodel.RouteScreenViewModel
import com.shaw.rsc2023.databinding.FragmentRouteScreenDetailBinding
import com.shaw.rsc2023.model.RouteModel

class RouteScreenFragment : BaseRouteScreenFragment() {

    private lateinit var routeScreenViewModel: RouteScreenViewModel

    private var binding: FragmentRouteScreenDetailBinding? = null

    private var adapter: RouteDataAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRouteScreenDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        removeObservers()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        routeScreenViewModel = RouteScreenViewModel()
        selectedDriverId = arguments?.getString("EXTRA_ID")
        routes = arguments?.getSerializable("EXTRA_ROUTES") as? RouteDataSlice
        super.onViewCreated(view, savedInstanceState)
        if (!selectedDriverId.isNullOrBlank() && selectedDriverId?.isDigitsOnly() == true) {
            selectedDriverId?.let {
                routeScreenViewModel.getDriverRouteData(it, routes?.routeList ?: emptyList())
            }
        }
    }

    private var routes: RouteDataSlice? = null
    private var selectedDriverId: String? = null
    override fun configureRecycler() {
        val lm = LinearLayoutManager(context)
        lm.orientation = LinearLayoutManager.VERTICAL
        binding?.driverRouteDetailData?.layoutManager = lm
    }

    override fun setupObservers() {
        Log.d("RSLogging", "routes-screen-setupObservers")
        routeScreenViewModel.driverRouteDataHolder.observe(this.viewLifecycleOwner) {
            Log.d("RSLogging", "routes-screen-observe")
            setAdapter(it.routeDataList ?: emptyList())
        }
    }

    private fun setAdapter(routeDataList: List<RouteModel>) {
        Log.d("RSLogging", "routes-screen-setAdapter")
        context?.let {
            adapter = RouteDataAdapter(
                it,
                routeDataList
            )
            binding?.driverRouteDetailData?.adapter = adapter
        }

    }

    override fun removeObservers() {
        routeScreenViewModel.driverRouteDataHolder.removeObservers(this.viewLifecycleOwner)
    }

    companion object {
        fun getInstance(id: String, routes: RouteDataSlice): RouteScreenFragment {
            return RouteScreenFragment().apply {
                this@apply.arguments = bundleOf("EXTRA_ID" to id, "EXTRA_ROUTES" to routes)
            }
        }
    }


}