package com.kodego.diangca.ebrahim.myslambook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityForm1Binding
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityFormBinding
import com.kodego.diangca.ebrahim.myslambook.model.SlamBook

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private lateinit var slamBook: SlamBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponent()
    }

    private fun initComponent() {

    }

}