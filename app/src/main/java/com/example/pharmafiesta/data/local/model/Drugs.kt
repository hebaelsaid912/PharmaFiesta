package com.example.pharmafiesta.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "drug")
data class Drug(
   @PrimaryKey(autoGenerate = false)
   @ColumnInfo(name = "id")
   val id: String,
   @ColumnInfo(name = "tradename") val tradeName: String?,
   @ColumnInfo(name = "activeingredient") val activeIngredient: String?,
   @ColumnInfo(name = "group") val group: String?,
   @ColumnInfo(name = "company") val company: String?,
   @ColumnInfo(name = "info") val info: String?,
   @ColumnInfo(name = "form") val form: String?,
   @ColumnInfo(name = "price") val price: String?
)