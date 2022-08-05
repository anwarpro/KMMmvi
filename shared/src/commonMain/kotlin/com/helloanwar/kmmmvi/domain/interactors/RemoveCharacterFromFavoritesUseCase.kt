package com.helloanwar.kmmmvi.domain.interactors

import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.type.UseCaseIn

class RemoveCharacterFromFavoritesUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Int) -> Unit = { repository.removeCharacterFromFavorite(it) }
) : UseCaseIn<Int>()