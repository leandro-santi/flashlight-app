package com.example.flashlightcontroller

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flashlightcontroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

    }

    private fun setListeners() {

        binding.swFlashlightController.setOnCheckedChangeListener { buttonView, isChecked ->

            updateFlashlight(isChecked)

        }

    }

    private fun updateFlashlight(isChecked: Boolean) {

        try {
            cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, isChecked)
            binding.tvFlashlightStatus.text = if (isChecked) "ON" else "OFF"
        } catch (e: CameraAccessException) {
            println(e)
        } finally {
            Log.d("Try", "Tried")
        }

    }

}