package com.sary.assessment.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sary.assessment.core.functions.loadImage
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.data.models.CatalogItem
import com.sary.assessment.databinding.ItemBannerBinding
import com.sary.assessment.databinding.ItemGroupBinding
import com.sary.assessment.databinding.ItemSmartBinding
import com.squareup.picasso.Picasso

class BannerViewAdapter(private val catalogItemsList: List<CatalogItem>) :
  RecyclerView.Adapter<BannerViewAdapter.ViewHolder>() {

  override fun getItemCount(): Int = catalogItemsList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
    ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) =
    holder.bind(catalogItemsList[position])

  inner class ViewHolder(private val binding: ItemBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(catalogItem: CatalogItem) {
      binding.apply {
        item.setOnClickListener { Toast.makeText(root.context, "${catalogItem.name}", Toast.LENGTH_SHORT).show() }
        ivImage.loadImage(catalogItem.image)
      }
    }
  }
}