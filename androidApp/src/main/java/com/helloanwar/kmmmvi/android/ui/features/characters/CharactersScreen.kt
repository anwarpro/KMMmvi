package com.helloanwar.kmmmvi.android.ui.features.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.helloanwar.kmmmvi.android.ui.components.CharacterItem
import com.helloanwar.kmmmvi.android.ui.components.state.ManagementResourceState
import com.helloanwar.kmmmvi.domain.model.Character
import com.helloanwar.kmmmvi.features.characters.mvi.CharactersContract
import com.helloanwar.kmmmvi.features.characters.mvi.CharactersViewModel

@Composable
fun CharactersScreen(
    onCharacterClick: (Int) -> Unit,
    navigateToFavorite: () -> Unit,
    viewModel: CharactersViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ActionBar(navigateToFavorite) }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.characters,
            successView = { characters ->
                checkNotNull(characters)
                CharactersList(
                    characters = characters,
                    onCharacterClick = onCharacterClick
                )
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { viewModel.setEvent(CharactersContract.Event.OnGetCharacters) },
            onCheckAgain = { viewModel.setEvent(CharactersContract.Event.OnGetCharacters) },
        )
    }
}

@Composable
fun CharactersList(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onClick = { onCharacterClick(character.id) }
            )
        }
    }
}