package com.helloanwar.kmmmvi.domain.interactors

import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.type.UseCaseInOut

class IsCharacterFavoriteUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Int) -> Boolean = { repository.isCharacterFavorite(it) }
) : UseCaseInOut<Int, Boolean>()