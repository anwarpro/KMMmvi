package com.helloanwar.kmmmvi.domain.interactors

import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.type.UseCaseOut
import com.helloanwar.kmmmvi.domain.model.Character

class GetCharactersUseCase(
    private val repository: IRepository,
    override val block: suspend () -> List<Character> = { repository.getCharacters() }
) : UseCaseOut<List<Character>>()