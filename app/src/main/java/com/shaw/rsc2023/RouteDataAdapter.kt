package com.shaw.rsc2023

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaw.rsc2023.databinding.ItemRouteViewBinding
import com.shaw.rsc2023.model.RouteModel

class RouteDataAdapter(
    private val context: Context,
    private val routeDataList: List<RouteModel>
) :
    RecyclerView.Adapter<RouteDataAdapter.RouteDataViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RouteDataViewHolder {
        val binding = ItemRouteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RouteDataViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RouteDataViewHolder, position: Int) {
        val textToDisplay = "Type: ${routeDataList[position].type} Route Name:${routeDataList[position].name}"
        holder.routeDetailItem?.text = textToDisplay
    }

    override fun getItemCount(): Int = routeDataList.size

    inner class RouteDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val routeDetailItem: TextView?
        init{
            routeDetailItem = view.findViewById(R.id.route_detail_item)
        }
    }
}
