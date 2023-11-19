package com.example.pharmafiesta.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pharmafiesta.data.local.model.Drug
import com.example.pharmafiesta.data.local.dao.DrugsDao

@Database(entities = [Drug::class], version = 1)
abstract class DrugsDatabase : RoomDatabase() {
    abstract fun drugsDao(): DrugsDao
}