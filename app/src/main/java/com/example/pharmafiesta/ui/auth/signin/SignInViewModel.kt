package com.example.pharmafiesta.ui.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmafiesta.ui.auth.signup.ErrorStates
import com.example.pharmafiesta.utils.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val userPreferences : UserPreferences):ViewModel() {
    val emailState = MutableStateFlow("")
    val passwordState = MutableStateFlow("")
    val errorState = MutableStateFlow<List<Pair<ErrorStates, Boolean>>>(listOf())
    fun signIn(): Pair<String,Boolean> {
        return if (emailState.value == userPreferences.getUserEmail() && passwordState.value == userPreferences.getUserPassword())
           Pair( "SignIn Successfully",true)
        else
            Pair("SignIn Failed",false)
    }

    fun isFormValid() {
        viewModelScope.launch {
            errorState.emit(checkFormValidation())
        }
    }

    fun checkFormValidation(): List<Pair<ErrorStates, Boolean>> {
        val errorStates = ArrayList<Pair<ErrorStates, Boolean>>()
        if (checkEmailValidation()) {
            errorStates.add(Pair(ErrorStates.EMAIL, true))
        }
        if (checkPasswordValidation()) {
            errorStates.add(Pair(ErrorStates.PASSWORD, true))
        }
        return errorStates
    }

    private fun checkPasswordValidation(): Boolean =
        passwordState.value.isEmpty() || passwordState.value.length < 8

    private fun checkEmailValidation(): Boolean = emailState.value.isEmpty()
}