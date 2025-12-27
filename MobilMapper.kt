// db/MobilMapper.kt
package com.example.sewamobil.db

import com.example.sewamobil.db.entity.MobilEntity
import com.example.sewamobil.model.Mobil

fun MobilEntity.toModel(): Mobil {
    return Mobil(
        nama = nama,
        hargaPerHari = hargaPerHari,
        transmisi = transmisi,
        kursi = kursi
    )
}

fun Mobil.toEntity(id: Long = 0L): MobilEntity {
    return MobilEntity(
        id = id,
        nama = nama,
        hargaPerHari = hargaPerHari,
        transmisi = transmisi,
        kursi = kursi
    )
}
