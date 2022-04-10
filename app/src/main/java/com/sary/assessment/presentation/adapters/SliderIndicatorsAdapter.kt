package com.sary.assessment.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sary.assessment.R
import com.sary.assessment.data.models.Banner
import com.sary.assessment.databinding.ItemIndicatorBinding

class SliderIndicatorsAdapter(private val imagesList: List<Banner>?) :
  RecyclerView.Adapter<SliderIndicatorsAdapter.ViewHolder>() {

  override fun getItemCount(): Int = imagesList?.size ?: 0

  fun setItemActive(position: Int) {
    imagesList?.forEach {
      it.isActive = false
    }
    val item = imagesList?.get(position)
    item?.isActive = true
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
    ItemIndicatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) =
    holder.bind(imagesList?.get(position))

  inner class ViewHolder(private val binding: ItemIndicatorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context
    fun bind(banner: Banner?) {
      binding.indicator.apply {
        if (banner?.isActive == false)
          setBackgroundColor(context.getColor(R.color.gray_background))
        else
          setBackgroundColor(context.getColor(R.color.gray))
      }
    }
  }
}