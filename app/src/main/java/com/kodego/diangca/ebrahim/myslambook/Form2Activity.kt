package com.kodego.diangca.ebrahim.myslambook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kodego.diangca.ebrahim.myslambook.databinding.ActivityForm2Binding
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterHobbies
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterMovie
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterSkill
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterSong
import com.kodego.diangca.ebrahim.myslambook.model.*

class Form2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityForm2Binding
    private lateinit var slamBook: SlamBook


    private lateinit var adapterSong: AdapterSong
    private var songs: ArrayList<Song> = ArrayList()

    private lateinit var adapterMovie: AdapterMovie
    private var movies: ArrayList<Movie> = ArrayList()

    private lateinit var adapterHobbies: AdapterHobbies
    private var hobbies: ArrayList<Hobbies> = ArrayList()

    private lateinit var adapterSkill: AdapterSkill
    private var skills: ArrayList<Skill> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForm2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        adapterSong = AdapterSong(this, songs)
        binding.favSongList.layoutManager = LinearLayoutManager(applicationContext)
        binding.favSongList.adapter = adapterSong

        adapterMovie = AdapterMovie(this, movies)
        binding.favMovieList.layoutManager = LinearLayoutManager(applicationContext)
        binding.favMovieList.adapter = adapterMovie

        adapterHobbies = AdapterHobbies(this, hobbies)
        binding.hobbiesList.layoutManager = LinearLayoutManager(applicationContext)
        binding.hobbiesList.adapter = adapterHobbies

        adapterSkill = AdapterSkill(this, skills)
        binding.skillList.layoutManager = LinearLayoutManager(applicationContext)
        binding.skillList.adapter = adapterSkill

        if (intent!=null) {
            slamBook = ((intent?.extras?.getSerializable("slamBooK") as SlamBook?)!!)
        }

        Snackbar.make(
            binding.root,
            "Hi ${slamBook.lastName}, ${slamBook.lastName}!, Please complete the following fields. Thank you!",
            Snackbar.LENGTH_SHORT
        ).show()

        binding.btnAddFavSong.setOnClickListener {
            btnAddOnClickListener(binding.root, "Song", binding.songName, binding.favSongList)
        }
        binding.btnAddFavMov.setOnClickListener {
            btnAddOnClickListener(binding.root, "Movie", binding.movieName, binding.favMovieList)
        }

        binding.btnAddHobbies.setOnClickListener {
            btnAddOnClickListener(binding.root, "Hobbies", binding.hobbies, binding.hobbiesList)
        }

        binding.btnAddSkill.setOnClickListener {
            btnAddOnClickListener(binding.root, "Skills", binding.skill, binding.skillList)
        }


        binding.btnBack.setOnClickListener {
            btnBackOnClickListener()
        }
        binding.btnNext.setOnClickListener {
            btnNextOnClickListener()
        }
    }

    private fun btnAddOnClickListener(
        view: View?,
        type: String,
        field: TextInputEditText,

        recyclerView: RecyclerView
    ) {
        var text = field.text.toString()

        if (text.isEmpty()) {
            Snackbar.make(binding.root, "Please check empty fields.", Snackbar.LENGTH_SHORT).show()
            return
        }
        when (type) {
            "Song" -> {
                songs.add(Song(text))
            }
            "Movie" -> {
                movies.add(Movie(text))
            }
            "Hobbies" -> {
                hobbies.add(Hobbies(text))
            }
            "Skills" -> {
                if(binding.skillRate.selectedItemPosition.toInt() == 0){
                    Snackbar.make(binding.root, "Please select rate first.s", Snackbar.LENGTH_SHORT).show()
                    return
                }
                skills.add(Skill(text, binding.skillRate.selectedItemPosition))
            }
        }
        Snackbar.make(binding.root, "Data has been successfully added.", Snackbar.LENGTH_SHORT)
            .show()

        field.setText("")
        recyclerView.adapter!!.notifyDataSetChanged()

        // on below line checking if view is not null.
        if (view!=null) {
            // on below line we are creating a variable
            // for input manager and initializing it.
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            // on below line hiding our keyboard.
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            recyclerView.requestFocus()
        }
    }


    private fun btnNextOnClickListener() {
        var nextForm = Intent(this, Form3Activity::class.java)
        startActivity(nextForm)
        finish()
    }

    private fun btnBackOnClickListener() {
        startActivity(Intent(this, Form1Activity::class.java))
        finish()
    }
}