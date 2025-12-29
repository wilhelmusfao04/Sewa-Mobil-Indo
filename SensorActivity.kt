// ui/SensorActivity.kt
package com.example.sewamobil.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sewamobil.R
import kotlin.math.abs
import kotlin.math.sqrt

class SensorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private var accel: Sensor? = null
    private var gyro: Sensor? = null
    private var light: Sensor? = null
    private var proximity: Sensor? = null

    private lateinit var tvAccel: TextView
    private lateinit var tvGyro: TextView
    private lateinit var tvLight: TextView
    private lateinit var tvProx: TextView
    private lateinit var tvShake: TextView

    private var lastAccelMag = 0f
    private var shakeCounter = 0
    private var lastShakeAt = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)

        tvAccel = findViewById(R.id.tvAccel)
        tvGyro = findViewById(R.id.tvGyro)
        tvLight = findViewById(R.id.tvLight)
        tvProx = findViewById(R.id.tvProx)
        tvShake = findViewById(R.id.tvShake)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        renderAvailability()
    }

    private fun renderAvailability() {
        tvAccel.text = if (accel != null) "Accelerometer: siap" else "Accelerometer: tidak tersedia"
        tvGyro.text = if (gyro != null) "Gyroscope: siap" else "Gyroscope: tidak tersedia"
        tvLight.text = if (light != null) "Light: siap" else "Light: tidak tersedia"
        tvProx.text = if (proximity != null) "Proximity: siap" else "Proximity: tidak tersedia"
        tvShake.text = "Shake: 0"
    }

    override fun onResume() {
        super.onResume()
        accel?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        gyro?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        light?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        proximity?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val mag = sqrt(x * x + y * y + z * z)
                detectShake(mag)
                tvAccel.text = "Accelerometer: x=%.2f y=%.2f z=%.2f".format(x, y, z)
            }

            Sensor.TYPE_GYROSCOPE -> {
                val rx = event.values[0]
                val ry = event.values[1]
                val rz = event.values[2]
                tvGyro.text = "Gyroscope: x=%.2f y=%.2f z=%.2f".format(rx, ry, rz)
            }

            Sensor.TYPE_LIGHT -> {
                val lx = event.values[0]
                tvLight.text = "Light: %.1f lx".format(lx)
            }

            Sensor.TYPE_PROXIMITY -> {
                val px = event.values[0]
                val near = px < (proximity?.maximumRange ?: 0f)
                tvProx.text = if (near) "Proximity: dekat" else "Proximity: jauh"
            }
        }
    }

    private fun detectShake(currentMag: Float) {
        val delta = abs(currentMag - lastAccelMag)
        lastAccelMag = currentMag

        val now = System.currentTimeMillis()
        if (delta > 6.5f && now - lastShakeAt > 450L) {
            lastShakeAt = now
            shakeCounter++
            tvShake.text = "Shake: $shakeCounter"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
