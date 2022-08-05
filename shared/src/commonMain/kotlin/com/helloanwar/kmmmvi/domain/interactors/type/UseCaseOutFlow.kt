package com.helloanwar.kmmmvi.domain.interactors.type

import kotlinx.coroutines.flow.Flow

abstract class UseCaseOutFlow<OUT> {
    abstract operator fun invoke(): Flow<OUT>
}