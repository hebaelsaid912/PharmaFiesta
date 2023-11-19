package com.example.pharmafiesta.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pharmafiesta.data.local.Drug

@Dao
interface DrugsDao {
    @Query("SELECT * FROM drug")
    fun getAll(): List<Drug>

    @Query("SELECT * FROM drug WHERE tradeName LIKE :drugTradeName ")
    fun searchDrugs(drugTradeName: String): List<Drug>

    @Insert
    fun insertAll(vararg drug: Drug)

    @Delete
    fun delete(drugs: List<Drug>)
}