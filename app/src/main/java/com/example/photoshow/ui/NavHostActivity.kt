package com.example.photoshow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photoshow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}
