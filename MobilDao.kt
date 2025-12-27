// db/dao/MobilDao.kt
package com.example.sewamobil.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sewamobil.db.entity.MobilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MobilDao {

    @Query("SELECT * FROM mobil ORDER BY nama ASC")
    fun observeAll(): Flow<List<MobilEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MobilEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MobilEntity): Long

    @Query("DELETE FROM mobil")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM mobil")
    suspend fun count(): Int
}
