package com.example.pharmafiesta.ui.home.laboratores.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmafiesta.databinding.LayoutLabsItemBinding
import com.example.pharmafiesta.databinding.LayoutTestItemBinding
import com.example.pharmafiesta.ui.home.laboratores.model.ModelLabs
import com.example.pharmafiesta.utils.layoutInflater

class AdapterLabs : ListAdapter<ModelLabs, RecyclerView.ViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            LayoutLabsItemBinding.inflate(parent.layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        getItem(position)?.let {
            (holder as ViewHolder).bind(it)

        }
    }

    private inner class ViewHolder(private val binding: LayoutLabsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: ModelLabs) = with(binding) {

            tvLabNameValue.text = item.labName
            tvSiteValue.text = item.labSite
            tvPhoneValue.text = item.phone

        }

    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ModelLabs>() {
            override fun areItemsTheSame(
                oldItem: ModelLabs,
                newItem: ModelLabs
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ModelLabs,
                newItem: ModelLabs
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}