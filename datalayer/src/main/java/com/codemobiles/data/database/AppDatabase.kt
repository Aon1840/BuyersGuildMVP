package com.codemobiles.buyersguildmvp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codemobiles.data.model.db.MobileEntity

@Database(entities = [MobileEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): MobileDAO
}