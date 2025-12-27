// MainActivity.kt (update)
package com.example.sewamobil

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
            startActivity(
                Intents.openDetailMobil(
                    context = this,
                    nama = "Toyota Avanza",
                    harga = 350000,
                    transmisi = "Manual",
                    kursi = 7
                )
            )
        }

        findViewById<Button>(R.id.btnLihatDetailBrio).setOnClickListener {
            startActivity(
                Intents.openDetailMobil(
                    context = this,
                    nama = "Honda Brio",
                    harga = 300000,
                    transmisi = "Automatic",
                    kursi = 5
                )
            )
        }

        findViewById<Button>(R.id.btnLihatDetailInnova).setOnClickListener {
            startActivity(
                Intents.openDetailMobil(
                    context = this,
                    nama = "Toyota Innova",
                    harga = 550000,
                    transmisi = "Automatic",
                    kursi = 7
                )
            )
        }
    }
}
