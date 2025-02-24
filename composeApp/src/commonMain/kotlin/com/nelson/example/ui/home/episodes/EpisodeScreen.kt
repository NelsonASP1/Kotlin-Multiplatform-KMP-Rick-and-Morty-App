package com.nelson.example.ui.home.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.nelson.example.domain.model.EpisodeModel
import com.nelson.example.domain.model.SeasonEpisode
import com.nelson.example.ui.components.PaginLoadingState
import com.nelson.example.ui.components.PaginWrapper
import com.nelson.example.ui.components.PagingType
import com.nelson.example.ui.components.VideoPlayer
import com.nelson.example.ui.core.BackgroundPrimaryColor
import com.nelson.example.ui.core.DefaultTextColor
import example.composeapp.generated.resources.Res
import example.composeapp.generated.resources.close
import example.composeapp.generated.resources.compose_multiplatform
import example.composeapp.generated.resources.rickface
import example.composeapp.generated.resources.season1
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodeScreen(){
    //declaramos nuestro viewModel
    val episodesViewModel = koinViewModel<EpisodesViewModel>()
    //Obtenemos el estado
    val state by episodesViewModel.state.collectAsState()

    val episodes = state.characters.collectAsLazyPagingItems()
    Column(Modifier.fillMaxSize().background(BackgroundPrimaryColor)){
        Spacer(Modifier.height(16.dp))
        PaginWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = { PaginLoadingState() },
            emptyView = {},
            itemView = {EpisodeItemList(it) {url -> episodesViewModel.onPlaySelected(url)} })
        EpisodePlayer(state.playVideo){episodesViewModel.onCloseVideo()}
    }
    //val name = name()
    //Text("Hola $name")
}

expect fun name():String

@Composable
fun EpisodePlayer(playVideo:String, onCloseVideo:() -> Unit){
    //Si el estado no esta vacio y no tiene espacios me reproduce el video
    AnimatedContent (playVideo.isNotBlank()){ condition ->
        if (condition){

            ElevatedCard(modifier = Modifier.fillMaxWidth().height(250.dp).padding(16.dp)
                .border(3.dp, Color.Green, CardDefaults.elevatedShape)
            ) {
                Box(modifier = Modifier.background(Color.Black)) {
                    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                        VideoPlayer(Modifier.fillMaxWidth().height(200.dp), playVideo)
                    }
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(Res.drawable.close),
                            "",
                            modifier = Modifier.padding(8.dp).size(40.dp).clickable { onCloseVideo() }
                        )
                    }
                }
            }

        }else{
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().height(250.dp).padding(16.dp)
            ){
                Image(painter = painterResource(Res.drawable.rickface), null)
                Text("Click de Video")
            }

        }
    }
}

@Composable
fun EpisodeItemList(episode:EpisodeModel, onEpisodeSelected:(String) -> Unit){

    Column(modifier = Modifier.width(120.dp)
        .padding(horizontal = 8.dp)
        .clickable {onEpisodeSelected(episode.videoURL)}){
        Image(
            modifier = Modifier.height(180.dp).fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(getSeasonImage(episode.season))
        )
        Spacer(Modifier.height(2.dp))
        Text(episode.episode, color = DefaultTextColor, fontWeight = FontWeight.Bold)
    }
}

//El dominio no bebe conocer la parte operativa de android por eso esta funcion no se coloco en el dominio
fun getSeasonImage(seasonEpisode: SeasonEpisode):DrawableResource{
    return when(seasonEpisode){
        SeasonEpisode.SEASON_1 -> Res.drawable.season1
        SeasonEpisode.SEASON_2 -> Res.drawable.season1
        SeasonEpisode.SEASON_3 -> Res.drawable.season1
        SeasonEpisode.SEASON_4 -> Res.drawable.season1
        SeasonEpisode.SEASON_5 -> Res.drawable.season1
        SeasonEpisode.SEASON_6 -> Res.drawable.season1
        SeasonEpisode.SEASON_7 -> Res.drawable.season1
        SeasonEpisode.UNKNOWN ->  Res.drawable.season1
    }
}