package com.sary.assessment.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sary.assessment.core.functions.loadImage
import com.sary.assessment.data.models.CatalogItem
import com.sary.assessment.databinding.ItemSmartBinding
import com.squareup.picasso.Picasso

class SmartViewAdapter(private val catalogItemsList: List<CatalogItem>) :
  RecyclerView.Adapter<SmartViewAdapter.ViewHolder>() {

  override fun getItemCount(): Int = catalogItemsList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
    ItemSmartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) =
    holder.bind(catalogItemsList[position])

  inner class ViewHolder(private val binding: ItemSmartBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(catalogItem: CatalogItem) {
      binding.apply {
        item.setOnClickListener { Toast.makeText(root.context, "${catalogItem.name}", Toast.LENGTH_SHORT).show() }
        tvTitle.text = catalogItem.name
        ivIcon.loadImage(catalogItem.image)
      }

    }
  }
}