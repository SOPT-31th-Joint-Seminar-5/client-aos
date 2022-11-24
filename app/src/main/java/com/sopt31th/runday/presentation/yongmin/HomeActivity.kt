package com.sopt31th.runday.presentation.yongmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt31th.runday.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
