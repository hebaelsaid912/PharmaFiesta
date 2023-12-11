package com.example.pharmafiesta.ui.home.calculator.activityDoseAdjustment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.pharmafiesta.databinding.ActivityDoseAdjustmentBinding
import com.example.pharmafiesta.utils.TxT
import com.example.pharmafiesta.utils.onTextChange
import com.example.pharmafiesta.utils.resetError
import com.example.pharmafiesta.utils.setError

class ActivityDoseAdjustment : AppCompatActivity() {

    lateinit var binding: ActivityDoseAdjustmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoseAdjustmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        click()

    }

    @SuppressLint("SetTextI18n")
    private fun click() {
        binding.btnCalc.setOnClickListener {
            if (validateForm()) {

                val bsa = calcBsa(
                    binding.edPatientHeight.TxT().toDouble(),
                    binding.edPatientWeight.TxT().toDouble()
                )
                val adjustDose = calcAdjustDose(binding.edStandardDose.TxT().toDouble(),bsa)

                binding.layoutBsa.isVisible = true
                binding.layoutDose.isVisible = true

                binding.tvBSAResult.text = "th result of BSA is = $bsa m²"
                binding.tvAdjustedDoseResult.text = "th result of Adjusted Dose is = $adjustDose mg"

            }
        }

        binding.btnClear.setOnClickListener {
            binding.edPatientHeight.setText("")
            binding.edPatientWeight.setText("")
            binding.edStandardDose.setText("")
            binding.layoutDose.isVisible = false
            binding.layoutBsa.isVisible = false
        }
    }

    private fun calcBsa(height_cm: Double, weight_kg: Double): Double {
        val bsa = 0.007184 * (height_cm * 0.725) * (weight_kg * 0.425)
        return bsa
    }

    private fun calcAdjustDose(standard_dose:Double,bsa: Double): Double {
        val adjusted_dose = (standard_dose * bsa) / 1.73
        return adjusted_dose
    }

    private fun initialize() = with(binding) {
        edPatientHeight.onTextChange {
            edPatientHeight.resetError()
        }

        edPatientWeight.onTextChange {
            edPatientWeight.resetError()
        }

        edStandardDose.onTextChange {
            edStandardDose.resetError()
        }
    }

    private fun validateForm(): Boolean = with(binding) {
        if (edPatientHeight.TxT().isEmpty()) {
            edPatientHeight.error = "Enter patient height"
            edPatientHeight.setError()
            binding.nestedScroll.scrollTo(0, binding.edPatientHeight.top)
            false
        } else if (edPatientWeight.TxT().isEmpty()) {
            edPatientWeight.error = "Enter patient weight"
            edPatientWeight.setError()
            binding.nestedScroll.scrollTo(0, binding.edPatientWeight.top)
            false
        } else if (edStandardDose.TxT().isEmpty()) {
            edStandardDose.error = "Enter Standard Dose"
            edStandardDose.setError()
            binding.nestedScroll.scrollTo(0, binding.edStandardDose.top)
            false
        } else {
            true
        }
    }

}