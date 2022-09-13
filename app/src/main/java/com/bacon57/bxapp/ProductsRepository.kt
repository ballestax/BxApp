package com.bacon57.bxapp

class ProductsRepository(private val retrofitService: RetrofitService) {

    fun getAllProducts() = retrofitService.getlAllProducts()

}
