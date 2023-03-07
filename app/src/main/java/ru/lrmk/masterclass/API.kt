package ru.lrmk.masterclass

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

val api = API.getAPI()

interface API {
    @GET(".")
    suspend fun movies(): List<Movie>

    companion object {
        private const val base = "https://mad.lrmk.ru/media/"
        const val small = base + "small/"
        const val big = base + "image/"
        const val video = base + "video/"
        fun getAPI(): API = Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(API::class.java)
    }
}