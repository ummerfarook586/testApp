package com.matrimony.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrimony.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), HomeListener {
    private lateinit var database: ProfileDatabase
    private lateinit var profileViewModel: ProfileViewModel
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(mainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        profileViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProfileViewModel::class.java]

        profileViewModel.profileList.observe(this) { profileList ->
            if (profileList.size > 0) {
                mainBinding.tvnoData.visibility = View.GONE
                mainBinding.rvProfile.visibility = View.VISIBLE
                mainBinding.rvProfile.apply {
                    adapter = HomeProfileAdapter(profileList, this@MainActivity)
                    layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    // adapter.notifyDataSetChanged()
                }
            } else {
                mainBinding.tvnoData.visibility = View.VISIBLE
                mainBinding.rvProfile.visibility = View.GONE
            }
        }
        profileViewModel.loadData()


    }

    override fun yesClicked(index: Int) {
        profileViewModel.onButtonClick(true, index)
        mainBinding.rvProfile.adapter?.notifyDataSetChanged()
        showToast("Profile Selected")

    }

    override fun noClicked(index: Int) {
        profileViewModel.onButtonClick(false, index)
        mainBinding.rvProfile.adapter?.notifyDataSetChanged()
        showToast("Profile Removed")
    }

    override fun onProfileClicked(profileId: Int) {
        startActivity(
            Intent(this, ProfileDetailActivity::class.java).putExtra(
                "Profileid",
                profileId
            )
        )
    }

    fun showToast(message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }
}