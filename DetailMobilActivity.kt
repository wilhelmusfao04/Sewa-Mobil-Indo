// ui/DetailMobilActivity.kt (add button to open SensorActivity)
package com.example.sewamobil.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sewamobil.R

class DetailMobilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mobil)

        findViewById<Button>(R.id.btnSensor).setOnClickListener {
            startActivity(Intent(this, SensorActivity::class.java))
        }
    }
}
