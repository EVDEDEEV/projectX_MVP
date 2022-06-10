package com.example.projectx.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("insert.php")
    suspend fun insert(
        @Field("number") number: String,
        @Field("type") type: String,
        @Field("duration") duration: String,
        @Field("date") date: String,
    ): Unit
//    ): Any

    companion object {
        private const val BASE_URL = "http://csgobrhl.beget.tech/"

        fun newInstance(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

}