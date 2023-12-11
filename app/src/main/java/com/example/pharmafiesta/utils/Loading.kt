package com.example.pharmafiesta.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import com.example.pharmafiesta.ui.theme.Green59

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { }) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Green59)
        }
    }
}
