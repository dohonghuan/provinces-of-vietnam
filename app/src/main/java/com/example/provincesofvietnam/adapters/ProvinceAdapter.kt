package com.example.provincesofvietnam.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.provincesofvietnam.presentation.provincelist.OverviewFragmentDirections
import com.example.provincesofvietnam.R
import com.example.provincesofvietnam.databinding.ProvinceItemBinding
import com.example.provincesofvietnam.domain.ProvinceDomain

class ProvinceAdapter() : RecyclerView.Adapter<ProvinceViewHolder>() {

    var provincesList: List<ProvinceDomain> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceViewHolder {

        val withDataBinding: ProvinceItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ProvinceViewHolder.LAYOUT,
            parent,
            false
        )

        return ProvinceViewHolder(withDataBinding).apply {
            viewDataBinding.root.setOnClickListener {
                val action = OverviewFragmentDirections.actionOverviewFragmentToProvinceFragment(
                    codeId = viewDataBinding.province!!.code,
                    provinceName = viewDataBinding.province!!.name
                )
                viewDataBinding.root.findNavController().navigate(action)
            }
        }
    }

    override fun onBindViewHolder(
        holder: ProvinceViewHolder,
        position: Int
    ) {
        holder.viewDataBinding.also {
            it.province = provincesList[position]
            if (provincesList[position].divisionType != "tỉnh") {
                it.provinceName.setTextColor(Color.RED)
            } else {
                it.provinceName.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount() = provincesList.size
}

class ProvinceViewHolder(val viewDataBinding: ProvinceItemBinding
): RecyclerView.ViewHolder(viewDataBinding.root) {

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.province_item
    }

}