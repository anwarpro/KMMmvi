package com.helloanwar.kmmmvi.features.favorites.mvi

import com.helloanwar.kmmmvi.base.mvi.BasicUiState
import com.helloanwar.kmmmvi.base.mvi.UiEvent
import com.helloanwar.kmmmvi.base.mvi.UiState
import com.helloanwar.kmmmvi.domain.model.Character

interface CharactersFavoritesContract {
    sealed class Event : UiEvent {
        object OnGetCharactersFavorites : Event()
    }

    data class State(
        val charactersFavorites: BasicUiState<List<Character>>,
    ) : UiState
}