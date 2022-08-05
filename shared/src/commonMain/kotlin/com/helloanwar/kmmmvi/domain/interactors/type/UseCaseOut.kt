package com.helloanwar.kmmmvi.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseOut<OUT> {
    operator fun invoke(): Flow<OUT> = flow { emit(block()) }
    protected abstract val block: suspend () -> OUT
}