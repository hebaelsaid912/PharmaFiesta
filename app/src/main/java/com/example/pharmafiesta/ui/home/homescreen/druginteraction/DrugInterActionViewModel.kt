package com.example.pharmafiesta.ui.home.homescreen.druginteraction

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmafiesta.data.local.database.DrugsDatabase
import com.example.pharmafiesta.data.local.model.Drug
import com.example.pharmafiesta.domain.readfile.DrugsUiModel
import com.example.pharmafiesta.utils.readAssetsFile
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugInterActionViewModel @Inject constructor(val application: Application, private val drugsDatabase: DrugsDatabase): ViewModel() {

    private val _drugList = MutableStateFlow<List<Drug>>(emptyList())
     val drugList = _drugList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            insertDrugsListItemsIntoDB(generateMockList())
        }
    }

    private fun generateMockList(): List<DrugsUiModel> {
        val response = application.assets.readAssetsFile("Druge.json")
        return GsonBuilder().create().fromJson(response, Array<DrugsUiModel>::class.java).asList()
    }

    private suspend fun insertDrugsListItemsIntoDB(data: List<DrugsUiModel>) {
        clearDrugsTable()
        for (item in data) {
            /*Log.e(TAG , "name:: ${item.tradename}")
            Log.e(TAG , "activeIngredient:: ${item.activeingredient}")
            Log.e(TAG , "company:: ${item.company}")
            Log.e(TAG , "info:: ${item.info}")
            Log.e(TAG , "price:: ${item.price}")*/
            drugsDatabase.drugsDao().insertAll(
                item.mapDrugToDrugEntity()
            )
        }
    }

    fun searchDrugs(searchKeyword: String){
        viewModelScope.launch(Dispatchers.IO)  {
           val drugs =  searchDrugsListItemsIntoDB(searchKeyword = searchKeyword)
            Log.e("TAG", "drugs:: ${drugs.size}")
            _drugList.emit(drugs)
        }
    }

    private suspend fun searchDrugsListItemsIntoDB(searchKeyword: String): List<Drug> =
        drugsDatabase.drugsDao().searchDrugsBygroup(searchKeyword.trim())


    private suspend fun clearDrugsTable() {
        drugsDatabase.drugsDao().clearDrugsDatabase()
    }
}