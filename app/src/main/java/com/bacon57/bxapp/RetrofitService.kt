package com.bacon57.bxapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitService {

    @GET("products")
    fun getlAllProducts() : Call<List<Product>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService{
            if(retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://baconapi-heroku.herokuapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}