package com.bacon57.bxapp.model

import java.io.Serializable
import java.math.BigDecimal

data class Additional(
    val id: Long,
    val name: String,
    val price: Double
): Serializable
