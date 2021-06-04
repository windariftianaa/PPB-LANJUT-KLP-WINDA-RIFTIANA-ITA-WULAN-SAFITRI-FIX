package com.klp_wita.mibu.ui.Home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klp_wita.mibu.R
import com.klp_wita.mibu.model.FruitListData
import kotlin.coroutines.coroutineContext

class HomeViewModel() : ViewModel() {

    var lst = MutableLiveData<ArrayList<FruitListData>>()
    var newlist = arrayListOf<FruitListData>()

    fun getFruit(){
        newlist.addAll(generateFruit())
        lst.value = newlist
    }

    fun generateFruit(): ArrayList<FruitListData> {


        val listmydata = arrayListOf<FruitListData>()

        for (i in 1..4){
            val myData = FruitListData(
                "Durian",
                "https://is2-ssl.mzstatic.com/image/thumb/Purple114/v4/d6/49/75/d6497519-5b53-2f77-1071-24997a0ac535/source/512x512bb.jpg",
                15000
            )
            listmydata.add(myData)
        }
        return listmydata
    }

}