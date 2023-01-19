package com.bacon57.bxapp.model

import java.math.BigDecimal

data class ProductOrder(
    val productId: Long,
    val presentationId: Long,
    val price: BigDecimal,
    val exclusions: List<Ingredient>,
    val additions: List<Additional>,
    
) : java.io.Serializable
