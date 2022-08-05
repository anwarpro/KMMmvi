package com.helloanwar.kmmmvi.domain.interactors

import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.type.UseCaseIn
import com.helloanwar.kmmmvi.domain.model.Character

class AddCharacterToFavoritesUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Character) -> Unit = { repository.addCharacterToFavorites(it) }
) : UseCaseIn<Character>()