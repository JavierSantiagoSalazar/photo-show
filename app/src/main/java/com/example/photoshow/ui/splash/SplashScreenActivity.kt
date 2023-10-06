package com.example.photoshow.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.photoshow.databinding.ActivitySplashScreenBinding
import com.example.photoshow.ui.NavHostActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.lottie.animate()
                .setStartDelay(4000)
                .translationX(-1500F).duration = 1000

            binding.tvAppName.animate()
                .setStartDelay(4000)
                .translationX(-1500F).duration = 1000

            startActivity(Intent(this, NavHostActivity::class.java))
            finish()
        }, 4000)
    }
}
