package com.bacon57.bxapp

import java.io.Serializable
import java.math.BigDecimal

data class Additional(
    val id: Long,
    val name: String,
    val price: Double
): Serializable
