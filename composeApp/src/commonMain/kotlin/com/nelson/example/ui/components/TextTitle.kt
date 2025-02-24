package com.nelson.example.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.nelson.example.ui.core.DefaultTextColor

@Composable
fun TextTitle(text:String){
    //Uppercase para que los titulos siempre esten en mayusculas
    Text(text.uppercase(), color = DefaultTextColor, fontWeight = FontWeight.Bold)
}