// db/entity/MobilEntity.kt
package com.example.sewamobil.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mobil")
data class MobilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val hargaPerHari: Int,
    val transmisi: String,
    val kursi: Int
)
