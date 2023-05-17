package com.kodego.diangca.ebrahim.myslambook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.myslambook.databinding.FragmentFormPageOneBinding
import com.kodego.diangca.ebrahim.myslambook.databinding.FragmentFormPageThreeBinding
import com.kodego.diangca.ebrahim.myslambook.databinding.FragmentFormPageTwoBinding
import com.kodego.diangca.ebrahim.myslambook.model.SlamBook


class FormPageThreeFragment(slamBook: SlamBook) : Fragment() {
    private lateinit var slamBook: SlamBook

    private lateinit var binding: FragmentFormPageThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!=null) {
            slamBook = ((arguments?.getParcelable("slamBooK") as SlamBook?)!!)
            slamBook.printLog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormPageThreeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }


    private fun initComponents() {
        with(binding) {
            btnBack.setOnClickListener {
                btnBackOnClickListener()
            }
            btnSave.setOnClickListener {
                btnSaveOnClickListener()
            }
        }
    }

    private fun btnSaveOnClickListener() {
        Toast.makeText(binding.root.context, "Saving..", Toast.LENGTH_LONG).show()
    }

    private fun btnBackOnClickListener() {
        var bundle = Bundle()
        bundle.putParcelable("slamBooK", slamBook)

        findNavController().navigate(R.id.action_formPageThreeFragment_to_formPageTwoFragment, bundle)
    }


}