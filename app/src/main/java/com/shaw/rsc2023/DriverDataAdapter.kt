package com.shaw.rsc2023

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaw.rsc2023.databinding.ItemDriverViewBinding
import com.shaw.rsc2023.model.DriverModel

class DriverDataAdapter(
    private val context: Context,
    private var driverDataList: MutableList<DriverModel>,
    private val onClick: (id: String) -> Unit
) :
    RecyclerView.Adapter<DriverDataAdapter.DriverViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DriverViewHolder {
        val binding = ItemDriverViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriverViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.driverDetailItem?.text = driverDataList[position].name
        holder.driverDetailItem?.tag = driverDataList[position].id
    }

    override fun getItemCount(): Int = driverDataList.size
    fun sortData() {
        if(driverDataList.isNotEmpty()){
            val sortOrderList = driverDataList.sortedBy { d -> d.lastName }.toMutableList()
            driverDataList = sortOrderList
            notifyDataSetChanged()
        }

    }

    inner class DriverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val driverDetailItem: TextView?

        init {
            driverDetailItem = view.findViewById(R.id.driver_detail_item)
            driverDetailItem.setOnClickListener {
                (it.tag as? String)?.let { id ->
                    onClick.invoke(id)
                }
            }
        }

    }
}
