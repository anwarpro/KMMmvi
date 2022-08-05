package com.helloanwar.kmmmvi.repository

import com.helloanwar.kmmmvi.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICacheData {
    fun addCharacterToFavorite(character: Character)
    fun removeCharacterFromFavorite(idCharacter: Int)
    fun getAllCharacterFavorites(): Flow<List<Character>>
    fun isCharacterFavorite(idCharacter: Int): Boolean
}