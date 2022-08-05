package com.helloanwar.kmmmvi.features.detail.mvi

import com.helloanwar.kmmmvi.base.mvi.BasicUiState
import com.helloanwar.kmmmvi.base.mvi.UiEffect
import com.helloanwar.kmmmvi.base.mvi.UiEvent
import com.helloanwar.kmmmvi.base.mvi.UiState
import com.helloanwar.kmmmvi.domain.model.Character

interface CharacterDetailContract {
    sealed class Event : UiEvent {
        data class GetCharacter(val idCharacter: Int) : Event()
        object AddCharacterToFavorite : Event()
        object RemoveCharacterToFavorite : Event()
        object Retry : Event()
    }

    data class State(
        val character: BasicUiState<Character>,
        val isFavorite: Boolean,
    ) : UiState

    sealed class Effect : UiEffect {
        object CharacterAdded : Effect()
        object CharacterRemoved : Effect()
    }
}