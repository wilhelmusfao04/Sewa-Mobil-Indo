// repo/MobilRepository.kt
package com.example.sewamobil.repo

import android.content.Context
import com.example.sewamobil.db.AppDatabase
import com.example.sewamobil.db.toEntity
import com.example.sewamobil.db.toModel
import com.example.sewamobil.model.Mobil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MobilRepository(context: Context) {

    private val dao = AppDatabase.getInstance(context).mobilDao()

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
}
