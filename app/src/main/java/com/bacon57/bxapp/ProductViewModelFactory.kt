package com.bacon57.bxapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ProductViewModelFactory(private val repository: ProductsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ProductListViewModel::class.java)){
            ProductListViewModel(this.repository) as T
        }else{
            throw  IllegalArgumentException("ViewModel not found")
        }
    }
}