package com.example.pharmafiesta.ui.home.calculator.singleDose

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.pharmafiesta.databinding.ActivitySingleDoseBinding
import com.example.pharmafiesta.ui.home.calculator.selector.ListSelectorBottomSheet
import com.example.pharmafiesta.utils.TxT
import com.example.pharmafiesta.utils.onTextChange
import com.example.pharmafiesta.utils.resetError
import com.example.pharmafiesta.utils.setError

class ActivitySingleDose : AppCompatActivity() {

    lateinit var binding:ActivitySingleDoseBinding
    var doseUnitVlaue = 0.0
    var drugUnitVlaue = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleDoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        click()

    }

    @SuppressLint("SetTextI18n")
    private fun click() {

        binding.edDoseUnit.setOnClickListener {
            if(binding.edDose.TxT().isEmpty()){
                Toast.makeText(this,"Enter Dose Value Firstly",Toast.LENGTH_LONG).show()
            }else {
                ListSelectorBottomSheet(returnListUnits(), itemSelected = {
                    doseUnitVlaue = 0.0
                    doseUnitVlaue = returnUnitValue(it)
                    binding.edDoseUnit.setText(it)
                }).show(supportFragmentManager, "selector")
            }
        }

        binding.edDrugUnit.setOnClickListener {
            if(binding.edDrugAmount.TxT().isEmpty()){
                Toast.makeText(this,"Enter Drug Amount Value Firstly",Toast.LENGTH_LONG).show()
            }else {
                ListSelectorBottomSheet(returnListUnits(), itemSelected = {
                    drugUnitVlaue = 0.0
                    drugUnitVlaue = returnUnitValue(it)
                    binding.edDrugUnit.setText(it)
                }).show(supportFragmentManager, "selector")
            }
        }

        binding.btnCalc.setOnClickListener {
            if (validateForm()) {

                val dose_to_mg = calcDoseToMg(
                    binding.edDose.TxT().toDouble(),
                    doseUnitVlaue
                )

                val drug_to_mg = calcDrugToMg(
                    binding.edDrugAmount.TxT().toDouble(),
                    drugUnitVlaue
                )

                val doseInMl = calcDoseInMl(dose_to_mg,drug_to_mg,binding.edPer.TxT().toDouble())

                binding.layoutDoseMl.isVisible = true

                val formatteddoseInMl= String.format("%.2f", doseInMl)

                binding.tvDoseInMlResult.text = "th result of Dose in Ml is = $formatteddoseInMl Ml"

            }
        }

        binding.btnClear.setOnClickListener {
            binding.edDose.setText("")
            binding.edDoseUnit.setText("")
            binding.edDrugAmount.setText("")
            binding.edDrugUnit.setText("")
            binding.edPer.setText("")
            binding.layoutDoseMl.isVisible = false
        }

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun returnUnitValue(unit:String):Double{
        val unitValue = when (unit) {
            "g" -> {
                1000.0
            }
            "mcg" -> {
                0.001
            }
            "mEq" -> {
                0.001
            }
            else -> {
                1.0
            }
        }
        return unitValue
    }

    private fun returnListUnits():ArrayList<String>{
        val units = ArrayList<String>()
        units.add("g")
        units.add("mcg")
        units.add("mEq")
        units.add("mg")
        units.add("mmol")
        units.add("units")
        return units
    }
    private fun calcDoseToMg(dose: Double, unitValue: Double): Double {
        val dose_mg = dose * unitValue
        return dose_mg
    }

    private fun calcDrugToMg(drug: Double, unitValue: Double): Double {
        val drug_amount_mg = drug * unitValue
        return drug_amount_mg
    }

    private fun calcDoseInMl(dose: Double, drug: Double, per: Double): Double {
        val dose_in_ml = dose / (drug / per)
        return dose_in_ml
    }

    private fun initialize() = with(binding) {
        edDose.onTextChange {
            edDose.resetError()
        }

        edDoseUnit.onTextChange {
            edDoseUnit.resetError()
        }

        edDrugAmount.onTextChange {
            edDrugAmount.resetError()
        }

        edDrugUnit.onTextChange {
            edDrugUnit.resetError()
        }

        edPer.onTextChange {
            edPer.resetError()
        }
    }

    private fun validateForm(): Boolean = with(binding) {
        if (edDose.TxT().isEmpty()) {
            edDose.error = "Enter Dose"
            edDose.setError()
            binding.nestedScroll.scrollTo(0, binding.edDose.top)
            false
        } else if (edDoseUnit.TxT().isEmpty()) {
            edDoseUnit.error = "Enter Dose Unit"
            edDoseUnit.setError()
            binding.nestedScroll.scrollTo(0, binding.edDoseUnit.top)
            false
        } else if (edDrugAmount.TxT().isEmpty()) {
            edDrugAmount.error = "Enter Drug Amount"
            edDrugAmount.setError()
            binding.nestedScroll.scrollTo(0, binding.edDrugAmount.top)
            false
        }else if (edDrugUnit.TxT().isEmpty()) {
            edDrugUnit.error = "Enter Drug Unit"
            edDrugUnit.setError()
            binding.nestedScroll.scrollTo(0, binding.edDrugUnit.top)
            false
        }else if (edPer.TxT().isEmpty()) {
            edPer.error = "Enter Per"
            edPer.setError()
            binding.nestedScroll.scrollTo(0, binding.edPer.top)
            false
        } else {
            true
        }
    }

    override fun onBackPressed() {
        finish()
    }

}