package com.funnco.kotlinlearning1

import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("WeatherForecast")
    fun getWeather(): Call<List<Weather>>

    @GET("Hahaton")
    fun getUsers(): Call<List<User>>

    @FormUrlEncoded
    @POST("Hahaton")
    fun addUser(
        @Field("width") width: String,
        @Field("date") date: Long,
        @Field("name") name: String,
        @Field("age") age: Int,

    ):Call<List<User>>

    @POST("Hahaton")
    fun addUser(
        @Body user:User
        ):Call<Boolean>
}
