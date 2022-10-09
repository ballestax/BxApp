package com.bacon57.bxapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ProductViewModelFactory(
    private val repository: ProductsRepository,
    private val repositoryCat: CategoriesRepository,
    private val id: Long) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ProductListViewModel::class.java)){
            ProductListViewModel(this.repository, this.repositoryCat) as T
        }else if(modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            ProductDetailViewModel(this.repository, this.id) as T
        }else{
            throw  IllegalArgumentException("ViewModel not found")
        }
    }
}