package com.nelson.example.di

import com.nelson.example.data.ApiService
import com.nelson.example.data.RepositoryImpl
import com.nelson.example.data.remote.pagin.CharactersPagingSource
import com.nelson.example.data.remote.pagin.EpisodesPaginSource
import com.nelson.example.domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    //Esta funcion significa que quiero una unica instancia, cuando se llame esta funcion
    // solo creara un obejto y cuando se llame nuevamente se recuperara el antiguo objeto
    single {
        HttpClient{
            install(ContentNegotiation){
                //Me permite ignorar elementos que yo desconozca que esten dentro del JSON
                json(json = Json { ignoreUnknownKeys = true}, contentType = ContentType.Any)
            }
            install(DefaultRequest){
                url{
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                    //En caso que necesite un Api Key
                    //parameters.append("key", "02928272662")
                }
            }
        }
    }
    factoryOf(::ApiService)
    factory <Repository>{RepositoryImpl(get(), get(), get(), get())}
    factoryOf(::CharactersPagingSource)
    factoryOf(::EpisodesPaginSource)
}