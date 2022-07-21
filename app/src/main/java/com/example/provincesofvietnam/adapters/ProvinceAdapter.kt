package com.example.provincesofvietnam.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.provincesofvietnam.presentation.provincelist.OverviewFragmentDirections
import com.example.provincesofvietnam.R
import com.example.provincesofvietnam.databinding.ProvinceItemBinding
import com.example.provincesofvietnam.domain.ProvinceDomain

class ProvinceAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProvinceDomain, ProvinceAdapter.ProvinceViewHolder>(DiffCallback) {

    class ProvinceViewHolder(
        private val viewDataBinding: ProvinceItemBinding
    ): RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(province: ProvinceDomain, onClickListener: OnClickListener) {
            viewDataBinding.province = province
            if (province.divisionType != "tỉnh") {
                viewDataBinding.provinceName.setTextColor(Color.RED)
            } else {
                viewDataBinding.provinceName.setTextColor(Color.BLACK)
            }
            itemView.setOnClickListener {
                onClickListener.onClick(province)
            }
            viewDataBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProvinceViewHolder {

        return ProvinceViewHolder(
            ProvinceItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(
        holder: ProvinceViewHolder,
        position: Int
    ) {
        val province = getItem(position)
        holder.bind(province, onClickListener)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProvinceDomain>() {
        override fun areItemsTheSame(oldItem: ProvinceDomain, newItem: ProvinceDomain): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProvinceDomain, newItem: ProvinceDomain): Boolean {
            return oldItem.code == newItem.code
        }

    }

    class OnClickListener(
        val clickListener: (province: ProvinceDomain) -> Unit
    ) {
        fun onClick(province: ProvinceDomain) = clickListener(province)
    }
}