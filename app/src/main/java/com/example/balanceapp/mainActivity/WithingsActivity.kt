package com.example.balanceapp.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.balanceapp.R
import com.example.balanceapp.databinding.ActivityWithingsBinding


class WithingsActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWithingsBinding

    override fun onCreate(savedInstanceState: Bundle?){
        binding = ActivityWithingsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withings)


    }
}