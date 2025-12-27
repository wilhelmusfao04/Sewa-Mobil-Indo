// DetailMobilActivity.kt
package com.example.sewamobil

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.text.NumberFormat
import java.util.Locale

class DetailMobilActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_HARGA = "extra_harga"
        const val EXTRA_TRANSMISI = "extra_transmisi"
        const val EXTRA_KURSI = "extra_kursi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mobil)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)

        val nama = intent.getStringExtra(EXTRA_NAMA).orEmpty()
        val harga = intent.getIntExtra(EXTRA_HARGA, 0)
        val transmisi = intent.getStringExtra(EXTRA_TRANSMISI).orEmpty()
        val kursi = intent.getIntExtra(EXTRA_KURSI, 0)

        findViewById<TextView>(R.id.tvNamaMobil).text = nama
        findViewById<TextView>(R.id.tvHarga).text = formatRupiah(harga) + " / hari"
        findViewById<TextView>(R.id.tvTransmisi).text = transmisi
        findViewById<TextView>(R.id.tvKursi).text = "$kursi Kursi"

        findViewById<Button>(R.id.btnSewa).setOnClickListener {
            Toast.makeText(this, "Berhasil memilih sewa: $nama", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun formatRupiah(value: Int): String {
        val localeID = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localeID)
        return formatter.format(value).replace(",00", "")
    }
}
