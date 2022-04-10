package com.sary.assessment.data.models

import com.google.gson.annotations.SerializedName

data class Banner(
  @SerializedName("id") val id: Int?,
  @SerializedName("image") val image: String?,
  @SerializedName("is_available") val isAvailable: Boolean?,
  @SerializedName("photo") val photo: String,
  var isActive: Boolean = false
)