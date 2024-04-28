package com.matrimony.test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.matrimony.test.databinding.ActivityProfileDetailBinding

class ProfileDetailActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    lateinit var profileDetailBinding: ActivityProfileDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        profileDetailBinding = ActivityProfileDetailBinding.inflate(layoutInflater)
        setContentView(profileDetailBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProfileViewModel::class.java]

        var profile = profileViewModel.getSelectedProfile(intent.getIntExtra("Profileid", 0))
        val imageList = listOf(
            "https://source.unsplash.com/300x300/",
            "https://source.unsplash.com/300x300/",
            "https://source.unsplash.com/300x300/"
        )
        with(profileDetailBinding) {
            recycler.apply {
                adapter = ImageAdapter(imageList)
            }
            tvName.text = profile?.name
            tvProfileDetails.text =
                "${profile?.age} Yrs, ${profile?.height} ${profile?.profession} ${profile?.address}"
        }

    }
}