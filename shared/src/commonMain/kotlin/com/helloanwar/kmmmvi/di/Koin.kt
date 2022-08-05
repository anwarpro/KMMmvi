package com.helloanwar.kmmmvi.di

import com.helloanwar.kmmmvi.data_cache.CacheDataImp
import com.helloanwar.kmmmvi.data_remote.RemoteDataImp
import com.helloanwar.kmmmvi.domain.IRepository
import com.helloanwar.kmmmvi.domain.interactors.*
import com.helloanwar.kmmmvi.repository.ICacheData
import com.helloanwar.kmmmvi.repository.IRemoteData
import com.helloanwar.kmmmvi.repository.RepositoryImp
import com.helloanwar.kmmmvi.repository.model.mapper.ApiCharacterMapper
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            mapperModule,
            dispatcherModule,
            useCasesModule,
            platformModule()
        )
    }

// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get(), get(), get()) }
    single<ICacheData> { CacheDataImp(get()) }
    single<IRemoteData> { RemoteDataImp(get(), get()) }

    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single { "https://rickandmortyapi.com/" }
}

val useCasesModule: Module = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharactersFavoritesUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    factory { AddCharacterToFavoritesUseCase(get()) }
    factory { RemoveCharacterFromFavoritesUseCase(get()) }
    factory { IsCharacterFavoriteUseCase(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val mapperModule = module {
    factory { ApiCharacterMapper() }
}