package com.sary.assessment.core.api

import androidx.annotation.Keep

@Keep
sealed class NetworkState<T> {
  class Loading<T> : NetworkState<T>()
  data class Success<T>(val data: T) : NetworkState<T>()
  class Error<T> : NetworkState<T>()
}