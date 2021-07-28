package com.klp_wita.mibu.ui.PlaceOrder.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PlaceOderViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceOrderViewModel::class.java)) {
            return PlaceOrderViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}