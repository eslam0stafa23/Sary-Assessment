package com.sary.assessment.data.models

import com.google.gson.annotations.SerializedName

data class Catalog(
  @SerializedName("data") val items: List<CatalogItem>,
  @SerializedName("data_type") val dataType: String?,
  @SerializedName("id") val id: Int?,
  @SerializedName("row_count") val rowCount: Int,
  @SerializedName("show_title") val showTitle: Boolean,
  @SerializedName("title") val title: String?,
  @SerializedName("ui_type") val uiType: String?
) {

  val notNullItems: List<CatalogItem>
    get() {
      return items.filter {
        !it.image.isNullOrEmpty()
      }
    }
}