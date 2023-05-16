package com.kodego.diangca.ebrahim.myslambook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityForm3Binding

class Form3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityForm3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForm3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}