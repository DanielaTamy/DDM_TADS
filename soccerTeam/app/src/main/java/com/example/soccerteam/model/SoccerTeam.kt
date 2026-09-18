package com.example.soccerteam.model

import java.io.Serializable

data class SoccerTeam(
    val name: String,
    val city: String,
    val state: String,
    val imageResId: Int
) : Serializable