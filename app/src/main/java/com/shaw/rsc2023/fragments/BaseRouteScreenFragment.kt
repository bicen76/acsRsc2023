package com.shaw.rsc2023.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shaw.rsc2023.RouteScreenListener

open class BaseRouteScreenFragment : Fragment(), RouteScreenListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecycler()
        setupObservers()
    }

    override fun configureRecycler() {}

    override fun setupObservers() {}

    override fun removeObservers() {}

}