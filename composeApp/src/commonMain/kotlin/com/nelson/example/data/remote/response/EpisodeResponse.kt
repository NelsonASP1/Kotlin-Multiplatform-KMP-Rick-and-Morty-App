package com.nelson.example.data.remote.response

import com.nelson.example.domain.model.EpisodeModel
import com.nelson.example.domain.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse (
    val id: Int,
    val name: String,
    val episode: String,
    val characters:List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            //basicamente el me guardara los ultimos elemntos despues del ultimo eslash ( / )
            characters = characters.map { url -> url.substringAfterLast("/") },
            season = getSeasonFromEpisodeCode(episode),
            videoURL = getVideoUrlFromSeason(season)
        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
       return when(season){
            SeasonEpisode.SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
            SeasonEpisode.UNKNOWN ->  "https://firebasestorage.googleapis.com/v0/b/nelsonkmp-5034f.appspot.com/o/trailer.mp4?alt=media&token=ea0a1a06-a434-4c40-a86c-a8acfca72238"
        }
    }

    private fun getSeasonFromEpisodeCode(episode: String):SeasonEpisode{
        //Aqui creamos una funcion personalizada para regresar la Temporada o Season
        // apartir del campo que "episode" que nos llega del Api
        return when{
            episode.startsWith("SO1") -> SeasonEpisode.SEASON_1
            episode.startsWith("SO2") -> SeasonEpisode.SEASON_2
            episode.startsWith("SO3") -> SeasonEpisode.SEASON_3
            episode.startsWith("SO4") -> SeasonEpisode.SEASON_4
            episode.startsWith("SO5") -> SeasonEpisode.SEASON_5
            episode.startsWith("SO6") -> SeasonEpisode.SEASON_6
            episode.startsWith("SO7") -> SeasonEpisode.SEASON_7
            else -> SeasonEpisode.UNKNOWN
        }
    }
}