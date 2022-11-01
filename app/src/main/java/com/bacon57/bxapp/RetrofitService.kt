package com.bacon57.bxapp

import com.bacon57.bxapp.model.Category
import com.bacon57.bxapp.model.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("products")
    fun getAllProducts() : Call<List<Product>>

    @GET("products/{id}")
    fun getProductById( @Path("id") id:Long) : Call<Product>

    @GET("categories")
    fun getAllCategories() : Call<List<Category>>

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