// MainActivity.kt
package com.example.sewamobil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        findViewById<Button>(R.id.btnLihatDetailAvanza).setOnClickListener {
            openDetail(
                nama = "Toyota Avanza",
                harga = 350000,
                transmisi = "Manual",
                kursi = 7
            )
        }

        findViewById<Button>(R.id.btnLihatDetailBrio).setOnClickListener {
            openDetail(
                nama = "Honda Brio",
                harga = 300000,
                transmisi = "Automatic",
                kursi = 5
            )
        }

        findViewById<Button>(R.id.btnLihatDetailInnova).setOnClickListener {
            openDetail(
                nama = "Toyota Innova",
                harga = 550000,
                transmisi = "Automatic",
                kursi = 7
            )
        }
    }

    private fun openDetail(nama: String, harga: Int, transmisi: String, kursi: Int) {
        val intent = Intent(this, DetailMobilActivity::class.java).apply {
            putExtra(DetailMobilActivity.EXTRA_NAMA, nama)
            putExtra(DetailMobilActivity.EXTRA_HARGA, harga)
            putExtra(DetailMobilActivity.EXTRA_TRANSMISI, transmisi)
            putExtra(DetailMobilActivity.EXTRA_KURSI, kursi)
        }
        startActivity(intent)
    }
}
