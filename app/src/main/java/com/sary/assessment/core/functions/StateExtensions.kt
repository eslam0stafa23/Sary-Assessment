package com.sary.assessment.core.functions

import android.view.View
import com.sary.assessment.databinding.LayoutStateBinding

fun LayoutStateBinding.startLoading() {
  layoutBackgroundProgress.show()
  layoutLoading.show()
  layoutConnectionError.hide()
  layoutError.hide()
}

fun LayoutStateBinding.stopLoading() {
  layoutBackgroundProgress.hide()
  layoutLoading.hide()
}

fun LayoutStateBinding.showErrorMessage(errorMessage: String) {
  layoutBackgroundProgress.show()
  layoutLoading.hide()
  layoutConnectionError.hide()
  tvError.show()
  tvError.text = errorMessage
  layoutError.show()
}

fun LayoutStateBinding.showConnectionError(
  errorMessage: String,
  btnText: String,
  action: View.OnClickListener
) {
  layoutBackgroundProgress.show()
  layoutLoading.hide()
  layoutError.hide()
  tvConnectionError.show()
  tvConnectionError.text = errorMessage
  layoutConnectionError.show()
  btnConnectionError.text = btnText
  btnConnectionError.setOnClickListener(action)
}