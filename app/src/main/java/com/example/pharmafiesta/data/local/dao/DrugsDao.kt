package com.example.pharmafiesta.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pharmafiesta.data.local.model.Drug

@Dao
interface DrugsDao {
    @Query("SELECT * FROM drug")
    suspend fun getAll(): List<Drug>

    @Query("SELECT * FROM drug WHERE tradename =:drugTradeName ORDER BY id DESC ")
    suspend fun searchDrugs(drugTradeName: String): List<Drug>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg drug: Drug)

    @Delete
    suspend fun delete(drugs: List<Drug>)

    @Query("DELETE  FROM drug")
    suspend fun clearDrugsDatabase()
}