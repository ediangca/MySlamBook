package com.kodego.diangca.ebrahim.myslambook

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterHobbies
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterMovie
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterSkill
import com.kodego.diangca.ebrahim.myslambook.adapter.AdapterSong
import com.kodego.diangca.ebrahim.myslambook.databinding.FragmentFormPageTwoBinding
import com.kodego.diangca.ebrahim.myslambook.model.*


class FormPageTwoFragment() : Fragment() {

    private lateinit var binding: FragmentFormPageTwoBinding

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
        if (arguments!=null) {
            slamBook = ((arguments?.getParcelable("slamBooK") as SlamBook?)!!)
            slamBook.printLog()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("ON_RESUME", "RESUME_PAGE_TWO")
        if (arguments!=null) {
            slamBook = ((arguments?.getParcelable("slamBooK") as SlamBook?)!!)
            slamBook.printLog()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ON_ATTACH", "ATTACH_PAGE_TWO")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormPageTwoBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }


    private fun initComponents() {

        with(binding) {

            adapterSong = AdapterSong(root.context, songs)
            binding.favSongList.layoutManager = LinearLayoutManager(root.context)
            binding.favSongList.adapter = adapterSong

            adapterMovie = AdapterMovie(root.context, movies)
            binding.favMovieList.layoutManager = LinearLayoutManager(root.context)
            binding.favMovieList.adapter = adapterMovie

            adapterHobbies = AdapterHobbies(root.context, this@FormPageTwoFragment.hobbies)
            binding.hobbiesList.layoutManager = LinearLayoutManager(root.context)
            binding.hobbiesList.adapter = adapterHobbies

            adapterSkill = AdapterSkill(root.context, skills)
            binding.skillList.layoutManager = LinearLayoutManager(root.context)
            binding.skillList.adapter = adapterSkill

            /*Snackbar.make(
                root,
                "Hi ${slamBook.lastName}, ${slamBook.lastName}!, Please complete the following fields. Thank you!",
                Snackbar.LENGTH_SHORT
            ).show()*/

            btnAddFavSong.setOnClickListener {
                btnAddOnClickListener(binding.root, "Song", binding.songName, binding.favSongList)
            }
            btnAddFavMov.setOnClickListener {
                btnAddOnClickListener(binding.root, "Movie", binding.movieName, binding.favMovieList)
            }

            btnAddHobbies.setOnClickListener {
                btnAddOnClickListener(binding.root, "Hobbies", binding.hobbies, binding.hobbiesList)
            }

            btnAddSkill.setOnClickListener {
                btnAddOnClickListener(binding.root, "Skills", binding.skill, binding.skillList)
            }

            btnBack.setOnClickListener {
                btnBackOnClickListener()
            }
            btnNext.setOnClickListener {
                btnNextOnClickListener()
            }

        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                var bundle = Bundle()
                bundle.putParcelable("slamBooK", slamBook)
                findNavController().navigate(R.id.action_formPageTwoFragment_to_formPageOneFragment, bundle)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    @SuppressLint("NotifyDataSetChanged")
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
                binding.root.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager

            // on below line hiding our keyboard.
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            recyclerView.requestFocus()
        }
    }


    private fun btnNextOnClickListener() {
        var bundle = Bundle()
        bundle.putParcelable("slamBooK", slamBook)

        findNavController().navigate(R.id.action_formPageTwoFragment_to_formPageThreeFragment, bundle)
    }

    private fun btnBackOnClickListener() {
        var bundle = Bundle()
        bundle.putParcelable("slamBooK", slamBook)

        findNavController().navigate(R.id.action_formPageTwoFragment_to_formPageOneFragment, bundle)
    }

}