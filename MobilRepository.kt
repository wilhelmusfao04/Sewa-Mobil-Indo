// repo/MobilRepository.kt (update)
package com.example.sewamobil.repo

import android.content.Context
import androidx.room.withTransaction
import com.example.sewamobil.db.AppDatabase
import com.example.sewamobil.db.entity.MobilEntity
import com.example.sewamobil.db.toEntity
import com.example.sewamobil.db.toModel
import com.example.sewamobil.model.Mobil
import com.example.sewamobil.network.ApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MobilRepository(context: Context) {

    private val db = AppDatabase.getInstance(context)
    private val dao = db.mobilDao()
    private val api = ApiClient.mobilApi

    fun observeMobil(): Flow<List<Mobil>> {
        return dao.observeAll().map { list -> list.map { it.toModel() } }
    }

    suspend fun seedIfEmpty() {
        if (dao.count() > 0) return

        val seed = listOf(
            Mobil("Toyota Avanza", 350000, "Manual", 7),
            Mobil("Honda Brio", 300000, "Automatic", 5),
            Mobil("Toyota Innova", 550000, "Automatic", 7)
        ).map { it.toEntity() }

        dao.insertAll(seed)
    }

    suspend fun syncMobilFromServer(): Result<Unit> {
        return runCatching {
            val remote = api.getMobil()
            val entities = remote.map {
                MobilEntity(
                    nama = it.nama,
                    hargaPerHari = it.hargaPerHari,
                    transmisi = it.transmisi,
                    kursi = it.kursi
                )
            }

            db.withTransaction {
                dao.clear()
                dao.insertAll(entities)
            }
        }
    }
}
