package com.example.pharmafiesta.ui.home.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pharmafiesta.databinding.ActivityCalculatorsBinding
import com.example.pharmafiesta.ui.home.BottomNavigationActivity
import com.example.pharmafiesta.ui.home.calculator.activityDoseAdjustment.ActivityDoseAdjustment
import com.example.pharmafiesta.ui.home.calculator.activityIdealBody.ActivityIdealBody
import com.example.pharmafiesta.ui.home.calculator.adapter.AdapterCalculator
import com.example.pharmafiesta.ui.home.calculator.singleDose.ActivitySingleDose
import com.example.pharmafiesta.ui.home.calculator.weightDose.ActivityWeightDose

class ActivityCalculators : AppCompatActivity() {

    lateinit var binding: ActivityCalculatorsBinding

    private val adapterCalculator by lazy { AdapterCalculator(onAction = {

        when(it){
            0->{
                val intent = Intent(this,ActivityDoseAdjustment::class.java)
                startActivity(intent)
            }
            1->{
                val intent = Intent(this,ActivityIdealBody::class.java)
                startActivity(intent)
            }
            2->{
                val intent = Intent(this,ActivitySingleDose::class.java)
                startActivity(intent)
            }
            3->{
                val intent = Intent(this,ActivityWeightDose::class.java)
                startActivity(intent)
            }
        }


    }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        click()
        fillCalculators()

    }

    private fun click() {
        binding.imgBack.setOnClickListener {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun fillCalculators() {
        val calculators = ArrayList<String>()
        calculators.add("Dose Adjustment for Body Surface Area")
        calculators.add("Ideal Body Weight and Dosing Weight")
        calculators.add("Single Dose-Volume Conversion calculator")
        calculators.add("Weight Based Dosage Calculator")

        binding.rvCalculators.adapter = adapterCalculator
        adapterCalculator.submitList(calculators)
    }

    override fun onBackPressed() {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
        finish()
    }
}