package com.klp_wita.mibu.model

import android.app.ActivityManager

data class FruitListData(
    var id:String = "",
    val fruit_name: String = "",
    val photo_url: String = "",
    val price: Int =0,
    val description: String = "",
    val category: Int = 0
)
