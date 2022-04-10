package com.sary.assessment.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sary.assessment.core.functions.loadImage
import com.sary.assessment.data.models.Banner
import com.sary.assessment.databinding.ItemImageBinding

class ImagesSliderAdapter(private val imagesList: List<Banner>) :
  RecyclerView.Adapter<ImagesSliderAdapter.ViewPagerViewHolder>() {

  override fun getItemCount(): Int = imagesList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPagerViewHolder(
    ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) =
    holder.bind(imagesList[position])

  inner class ViewPagerViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(banner: Banner) = binding.ivImage.loadImage(banner.photo)
  }
}