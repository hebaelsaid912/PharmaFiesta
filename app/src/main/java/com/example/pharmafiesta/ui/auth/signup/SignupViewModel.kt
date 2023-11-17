package com.example.pharmafiesta.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmafiesta.utils.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val userPreferences : UserPreferences):ViewModel() {
    val userNameState = MutableStateFlow("")
    val emailState = MutableStateFlow("")
    val phoneState = MutableStateFlow("")
    val passwordState = MutableStateFlow("")
    val errorState = MutableStateFlow <List<Pair<ErrorStates, Boolean>>>(listOf())
    fun saveSignUpData() {
        userPreferences.saveUserInformation(
            username = userNameState.value,
            email = emailState.value,
            phone = phoneState.value,
            password = passwordState.value
        )
    }

    fun isFormValid() {
        viewModelScope.launch {
            errorState.emit(checkFormValidation())
        }
    }

    fun checkFormValidation(): List<Pair<ErrorStates, Boolean>> {
        val errorStates = ArrayList<Pair<ErrorStates, Boolean>>()
        if (checkUserNameValidation()) {
            errorStates.add(Pair(ErrorStates.USER_NAME, true))
        }
        if (checkEmailValidation()) {
            errorStates.add(Pair(ErrorStates.EMAIL, true))
        }
        if (checkPhoneValidation()) {
            errorStates.add(Pair(ErrorStates.PHONE, true))
        }

        if (checkPasswordValidation()) {
            errorStates.add(Pair(ErrorStates.PASSWORD, true))
        }
        return errorStates
    }

    private fun checkUserNameValidation(): Boolean = userNameState.value.isEmpty()
    private fun checkPhoneValidation(): Boolean = phoneState.value.isEmpty()
    private fun checkPasswordValidation(): Boolean =
        passwordState.value.isEmpty() || passwordState.value.length < 8

    private fun checkEmailValidation(): Boolean = emailState.value.isEmpty()
}