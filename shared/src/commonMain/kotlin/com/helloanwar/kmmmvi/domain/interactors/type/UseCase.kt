package com.helloanwar.kmmmvi.domain.interactors.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCase {
    operator fun invoke(): Flow<Unit> = flow { emit(block()) }
    protected abstract val block: suspend () -> Unit
}