package com.helloanwar.kmmmvi.android.ui.features.features

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.helloanwar.kmmmvi.android.ui.components.ArrowBackIcon

@Composable
fun ActionBar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Characters Favorites") },
        navigationIcon = { ArrowBackIcon(onBackPressed) }
    )
}