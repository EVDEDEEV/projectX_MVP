package com.example.projectx.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.projectx.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val permissionLaunch = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        viewModel.send()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            permissionLaunch.launch(Manifest.permission.READ_CALL_LOG)
        }

        viewModel.state.observe(this, ::renderState)
    }

    private fun renderState(state: String) {
        binding.textResult.text = state
    }
}