package com.bacon57.bxapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bacon57.bxapp.database.dao.ProductDao
import com.bacon57.bxapp.database.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase: RoomDatabase(){

    abstract fun getProductDao():ProductDao
}