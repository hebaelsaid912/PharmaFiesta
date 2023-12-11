package com.example.pharmafiesta.ui.home.medicaltest.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmafiesta.databinding.LayoutTestItemBinding
import com.example.pharmafiesta.ui.home.medicaltest.model.MedicalTestModel
import com.example.pharmafiesta.utils.layoutInflater

class AdapterMedicalTest :
    ListAdapter<MedicalTestModel, RecyclerView.ViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            LayoutTestItemBinding.inflate(parent.layoutInflater, parent, false)
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

    private inner class ViewHolder(private val binding: LayoutTestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: MedicalTestModel) = with(binding) {

            tvTestNameValue.text = item.testName
            tvPercentageValue.text = item.percentage
            tvTestRangeValue.text = item.testRange
            tvStatusValue.text = item.testStatus

        }

    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MedicalTestModel>() {
            override fun areItemsTheSame(
                oldItem: MedicalTestModel,
                newItem: MedicalTestModel
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: MedicalTestModel,
                newItem: MedicalTestModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}