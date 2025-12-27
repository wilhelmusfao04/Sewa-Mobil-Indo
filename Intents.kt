// Intents.kt
package com.example.sewamobil

import android.content.Context
import android.content.Intent
import android.net.Uri

object Intents {

    fun openDetailMobil(
        context: Context,
        nama: String,
        harga: Int,
        transmisi: String,
        kursi: Int
    ): Intent {
        return Intent(context, DetailMobilActivity::class.java).apply {
            putExtra(DetailMobilActivity.EXTRA_NAMA, nama)
            putExtra(DetailMobilActivity.EXTRA_HARGA, harga)
            putExtra(DetailMobilActivity.EXTRA_TRANSMISI, transmisi)
            putExtra(DetailMobilActivity.EXTRA_KURSI, kursi)
        }
    }

    fun openWhatsAppAdmin(context: Context, phoneInternational: String, message: String): Intent {
        val encoded = Uri.encode(message)
        val uri = Uri.parse("https://wa.me/$phoneInternational?text=$encoded")
        return Intent(Intent.ACTION_VIEW, uri)
    }

    fun openGoogleMaps(context: Context, query: String): Intent {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(query)}")
        return Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.google.android.apps.maps")
        }
    }
}
