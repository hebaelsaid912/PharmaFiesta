package com.example.pharmafiesta.ui.home.homescreen.druginfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pharmafiesta.data.local.model.Drug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DrugInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _validateState = MutableStateFlow(String)
    val validateState = _validateState.asStateFlow()

    private val _stateHandle = MutableStateFlow(Drug())
    val stateHandle = _stateHandle.asStateFlow()
    private val args = DrugInfoArgs(savedStateHandle)

    init {
        getVerifyCodeArgs()
    }

    private fun getVerifyCodeArgs() {
        _stateHandle.update {
            it.copy(
                id = "",
                tradeName = "${args.tradeName}",
                activeIngredient = "${args.activeIngredient}",
                group = "${args.group}",
                company = "${args.company}",
                info = "${args.info}",
                form = "${args.form}",
                price = "${args.price}",
            )
        }
    }

}
