package com.example.pharmafiesta.ui.home.calculator.activityIdealBody

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.pharmafiesta.databinding.ActivityIdealBodyBinding
import com.example.pharmafiesta.ui.home.calculator.selector.ListSelectorBottomSheet
import com.example.pharmafiesta.utils.TxT
import com.example.pharmafiesta.utils.onTextChange
import com.example.pharmafiesta.utils.resetError
import com.example.pharmafiesta.utils.setError

class ActivityIdealBody : AppCompatActivity() {

    lateinit var binding: ActivityIdealBodyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdealBodyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        click()

    }

    @SuppressLint("SetTextI18n")
    private fun click() {

        binding.edSex.setOnClickListener {
            val sex = ArrayList<String>()
            sex.add("Male")
            sex.add("Female")
            ListSelectorBottomSheet(sex, itemSelected = {
                binding.edSex.setText(it)
            }).show(supportFragmentManager, "selector")
        }

        binding.btnCalc.setOnClickListener {
            if (validateForm()) {

                val idealBodyWeight = calcBodyWeight(
                    binding.edPatientHeight.TxT().toDouble(),
                    binding.edSex.TxT()
                )
                val doseWeight = calcDoseWeight(idealBodyWeight,binding.edPatientWeight.TxT().toDouble())

                binding.layoutBodyWeight.isVisible = true
                binding.layoutDosingWeight.isVisible = true

                binding.tvIdealBodyWeightResult.text = "th result of Ideal Body Weight is = $idealBodyWeight Kg"
                binding.tvDosingWeightResult.text = "th result of Dosing Weight is = $doseWeight Kg"

            }
        }

        binding.btnClear.setOnClickListener {
            binding.edPatientHeight.setText("")
            binding.edPatientWeight.setText("")
            binding.edSex.setText("")
            binding.layoutBodyWeight.isVisible = false
            binding.layoutDosingWeight.isVisible = false
        }
    }

    private fun calcBodyWeight(height: Double, sex: String): Double {
        val ideal_body_weight = if (sex == "Male") {
            50 + 2.3 * (height - 60)
        } else {
            45.5 + 2.3 * (height - 60)
        }
        return ideal_body_weight
    }

    private fun calcDoseWeight(ideal_body_weight: Double, actual_weight: Double): Double {
       val dosing_weight = ideal_body_weight + 0.4 * (actual_weight - ideal_body_weight)
        return dosing_weight
    }

    private fun initialize() = with(binding) {
        edPatientHeight.onTextChange {
            edPatientHeight.resetError()
        }

        edPatientWeight.onTextChange {
            edPatientWeight.resetError()
        }

        edSex.onTextChange {
            edSex.resetError()
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
        } else if (edSex.TxT().isEmpty()) {
            edSex.error = "Enter patient sex"
            edSex.setError()
            binding.nestedScroll.scrollTo(0, binding.edSex.top)
            false
        } else {
            true
        }
    }

}