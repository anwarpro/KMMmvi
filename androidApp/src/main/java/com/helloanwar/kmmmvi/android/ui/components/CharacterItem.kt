package com.helloanwar.kmmmvi.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.helloanwar.kmmmvi.domain.model.Character

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(
    character: Character,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp, bottom = 10.dp)
                .width(110.dp)
                .height(110.dp)
        )
        Text(
            text = character.name,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}