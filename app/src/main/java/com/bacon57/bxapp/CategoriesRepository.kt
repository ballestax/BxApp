package com.bacon57.bxapp

class CategoriesRepository(private val retrofitService: RetrofitService) {

    fun getAllCategories() = retrofitService.getlAllCategories()

}
