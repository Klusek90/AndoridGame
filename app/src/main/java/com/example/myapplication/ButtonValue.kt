package com.example.myapplication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ButtonValue(
    var status : Boolean,
    var player: String,
    var sequence : Int
)