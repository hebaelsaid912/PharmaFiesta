package com.example.pharmafiesta.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Drug(
   @PrimaryKey val uid: Int,
   @ColumnInfo(name = "activeIngredient") val activeIngredient: String?,
   @ColumnInfo(name = "company") val company: String?,
   @ColumnInfo(name = "form") val form: String?,
   @ColumnInfo(name = "group") val group: String?,
   @ColumnInfo(name = "id") val id: Int?,
   @ColumnInfo(name = "info") val info: Int?,
   @ColumnInfo(name = "price") val price: Double?,
   @ColumnInfo(name = "tradeName") val tradeName: String?
)