package com.kodego.diangca.ebrahim.myslambook

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.myslambook.databinding.FragmentFormPageOneBinding
import com.kodego.diangca.ebrahim.myslambook.model.SlamBook
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class FormPageOneFragment() : Fragment() {

    private lateinit var binding: FragmentFormPageOneBinding

    private lateinit var slamBook: SlamBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.d("ON_RESUME", "RESUME_PAGE_ONE")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ON_ATTACH", "ATTACH_PAGE_ONE")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormPageOneBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }

    private fun initComponents() {
        if (arguments!=null) {
            slamBook = ((arguments?.getParcelable("slamBooK") as SlamBook?)!!)
            slamBook.printLog()
            restoreField()
        } else {
            slamBook = SlamBook()
        }


        with(binding) {
            btnBack.setOnClickListener {
                btnBackOnClickListener()
            }
            btnNext.setOnClickListener {
                btnNextOnClickListener()
            }
            binding.dateMonth.prompt = "Please enter Birth Month"
            binding.dateDay.prompt = "Please enter Birth day"
            binding.gender.prompt = "Please enter Gender"
            binding.status.prompt = "Please enter Status"
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun restoreField() {

        binding.apply {
            nickName.setText(slamBook.nickName)
            friendCall.setText(slamBook.friendCallMe)
            likeToCall.setText(slamBook.likeToCallMe)
            lastName.setText(slamBook.lastName)
            firstName.setText(slamBook.firstName)

            if(!slamBook.birthDate.isNullOrEmpty()){
                val arrayMonth = resources.getStringArray(R.array.monthName)
                val arrayDay = resources.getStringArray(R.array.monthDay)
                val birtDate: Date = Date(slamBook.birthDate)

                dateMonth.setSelection(arrayMonth.indexOf(SimpleDateFormat("MMMM").format(birtDate)));
                dateDay.setSelection(arrayDay.indexOf(SimpleDateFormat("dd").format(birtDate)));
                dateYear.setText(SimpleDateFormat("yyyy").format(birtDate));
            }

            if(!slamBook.gender.isNullOrEmpty()){
                val arrayGender = resources.getStringArray(R.array.gender)
                gender.setSelection(arrayGender.indexOf(slamBook.gender))
            }

            if(!slamBook.status.isNullOrEmpty()){
            val arrayStatus = resources.getStringArray(R.array.status)
            status.setSelection(arrayStatus.indexOf(slamBook.status))
            }
            emailAdd.setText(slamBook.email)
            contactNo.setText(slamBook.contactNo)
            address.setText(slamBook.address)

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
            binding.status.selectedItemPosition==0 ||
            binding.emailAdd.text.isNullOrEmpty() ||
            binding.contactNo.text.isNullOrEmpty() ||
            binding.address.text.isNullOrEmpty()
        ) {
            if(binding.nickName.text.isNullOrEmpty()){
                binding.nickName.error = "Please enter Nick Name"
            }
            if(binding.friendCall.text.isNullOrEmpty()){
                binding.friendCall.error = "Please enter Friend call you"
            }
            if(binding.likeToCall.text.isNullOrEmpty()){
                binding.likeToCall.error = "Please enter Like call you as"
            }
            if(binding.lastName.text.isNullOrEmpty()){
                binding.lastName.error = "Please enter Last Name"
            }
            if(binding.firstName.text.isNullOrEmpty()){
                binding.firstName.error = "Please enter First Name"
            }
            if(binding.dateMonth.selectedItemPosition==0){
            }
            if(binding.dateDay.selectedItemPosition==0){
            }
            if(binding.dateYear.text.isNullOrEmpty()){
                binding.dateYear.error = "Please enter Birth year"
            }
            if(binding.gender.selectedItemPosition==0){
            }
            if(binding.status.selectedItemPosition==0){
            }
            if(binding.emailAdd.text.isNullOrEmpty()){
                binding.emailAdd.error = "Please enter Email"
            }
            if(binding.contactNo.text.isNullOrEmpty()){
                binding.contactNo.error = "Please enter Contact No."
            }
            if(binding.address.text.isNullOrEmpty()){
                binding.address.error = "Please enter Address"
            }

            Snackbar.make(binding.root, "Please check empty fields", Snackbar.LENGTH_SHORT).show()

            return
        }
        slamBook.nickName = binding.nickName.text.toString()
        slamBook.friendCallMe = binding.friendCall.text.toString()
        slamBook.likeToCallMe = binding.likeToCall.text.toString()
        slamBook.lastName = binding.lastName.text.toString()
        slamBook.firstName = binding.firstName.text.toString()
        slamBook.birthDate =
            "${binding.dateMonth.selectedItem} ${binding.dateDay.selectedItem}, ${binding.dateYear.text}"
        slamBook.gender = binding.gender.selectedItem.toString()
        slamBook.status = binding.status.selectedItem.toString()
        slamBook.email = binding.emailAdd.text.toString()
        slamBook.contactNo = binding.contactNo.text.toString()
        slamBook.address = binding.address.text.toString()

        Log.d(
            "FORM 1",
            "Hi ${slamBook.lastName}, ${slamBook.lastName}!, Please complete the following fields. Thank you!"
        )

        var bundle = Bundle()
        bundle.putParcelable("slamBooK", slamBook)

        findNavController().navigate(R.id.action_formPageOneFragment_to_formPageTwoFragment, bundle)

    }

    private fun btnBackOnClickListener() {
    }
}