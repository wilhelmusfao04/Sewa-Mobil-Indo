// network/MobilDto.kt
package com.example.sewamobil.network

import com.google.gson.annotations.SerializedName

data class MobilDto(
    @SerializedName("nama") val nama: String,
    @SerializedName("hargaPerHari") val hargaPerHari: Int,
    @SerializedName("transmisi") val transmisi: String,
    @SerializedName("kursi") val kursi: Int
)
