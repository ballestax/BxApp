package com.bacon57.bxapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.bacon57.bxapp.database.entities.ProductEntity
import com.bacon57.bxapp.database.entities.ProductOrderEntity

@Dao
interface ProductOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductOrder(product:ProductOrderEntity)
}