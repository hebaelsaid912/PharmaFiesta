package com.example.pharmafiesta.domain.readfile

import com.example.pharmafiesta.data.local.model.Drug


data class DrugsUiModel(
   val id: String?="",
   val tradename: String?="",
   val activeingredient: String?="",
   val company: String?="",
   val group: String?="",
   val info: String?="",
   val form: String?="",
   val price: String?=""
){
   fun mapDrugToDrugEntity () : Drug =  Drug(
      id!!,tradename,activeingredient, group, company, info, form, price
   )
}