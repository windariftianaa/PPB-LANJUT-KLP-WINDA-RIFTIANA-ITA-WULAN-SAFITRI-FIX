package com.klp_wita.mibu.ui.PlaceOrder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klp_wita.mibu.R
import com.klp_wita.mibu.ui.PlaceOrder.Fragment.PlaceOrderFragment
import java.text.DecimalFormat

class PlaceOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.placeordercontainer, PlaceOrderFragment.newInstance())
                .commitNow()
        }

        val itemid = intent.getStringExtra("EXTRA_ITEM_ID").toString()

    }
    fun nFormatter(n:Int):String{
        val dec = DecimalFormat("#,###")
        return dec.format(n).toString()
    }


    fun getItemID():String = intent.getStringExtra("EXTRA_ITEM_ID").toString()

}