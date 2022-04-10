package com.sary.assessment.core.functions

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sary.assessment.data.models.Banner
import com.sary.assessment.presentation.adapters.ImagesSliderAdapter
import com.sary.assessment.presentation.adapters.SliderIndicatorsAdapter

fun ViewPager2.setImages(images: List<Banner>) {
  orientation = ViewPager2.ORIENTATION_HORIZONTAL
  adapter = ImagesSliderAdapter(images)
  currentItem = 0

  registerOnPageChangeCallback(
    object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        val parentLayout = this@setImages.parent as ConstraintLayout
        val indicatorsRecyclerView = parentLayout.getChildAt(1) as RecyclerView
        indicatorsRecyclerView.setIndicatorActive(position)
        indicatorsRecyclerView.smoothScrollToPosition(position + 2)
      }
    }
  )
}

fun RecyclerView.addIndicators(images: List<Banner>?) {
  this.apply {
    adapter = SliderIndicatorsAdapter(images)
    setHasFixedSize(true)
    layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    adapter = adapter
  }
}

fun RecyclerView.setIndicatorActive(position: Int) {
  val adapter = this.adapter as SliderIndicatorsAdapter
  adapter.setItemActive(position)
  adapter.notifyDataSetChanged()
}

