package com.nelson.example.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nelson.example.data.database.RickMortyDatabase
import com.nelson.example.data.database.entity.CharacterOfTheDayEntity
import com.nelson.example.data.remote.pagin.CharactersPagingSource
import com.nelson.example.data.remote.pagin.EpisodesPaginSource
import com.nelson.example.domain.Repository
import com.nelson.example.domain.model.CharacterModel
import com.nelson.example.domain.model.CharacterOfTheDayModel
import com.nelson.example.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

//Inyecto el Api Services para utilizarlo, y la paginacion
class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val rickMortyDatabase: RickMortyDatabase,
    private val episodesPaginSource: EpisodesPaginSource): Repository {

    companion object{
        //cada vez que el llamo al Api me regresa 20 valores pero ese valor no esta en el resul
        // o JSON que me regresa por ende queda hardcode pero no deberia ser el caso
        const val MAX_ITEMS = 20

        //Este indicara que necesitamos nuevos elementos en la UI y cuando falten 5 elementos para
        //terminar la lista me llamara de nuevo a la Api con mas elementos
        const val PREFETCH_ITEMS = 5
    }
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { charactersPagingSource }).flow
    }

    override suspend fun getCharacterDB(): CharacterOfTheDayModel? {
        return rickMortyDatabase.getPreferencesDao().getCharacterOFTheDayBD()?.toDomain()
    }

    override suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel) {
        rickMortyDatabase.getPreferencesDao().saveCharacter(characterOfTheDayModel.toEntity())
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = {episodesPaginSource}).flow
    }

    override suspend fun getEpisodesForCharacter(episode: List<String>): List<EpisodeModel> {
        //Esto es en caso que el Api no me retorne episodios para ese caracter
        if (episode.isEmpty()) return emptyList()
        //Esta validacion lo hacemos ya que algunos caracteres solo aparecen en un solo episodio
        // y al invocar este API me regresa un solo objeto no un listado de objetos y la aplicacion
        // falla ya que me regresa un dato inesperado
        return if (episode.size >1){

            //Convierte all a un string y despues pasando por parametro una coma
             api.getEpisodes(episode.joinToString(",")).map { episodeResponse ->
                episodeResponse.toDomain()
            }

        }else{
            //No necesito hacer un Map ya que es un unico item
            listOf( api.getSingleEpisode(episode.first()).toDomain())
        }
    }
}