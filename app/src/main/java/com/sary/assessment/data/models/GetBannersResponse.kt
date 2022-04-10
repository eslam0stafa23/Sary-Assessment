package com.sary.assessment.data.models

import com.google.gson.annotations.SerializedName

data class GetBannersResponse(
  @SerializedName("result") val result: List<Banner>,
  @SerializedName("status") val status: Boolean
)