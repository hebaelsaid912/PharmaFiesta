package com.example.pharmafiesta.utils

import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GifScreen(url: String) {

    val context = LocalContext.current

    val view = remember { ImageView(context) }

    Box(modifier = Modifier.height(200.dp)) {
        // Load Gif with Glide library
        DisposableEffect(context) {
            Glide.with(context)
                .asGif()
                .load(url)
                .into(view)
            onDispose {
                // Cleanup when the composable is disposed
                Glide.with(context).clear(view)
            }
        }

        // Wrap the ImageView with Compose's View composable
        AndroidView(factory = { view })

    }
}

