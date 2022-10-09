package com.bacon57.bxapp

class ProductsRepository(private val retrofitService: RetrofitService) {

    fun getAllProducts() = retrofitService.getAllProducts()
    fun getProductById(id:Long) = retrofitService.getProductById(id)

}
