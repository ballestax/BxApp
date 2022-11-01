package com.bacon57.bxapp

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

    val productList = MutableLiveData<List<Product>>()
    val categoriesList = MutableLiveData<List<Category>>()
    val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible


    val errorMessage = MutableLiveData<String>()

    fun onCreate() {
        viewModelScope.launch {
            _progressVisible.value = true
            getAllProducts()
            getAllCategories()
            _progressVisible.value = false
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
                productList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
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
                categoriesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        }
        )
    }
}