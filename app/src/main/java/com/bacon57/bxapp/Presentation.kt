package com.bacon57.bxapp

import java.io.Serializable
import java.math.BigDecimal

data class Presentation(
    val id: Long,
    val name: String,
    val price: Double,
    val _enabled: Boolean,
    val _default: Boolean
): Serializable
