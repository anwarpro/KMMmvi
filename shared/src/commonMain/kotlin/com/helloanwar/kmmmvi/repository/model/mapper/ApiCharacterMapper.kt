package com.helloanwar.kmmmvi.repository.model.mapper

import com.helloanwar.kmmmvi.data_remote.model.ApiCharacter
import com.helloanwar.kmmmvi.domain.model.Character
import com.helloanwar.kmmmvi.domain.model.Gender
import com.helloanwar.kmmmvi.domain.model.Status
import com.helloanwar.kmmmvi.domain.model.map.Mapper

class ApiCharacterMapper : Mapper<ApiCharacter, Character>() {
    override fun map(model: ApiCharacter): Character = model.run {
        Character(
            id, name, when (status) {
                "Alive" -> Status.ALIVE
                "Dead" -> Status.DEAD
                else -> Status.UNKNOWN
            }, species, when (gender) {
                "Male" -> Gender.MALE
                "Female" -> Gender.FEMALE
                "Genderless" -> Gender.GENDERLESS
                else -> Gender.UNKNOWN
            }, origin.name, location.name, image
        )
    }

    override fun inverseMap(model: Character): ApiCharacter {
        TODO("Not yet implemented")
    }
}