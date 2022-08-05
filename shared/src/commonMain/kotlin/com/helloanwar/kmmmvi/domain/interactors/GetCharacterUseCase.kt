package com.helloanwar.kmmmvi.domain.interactors

import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.type.UseCaseInOut
import com.helloanwar.kmmmvi.domain.model.Character

class GetCharacterUseCase(
    private val repository: IRepository,
    override val block: suspend (param: Int) -> Character = { repository.getCharacter(it) }
) : UseCaseInOut<Int, Character>()