package com.klp_wita.mibu.ui.Buy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class BuyViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuyViewModel::class.java)) {
            return BuyViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}