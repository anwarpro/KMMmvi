package com.helloanwar.kmmmvi.features.favorites.mvi

import com.helloanwar.kmmmvi.base.mvi.BaseViewModel
import com.helloanwar.kmmmvi.base.mvi.BasicUiState
import com.helloanwar.kmmmvi.base.mvi.UiEffect
import com.helloanwar.kmmmvi.domain.interactors.GetCharactersFavoritesUseCase
import org.koin.core.component.inject

open class CharactersFavoritesViewModel :
    BaseViewModel<CharactersFavoritesContract.Event, CharactersFavoritesContract.State, UiEffect>() {
    private val getCharactersFavoritesUseCase: GetCharactersFavoritesUseCase by inject()

    init {
        getCharactersFavorites()
    }

    override fun createInitialState(): CharactersFavoritesContract.State =
        CharactersFavoritesContract.State(
            charactersFavorites = BasicUiState.Idle
        )

    override fun handleEvent(event: CharactersFavoritesContract.Event) {
        when (event) {
            CharactersFavoritesContract.Event.OnGetCharactersFavorites -> getCharactersFavorites()
        }
    }

    private fun getCharactersFavorites() {
        setState { copy(charactersFavorites = BasicUiState.Loading) }
        launch(getCharactersFavoritesUseCase(), { favorites ->
            setState {
                copy(
                    charactersFavorites =
                    if (favorites.isEmpty())
                        BasicUiState.Empty
                    else
                        BasicUiState.Success(favorites)
                )
            }
        }, {
            setState { copy(charactersFavorites = BasicUiState.Error()) }
        })
    }
}