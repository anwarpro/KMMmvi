package com.helloanwar.kmmmvi.di

import com.helloanwar.kmmmvi.data_cache.sqldelight.DatabaseDriverFactory
import com.helloanwar.kmmmvi.domain.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(get()) }
    single { MainDispatcher() }
}