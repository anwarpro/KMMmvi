package com.helloanwar.kmmmvi.repository

import com.helloanwar.kmmmvi.data_remote.model.ApiCharacter
import com.helloanwar.kmmmvi.data_remote.model.ApiCharactersResponse

interface IRemoteData {
    suspend fun getCharactersFromApi(): ApiCharactersResponse
    suspend fun getCharacterFromApi(id: Int): ApiCharacter
}