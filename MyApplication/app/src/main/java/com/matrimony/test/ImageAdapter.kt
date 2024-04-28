package com.matrimony.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.matrimony.test.databinding.DetailImageViewBinding

class ImageAdapter(val imageList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageAdapterViewHolder>() {

    class ImageAdapterViewHolder(val binding: DetailImageViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapterViewHolder {
        val binding =
            DetailImageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageAdapterViewHolder, position: Int) {
        val url = imageList[position]
      with(holder.binding){
          profileItemImage.load(url)
      }
    }
}