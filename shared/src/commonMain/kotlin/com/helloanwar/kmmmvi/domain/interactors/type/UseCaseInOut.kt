package com.helloanwar.kmmmvi.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseInOut<IN, OUT> {
    operator fun invoke(param: IN): Flow<OUT> = flow { emit(block(param)) }
    protected abstract val block: suspend (param: IN) -> OUT
}