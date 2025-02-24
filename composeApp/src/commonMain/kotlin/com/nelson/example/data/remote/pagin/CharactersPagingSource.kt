package com.nelson.example.data.remote.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nelson.example.data.ApiService
import com.nelson.example.domain.model.CharacterModel
import io.ktor.utils.io.errors.IOException

//Añadimos el Api services para manejar el tema de la paginacion
class CharactersPagingSource(private val api: ApiService): PagingSource<Int,CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllCharacters(page)//esto me regresa un wraper que no necesito
            val character = response.results//esto me regresa el resultado que necesito

            val pre = if (page > 0) -1 else null //pagina anterior
            val next = if (response.info.next!=null) page +1 else null // siguiente pagina

            //Esto ya maneja la paginacion
            LoadResult.Page(
                data = character.map { characterResponse -> characterResponse.toDomain() },
                prevKey = pre,
                nextKey = next
            )
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }

    }
}