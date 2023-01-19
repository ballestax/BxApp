package com.bacon57.bxapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bacon57.bxapp.model.Category
import com.bacon57.bxapp.model.Product
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel(
    private val repository: ProductsRepository,
    private val catRepository: CategoriesRepository,
) :
    ViewModel() {

    private val TAG = "ProductListViewModel"

    val productList = MutableLiveData<List<Product>>()
    val categoriesList = MutableLiveData<List<Category>>()

    val errorMessage = MutableLiveData<String>()

    fun onCreate() {
        viewModelScope.launch {
            getAllProducts()
            getAllCategories()
        }
    }

    fun onProductCLick(product: Product) {
        //Toast.makeText(this, "click on @{product.name}", Toast.LENGTH_LONG).show()
        println(":::click on @{product.name}")
    }

    fun getAllProducts() {
        val response = repository.getAllProducts()
        response.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.d(TAG, response.message())
                productList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                errorMessage.postValue(t.message)
            }
        }
        )
    }

    fun getAllCategories() {
        val response = catRepository.getAllCategories()
        response.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                Log.d(TAG, response.message())
                categoriesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                errorMessage.postValue(t.message)
            }
        }
        )
    }
}