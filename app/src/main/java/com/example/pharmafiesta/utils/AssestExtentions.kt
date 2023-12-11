package com.example.pharmafiesta.utils

import android.content.res.AssetManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.pharmafiesta.R

fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun EditText.onTextChange(text: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            text(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun View.setError() {
    this.setBackgroundResource(R.drawable.bg_error)
}

fun View.resetError() {
    this.setBackgroundResource(R.drawable.drawable_corner_edittext_purple)
}

fun EditText.TxT(): String {
    return this.text.toString()
}