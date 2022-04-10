package com.sary.assessment.presentation.annotations

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class CatalogDataType {
  companion object {
    const val SMART = "smart"
    const val GROUP = "group"
    const val BANNER = "banner"
  }
}