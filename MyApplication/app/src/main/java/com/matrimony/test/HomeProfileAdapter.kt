package com.matrimony.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.matrimony.test.databinding.HomeCardBinding

class HomeProfileAdapter(val profileList: ArrayList<ProfileDatabase.Profile>,val homeListener: HomeListener) :
    RecyclerView.Adapter<HomeProfileAdapter.HomeProfileViewHolder>() {


    class HomeProfileViewHolder(val binding: HomeCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProfileViewHolder {
        val binding = HomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: HomeProfileViewHolder, position: Int) {
        val profile = profileList[position]
        with(holder.binding) {
            ivProfileimg.load("https://source.unsplash.com/300x300/")
            tvName.text = profile.name
            tvProfileDetails.text = "${profile.age} Yrs, ${profile.height} ${profile.profession} ${profile.address}"
            btnYes.setOnClickListener {
                homeListener.yesClicked(position)
            }
            btnNo.setOnClickListener{
                homeListener.noClicked(position)
            }
            root.setOnClickListener {
                homeListener.onProfileClicked(profileId = profile.id?:0)
            }
        }
    }

}