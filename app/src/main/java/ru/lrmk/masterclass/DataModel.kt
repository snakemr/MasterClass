package ru.lrmk.masterclass

import java.util.*

class Movie(
    val id: Int = 0,
    val name: String = "",
    val vote_average: Float = 0f,
    val overview: String = "",
    val poster_path: String = "",
    val backdrop_path: String = "",
    val release_date: Date = Date(),
    val video: String? = null
)
