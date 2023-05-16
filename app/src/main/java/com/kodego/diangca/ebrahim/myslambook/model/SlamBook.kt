package com.kodego.diangca.ebrahim.myslambook.model

import com.kodego.diangca.ebrahim.myslambook.R


data class SlamBook(
    val profilePic: Int = R.drawable.profile_icon
) : java.io.Serializable {

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

}