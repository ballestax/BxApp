package com.bacon57.bxapp

import java.io.Serializable

data class Product(
    val id: Long,
    val name: String,
    val code: String,
    val category: String,
    val price: Double,
    val variable: Boolean,
    val ingredients: Set<Ingredient>
): Serializable
