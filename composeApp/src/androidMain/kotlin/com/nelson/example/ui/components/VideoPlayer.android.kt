package com.nelson.example.ui.components

import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {

    AndroidView(modifier = modifier, factory = { context ->
        val videoView = VideoView(context)
        videoView.apply {
            //Este video path es para videos en la web pero para videos internos es videoUri
            setVideoPath(url)
            setMediaController(android.widget.MediaController(context).apply {
                setAnchorView(videoView)
            })

            start()
        }
    })
}