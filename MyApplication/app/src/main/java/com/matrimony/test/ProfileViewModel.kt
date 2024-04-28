package com.matrimony.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var database: ProfileDatabase
    var profileSize = 0
    var profileSizeText = "$profileSize  profile matching with me"
    val profileList = MutableLiveData<ArrayList<ProfileDatabase.Profile>>()

    val dummyProfiles = listOf(
        ProfileDatabase.Profile(
            "Alice",
            30,
            "170cm",
            "Doctor",
            "123 Main St, Anytown, CA 12345",
            "https://example.com/image1.jpg"
        ),
        ProfileDatabase.Profile(
            "Bob",
            35,
            "185cm",
            "Engineer",
            "456 Elm St, Anytown, CA 98765",
            "https://example.com/image2.png"
        ),
        ProfileDatabase.Profile(
            "Charlie",
            28,
            "165cm",
            "Teacher",
            "789 Oak St, Anytown, NY 54321",
            "https://example.com/image3.jpeg"
        ),
        ProfileDatabase.Profile(
            "Diana",
            25,
            "158cm",
            "Artist",
            "1011 Beach Blvd, Anytown, FL 09876",
            "https://example.com/image4.bmp"
        ),
        ProfileDatabase.Profile(
            "Emily",
            40,
            "190cm",
            "Programmer",
            "1213 Maple Ave, Anytown, TX 23456",
            "https://example.com/image5.gif"
        )
    )

    init {
        database = ProfileDatabase(application)
        insertDummyData()
    }

    private fun insertDummyData() {
        val data = fetchDatafromDB()
        profileSize = data.size
        if (profileSize == 0) {
            dummyProfiles.forEach { profile ->
                database.insert(profile)
            }
        } else {
            profileList.value = data
        }

    }

    fun loadData() {
        profileList.value = fetchDatafromDB()
    }

    fun fetchDatafromDB(): ArrayList<ProfileDatabase.Profile> {
        return database.getAll()
    }

    fun onButtonClick(yesorNo: Boolean, index: Int) {
        profileList.value?.removeAt(index)
    }

    fun getSelectedProfile(profileId: Int): ProfileDatabase.Profile? {
        return database.getSelected(profileId)
    }

}