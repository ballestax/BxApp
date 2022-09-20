package com.bacon57.bxapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel(private val repository: ProductsRepository, private val catRepository: CategoriesRepository): ViewModel() {

    val productList = MutableLiveData<List<Product>>()
    val categoriesList = MutableLiveData<List<Category>>()

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

    fun getAllCategories(){
        val response = catRepository.getAllCategories()
        response.enqueue(object : Callback<List<Category>>{
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                categoriesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        }
        )
    }
}