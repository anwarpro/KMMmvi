package com.helloanwar.kmmmvi.android.ui.features.characters

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import com.helloanwar.kmmmvi.android.ui.components.ActionBarIcon

@Composable
fun ActionBar(
    actionFavorite: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "KmmMvi") },
        actions = {
            ActionBarIcon(
                onClick = actionFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}