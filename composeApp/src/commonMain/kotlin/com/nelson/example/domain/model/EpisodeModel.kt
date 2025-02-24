package com.nelson.example.domain.model

data class EpisodeModel(
    val id: Int,
    val name: String,
    val episode: String,
    val characters:List<String>,
    //EStos dos objetos no los trae el Api por ende los vamos a incluir
    val videoURL:String,
    val season:SeasonEpisode
)

enum class SeasonEpisode{
    SEASON_1,
    SEASON_2,
    SEASON_3,
    SEASON_4,
    SEASON_5,
    SEASON_6,
    SEASON_7,
    UNKNOWN
}