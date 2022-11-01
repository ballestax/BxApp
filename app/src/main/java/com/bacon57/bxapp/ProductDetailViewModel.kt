package com.bacon57.bxapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bacon57.bxapp.model.Product
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel(
    private val repository: ProductsRepository,
    private val id:Long
) :
    ViewModel() {

    val product = MutableLiveData<Product>()

    val errorMessage = MutableLiveData<String>()

    fun onCreate() {
        viewModelScope.launch {
            Log.d("getProductById", "Launching modelscope")
            getProductById(id)
        }
    }

    fun getProductById(id: Long) {
        val response = repository.getProductById(id)
        Log.d("Prueba", "$response")
        response.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Log.d("getProductById", "$response")
                product.postValue(response.body())
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("error", "${t.message}")
                errorMessage.postValue(t.message)
            }
        }
        )
    }


}