package com.sary.assessment.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sary.assessment.core.functions.loadImage
import com.sary.assessment.data.models.Catalog
import com.sary.assessment.data.models.CatalogItem
import com.sary.assessment.databinding.ItemGroupBinding
import com.sary.assessment.databinding.ItemSmartBinding
import com.squareup.picasso.Picasso

class GroupViewAdapter(private val catalogItemsList: List<CatalogItem>) :
  RecyclerView.Adapter<GroupViewAdapter.ViewHolder>() {

  override fun getItemCount(): Int = catalogItemsList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
    ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) =
    holder.bind(catalogItemsList[position])

  inner class ViewHolder(private val binding: ItemGroupBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(catalogItem: CatalogItem) {
      binding.apply {
        item.setOnClickListener { Toast.makeText(root.context, "${catalogItem.name}", Toast.LENGTH_SHORT).show() }
        ivIcon.loadImage(catalogItem.image)
      }

    }
  }
}