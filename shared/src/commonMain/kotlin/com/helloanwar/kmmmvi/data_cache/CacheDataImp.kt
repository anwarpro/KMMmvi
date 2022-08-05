package com.helloanwar.kmmmvi.data_cache

import com.helloanwar.kmmmvi.data_cache.sqldelight.DatabaseDriverFactory
import com.helloanwar.kmmmvi.datacache.sqldelight.CharacterFavorite
import com.helloanwar.kmmmvi.domain.model.Character
import com.helloanwar.kmmmvi.domain.model.Gender
import com.helloanwar.kmmmvi.domain.model.Status
import com.helloanwar.kmmmvi.repository.ICacheData
import com.helloanwar.kmmmvi.shared.data_cache.sqldelight.AppDatabase
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

class CacheDataImp(
    databaseDriverFactory: DatabaseDriverFactory
) : ICacheData {
    private val statusAdapter = object : ColumnAdapter<Status, String> {
        override fun decode(databaseValue: String): Status = when (databaseValue) {
            "Alive" -> Status.ALIVE
            "Dead" -> Status.DEAD
            else -> Status.UNKNOWN
        }

        override fun encode(value: Status): String = when (value) {
            Status.ALIVE -> "Alive"
            Status.DEAD -> "Dead"
            Status.UNKNOWN -> "Unknown"
        }
    }

    private val genderAdapter = object : ColumnAdapter<Gender, String> {
        override fun decode(databaseValue: String): Gender = when (databaseValue) {
            "Male" -> Gender.MALE
            "Female" -> Gender.FEMALE
            "Genderless" -> Gender.GENDERLESS
            else -> Gender.UNKNOWN
        }

        override fun encode(value: Gender): String = when (value) {
            Gender.MALE -> "Male"
            Gender.FEMALE -> "Female"
            Gender.GENDERLESS -> "Genderless"
            Gender.UNKNOWN -> "Unknown"
        }
    }

    private val database = AppDatabase.invoke(
        databaseDriverFactory.createDriver(),
        CharacterFavorite.Adapter(statusAdapter, genderAdapter)
    )
    private val dbQuery = database.appDatabaseQueries

    override fun addCharacterToFavorite(character: Character) {
        dbQuery.transaction {
            dbQuery.insertCharacterFavorite(
                character.id.toLong(),
                character.name,
                character.status,
                character.species,
                character.gender,
                character.origin,
                character.location,
                character.image
            )
        }
    }

    override fun removeCharacterFromFavorite(idCharacter: Int) {
        dbQuery.transaction {
            dbQuery.removeCharacterFavorite(idCharacter.toLong())
        }
    }

    override fun getAllCharacterFavorites(): Flow<List<Character>> =
        dbQuery.selectAllCharacterFavorite(::mapFavorite).asFlow().mapToList()

    override fun isCharacterFavorite(idCharacter: Int): Boolean =
        dbQuery.selectCharacterFavoriteById(idCharacter.toLong()).executeAsOne()

    private fun mapFavorite(
        id: Long,
        name: String,
        status: Status,
        species: String,
        gender: Gender,
        origin: String,
        location: String,
        image: String
    ): Character = Character(id.toInt(), name, status, species, gender, origin, location, image)
}