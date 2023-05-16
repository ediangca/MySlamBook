package com.kodego.diangca.ebrahim.myslambook

/**
This assignment was locked Dec 16, 2022 at 11:59pm.
Use 15 unique UI components to create slam book.
 * */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}