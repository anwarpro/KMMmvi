package com.helloanwar.kmmmvi.data_remote

import com.helloanwar.kmmmvi.data_remote.model.ApiCharacter
import com.helloanwar.kmmmvi.data_remote.model.ApiCharactersResponse
import com.helloanwar.kmmmvi.repository.IRemoteData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RemoteDataImp(
    private val endPoint: String,
    private val httpClient: HttpClient
) : IRemoteData {
    override suspend fun getCharactersFromApi(): ApiCharactersResponse =
        httpClient.get { apiUrl("/api/character") }.body()

    override suspend fun getCharacterFromApi(id: Int): ApiCharacter =
        httpClient.get { apiUrl("/api/character/$id") }.body()

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}