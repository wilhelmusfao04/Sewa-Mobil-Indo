// network/MobilApi.kt
package com.example.sewamobil.network

import retrofit2.http.GET

interface MobilApi {
    @GET("mobil")
    suspend fun getMobil(): List<MobilDto>
}
