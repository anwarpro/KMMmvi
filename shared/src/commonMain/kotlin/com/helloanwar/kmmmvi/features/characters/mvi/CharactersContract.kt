package com.helloanwar.kmmmvi.features.characters.mvi

import com.helloanwar.kmmmvi.base.mvi.BasicUiState
import com.helloanwar.kmmmvi.base.mvi.UiEvent
import com.helloanwar.kmmmvi.base.mvi.UiState
import com.helloanwar.kmmmvi.domain.model.Character

interface CharactersContract {
    sealed class Event : UiEvent {
        object OnGetCharacters : Event()
    }

    data class State(
        val characters: BasicUiState<List<Character>>
    ) : UiState
}