package com.helloanwar.kmmmvi.domain.interactors

import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.type.UseCaseOutFlow
import com.helloanwar.kmmmvi.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharactersFavoritesUseCase(
    private val repository: IRepository
) : UseCaseOutFlow<List<Character>>() {
    override fun invoke(): Flow<List<Character>> = repository.getCharactersFavorites()
}