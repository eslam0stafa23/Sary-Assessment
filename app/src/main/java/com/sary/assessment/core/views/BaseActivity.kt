package com.sary.assessment.core.views

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

  protected var binding: T? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = this.onBind()
    setContentView(binding?.root)
    onViewReady(savedInstanceState)
  }

  abstract fun onBind(): T

  @CallSuper
  protected open fun onViewReady(savedInstanceState: Bundle?) {
  }

}