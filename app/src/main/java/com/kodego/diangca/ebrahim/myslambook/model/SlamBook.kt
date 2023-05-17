package com.kodego.diangca.ebrahim.myslambook.model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.kodego.diangca.ebrahim.myslambook.R


data class SlamBook(
    val profilePic: Int = R.drawable.profile_icon
) : Parcelable {

    var firstName: String? = null
    var lastName: String? = null
    var nickName: String? = null
    var friendCallMe: String? = null
    var likeToCallMe: String? = null
    var birthDate: String? = null
    var gender: String? = null
    var status: String? = null
    var email: String? = null
    var contactNo: String? = null
    var address: String? = null

    var favoriteSongs: ArrayList<Song>? = null
    var favoriteMovies: ArrayList<Movie>? = null
    var hobbies: ArrayList<Hobbies>? = null
    var skillsWithRate: ArrayList<Skill>? = null

    var defineLove: String? = null
    var defineFriendship: String? = null
    var memorableExperience: String? = null
    var describeMe: String? = null
    var adviceForMe: String? = null
    var rateMe: Int? = null

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        firstName = parcel.readString()
        lastName = parcel.readString()
        nickName = parcel.readString()
        friendCallMe = parcel.readString()
        likeToCallMe = parcel.readString()
        birthDate = parcel.readString()
        gender = parcel.readString()
        status = parcel.readString()
        email = parcel.readString()
        contactNo = parcel.readString()
        address = parcel.readString()
        defineLove = parcel.readString()
        defineFriendship = parcel.readString()
        memorableExperience = parcel.readString()
        describeMe = parcel.readString()
        adviceForMe = parcel.readString()
        rateMe = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(profilePic)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(nickName)
        parcel.writeString(friendCallMe)
        parcel.writeString(likeToCallMe)
        parcel.writeString(birthDate)
        parcel.writeString(gender)
        parcel.writeString(status)
        parcel.writeString(email)
        parcel.writeString(contactNo)
        parcel.writeString(address)
        parcel.writeString(defineLove)
        parcel.writeString(defineFriendship)
        parcel.writeString(memorableExperience)
        parcel.writeString(describeMe)
        parcel.writeString(adviceForMe)
        parcel.writeValue(rateMe)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun printLog() {
        Log.d("SLAM_BOOK_DETAILS", toString())
    }

    override fun toString(): String {
        return "SlamBook(profilePic=$profilePic, firstName=$firstName, lastName=$lastName, nickName=$nickName, friendCallMe=$friendCallMe, likeToCallMe=$likeToCallMe, birthDate=$birthDate, gender=$gender, status=$status, email=$email, contactNo=$contactNo, address=$address, favoriteSongs=$favoriteSongs, favoriteMovies=$favoriteMovies, hobbies=$hobbies, skillsWithRate=$skillsWithRate, defineLove=$defineLove, defineFriendship=$defineFriendship, memorableExperience=$memorableExperience, describeMe=$describeMe, adviceForMe=$adviceForMe, rateMe=$rateMe)"
    }

    companion object CREATOR : Parcelable.Creator<SlamBook> {
        override fun createFromParcel(parcel: Parcel): SlamBook {
            return SlamBook(parcel)
        }

        override fun newArray(size: Int): Array<SlamBook?> {
            return arrayOfNulls(size)
        }
    }

}