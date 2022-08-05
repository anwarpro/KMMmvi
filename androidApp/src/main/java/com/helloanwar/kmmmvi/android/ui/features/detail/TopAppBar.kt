package com.helloanwar.kmmmvi.android.ui.features.detail

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import com.helloanwar.kmmmvi.android.ui.components.ActionBarIcon
import com.helloanwar.kmmmvi.android.ui.components.ArrowBackIcon


@Composable
fun ActionBar(
    name: String,
    isFavorite: Boolean,
    actionAddFavorite: () -> Unit,
    actionRemoveFavorite: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            if (isFavorite) {
                ActionBarIcon(
                    onClick = actionRemoveFavorite,
                    icon = Icons.Filled.Favorite
                )
            } else {
                ActionBarIcon(
                    onClick = actionAddFavorite,
                    icon = Icons.Filled.FavoriteBorder
                )
            }
        }
    )
}