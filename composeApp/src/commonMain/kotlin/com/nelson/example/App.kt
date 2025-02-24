package com.nelson.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nelson.example.ui.core.navigation.NavigationWrapper
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import example.composeapp.generated.resources.Res
import example.composeapp.generated.resources.compose_multiplatform
@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}