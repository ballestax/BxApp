package com.bacon57.bxapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel(private val repository: ProductsRepository): ViewModel() {

    val productList = MutableLiveData<List<Product>>()

    val errorMessage = MutableLiveData<String>()

    fun getAllProducts(){
        val response = repository.getAllProducts()
        response.enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                productList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        }
        )
    }
}