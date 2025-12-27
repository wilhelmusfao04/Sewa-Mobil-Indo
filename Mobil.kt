// model/Mobil.kt
package com.example.sewamobil.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mobil(
    val nama: String,
    val hargaPerHari: Int,
    val transmisi: String,
    val kursi: Int
) : Parcelable
