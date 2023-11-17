package com.example.pharmafiesta.utils

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(private val context: Context) {
    companion object {
        private const val PREF_FILE_NAME = "user_preferences"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_PASSWORD = "password"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    // Save user information to SharedPreferences
    fun saveUserInformation(username: String, email: String, phone: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PHONE, phone)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    // Retrieve user information from SharedPreferences
    fun getUserInformation(): User {
        val username = sharedPreferences.getString(KEY_USERNAME, "") ?: ""
        val email = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
        val phone = sharedPreferences.getString(KEY_PHONE, "") ?: ""
        val password = sharedPreferences.getString(KEY_PASSWORD, "") ?: ""

        return User(username, email, phone, password)
    }
    fun getUserEmail(): String = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    fun getUserPassword(): String = sharedPreferences.getString(KEY_PASSWORD, "") ?: ""


    // Clear user information from SharedPreferences
    fun clearUserInformation() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}

data class User(val username: String, val email: String, val phone: String, val password: String)
