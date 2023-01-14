package com.bacon57.bxapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_order_table")
data class ProductOrderEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "product_id")
    val idProduct: Long,

    @ColumnInfo(name = "presentation_id")
    val idPresentation: Long,

    @ColumnInfo(name = "quantity")
    val quantity: Long,



)
