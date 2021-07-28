package com.klp_wita.mibu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.ActivityFruitDetailBinding
import com.klp_wita.mibu.ui.PlaceOrder.PlaceOrderActivity
import com.klp_wita.mibu.ui.Profile.MapsActivity
import java.text.DecimalFormat

class FruitDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFruitDetailBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFruitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = FirebaseFirestore.getInstance()
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            val intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }

        updateUI()

        binding.btnBuy.setOnClickListener {
            val i = Intent(this, PlaceOrderActivity::class.java)
            i.putExtra("EXTRA_ITEM_ID",intent.getStringExtra("EXTRA_ITEM_ID").toString())
            startActivity(i)
        }


    }

    fun nFormatter(n:Int):String{
        val dec = DecimalFormat("#,###")
        return dec.format(n).toString()
    }

    private fun updateUI(){
        val itemid = intent.getStringExtra("EXTRA_ITEM_ID").toString()
        firestore.collection("fruit_list").document(itemid)
            .get().addOnSuccessListener { documents ->
                binding.tvFrdetailFruitname.text = documents.get("fruit_name").toString()
                binding.tvFrdetailDescription.text = documents.get("description").toString()
                binding.tvFrdetailPrice.text = "Rp. "+nFormatter(documents.get("price").toString().toInt())
                Glide.with(this)
                    .load(documents.get("photo_url").toString())
                    .override(1800,2000)
                    .centerCrop()
                    .into(binding.ivFrdetailFrimage)
                binding.toolbarLayout.title = documents.get("fruit_name").toString()
            }
    }


}