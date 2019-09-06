package com.codemobiles.buyersguildmvp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete

@Dao
interface MobileDAO {
    @Query(value = "select * from favorite")
    fun queryFavorites(): List<MobileEntity>

    @Insert
    fun addFavorite(favoriteEntity: MobileEntity)

    @Delete
    fun deleteFavorite(favoriteEntity: MobileEntity)
}