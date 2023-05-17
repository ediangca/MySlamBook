package com.kodego.diangca.ebrahim.myslambook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityMenuBinding
import com.kodego.diangca.ebrahim.myslambook.model.SlamBook

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private var slamBook = SlamBook()
    private var slamBooks:ArrayList<SlamBook> = ArrayList()


    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data

        Log.d("FROM REGISTER", data!!.getStringExtra("username").toString())
        Snackbar.make(
            binding.root,
            "Hi  ${data!!.getStringExtra("firstname")}! \n Please wait for the confirmation of your Account",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            btnCreateOnClickListener()
        }
    }

    private fun btnCreateOnClickListener() {
        var nextForm = Intent(this, FormActivity::class.java)
        nextForm.putExtra("slamBooK", slamBook)
        startActivity(nextForm)
        finish()

    }
}