package com.sary.assessment.data.models

import com.google.gson.annotations.SerializedName

data class GetCatalogResponse(
  @SerializedName("message") val message: String,
  @SerializedName("other") val other: Other,
  @SerializedName("result") val catalogs: List<Catalog>,
  @SerializedName("status") val status: Boolean
) {
  data class Other(
    @SerializedName("business_status") val businessStatus: BusinessStatus,
    @SerializedName("show_special_order_view") val showSpecialOrderView: Boolean,
    @SerializedName("uncompleted_profile_settings") val uncompletedProfileSettings: UncompletedProfileSettings
  ) {
    data class BusinessStatus(
      @SerializedName("id") val id: Int,
      @SerializedName("title") val title: String
    )

    data class UncompletedProfileSettings(
      @SerializedName("image") val image: String,
      @SerializedName("is_completed_profile") val isCompletedProfile: Boolean,
      @SerializedName("message") val message: String,
      @SerializedName("show_tag") val showTag: Boolean
    )
  }

}