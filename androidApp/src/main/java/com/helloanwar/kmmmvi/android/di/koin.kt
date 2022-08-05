package com.helloanwar.kmmmvi.android.di

import com.helloanwar.kmmmvi.features.characters.mvi.CharactersViewModel
import com.helloanwar.kmmmvi.features.detail.mvi.CharacterDetailViewModel
import com.helloanwar.kmmmvi.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { CharactersViewModel() }
    single { CharactersFavoritesViewModel() }
    single { CharacterDetailViewModel() }
}