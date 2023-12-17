package com.example.pharmafiesta.ui.home.calculator.weightDose

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.pharmafiesta.R
import com.example.pharmafiesta.databinding.ActivityWeightDoseBinding
import com.example.pharmafiesta.utils.TxT
import com.example.pharmafiesta.utils.onTextChange
import com.example.pharmafiesta.utils.resetError
import com.example.pharmafiesta.utils.setError

class ActivityWeightDose : AppCompatActivity() {

    lateinit var binding: ActivityWeightDoseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightDoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        click()

    }

    @SuppressLint("SetTextI18n")
    private fun click() {
        binding.btnCalc.setOnClickListener {
            if (validateForm()) {

                val dose = calcDose(
                    binding.edDosage.TxT().toDouble(),
                    binding.edPatientWeight.TxT().toDouble()
                )
                val liquidDose = calcLiquidDose(dose,binding.edPerVolume.TxT().toDouble(),binding.edMedAmount.TxT().toDouble())

                binding.layoutLiquidDose.isVisible = true
                binding.layoutDose.isVisible = true

                val formatteddose= String.format("%.2f", dose)
                val formattedliquidDose= String.format("%.2f", liquidDose)

                binding.tvDoseResult.text = "th result of Dose is = $formatteddose MG"
                binding.tvLiquidDoseResult.text = "th result of Liquid Dose is = $formattedliquidDose ML"

            }
        }

        binding.btnClear.setOnClickListener {
            binding.edDosage.setText("")
            binding.edPatientWeight.setText("")
            binding.edMedAmount.setText("")
            binding.edPerVolume.setText("")
            binding.layoutDose.isVisible = false
            binding.layoutLiquidDose.isVisible = false
        }

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun calcDose(dosage: Double, weight_kg: Double): Double {
        val dose = weight_kg * dosage
        return dose
    }

    private fun calcLiquidDose(dose:Double,per_volume: Double,med_amount: Double): Double {
        val liquid_dose = (dose * per_volume) / med_amount
        return liquid_dose
    }

    private fun initialize() = with(binding) {
        edDosage.onTextChange {
            edDosage.resetError()
        }

        edPatientWeight.onTextChange {
            edPatientWeight.resetError()
        }

        edMedAmount.onTextChange {
            edMedAmount.resetError()
        }

        edPerVolume.onTextChange {
            edPerVolume.resetError()
        }
    }

    private fun validateForm(): Boolean = with(binding) {
        if (edDosage.TxT().isEmpty()) {
            edDosage.error = "Enter Dosage"
            edDosage.setError()
            binding.nestedScroll.scrollTo(0, binding.edDosage.top)
            false
        } else if (edPatientWeight.TxT().isEmpty()) {
            edPatientWeight.error = "Enter patient weight"
            edPatientWeight.setError()
            binding.nestedScroll.scrollTo(0, binding.edPatientWeight.top)
            false
        } else if (edMedAmount.TxT().isEmpty()) {
            edMedAmount.error = "Enter Med Amount"
            edMedAmount.setError()
            binding.nestedScroll.scrollTo(0, binding.edMedAmount.top)
            false
        }else if (edPerVolume.TxT().isEmpty()) {
            edPerVolume.error = "Enter Per Volume"
            edPerVolume.setError()
            binding.nestedScroll.scrollTo(0, binding.edPerVolume.top)
            false
        } else {
            true
        }
    }

    override fun onBackPressed() {
        finish()
    }

}