package com.kodego.diangca.ebrahim.myslambook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityForm1Binding
import com.kodego.diangca.ebrahim.myslambook.model.SlamBook
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Form1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityForm1Binding
    private lateinit var slamBook: SlamBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForm1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent!=null) {
            slamBook = ((intent?.extras?.getSerializable("slamBooK") as SlamBook?)!!)
        }
        binding.btnBack.setOnClickListener {
            btnBackOnClickListener()
        }
        binding.btnNext.setOnClickListener {
            btnNextOnClickListener()
        }
    }

    private fun btnNextOnClickListener() {
        if (binding.nickName.text.isNullOrEmpty() ||
            binding.friendCall.text.isNullOrEmpty() ||
            binding.likeToCall.text.isNullOrEmpty() ||
            binding.lastName.text.isNullOrEmpty() ||
            binding.firstName.text.isNullOrEmpty() ||
            binding.dateMonth.selectedItemPosition==0 ||
            binding.dateDay.selectedItemPosition==0 ||
            binding.dateYear.text.isNullOrEmpty() ||
            binding.gender.selectedItemPosition==0 ||
            binding.gender.selectedItemPosition==0 ||
            binding.status.selectedItemPosition==0 ||
            binding.emailAdd.text.isNullOrEmpty() ||
            binding.contactNo.text.isNullOrEmpty() ||
            binding.address.text.isNullOrEmpty()
        ) {

            Snackbar.make(binding.root, "Please check empty fields", Snackbar.LENGTH_SHORT).show()

            return
        }
        slamBook.nickName = binding.nickName.text.toString()
        slamBook.friendCallMe = binding.friendCall.text.toString()
        slamBook.likeToCallMe = binding.likeToCall.text.toString()
        slamBook.lastName = binding.lastName.text.toString()
        slamBook.firstName = binding.firstName.text.toString()
        slamBook.birthDate =
            "${binding.dateYear.text}-${binding.dateMonth.selectedItem}-${binding.dateDay.selectedItem}"
        slamBook.gender = binding.gender.selectedItem.toString()
        slamBook.status = binding.status.selectedItem.toString()
        slamBook.email = binding.emailAdd.text.toString()
        slamBook.contactNo = binding.contactNo.text.toString()
        slamBook.address = binding.address.text.toString()

        Log.d(
            "FORM 1",
            "Hi ${slamBook.lastName}, ${slamBook.lastName}!, Please complete the following fields. Thank you!"
        )

        var nextForm = Intent(this, Form2Activity::class.java)
        nextForm.putExtra("slamBooK", slamBook)
        startActivity(nextForm)
        finish()
    }

    private fun btnBackOnClickListener() {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()

    }
}