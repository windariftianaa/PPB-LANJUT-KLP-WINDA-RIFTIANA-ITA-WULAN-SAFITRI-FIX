package com.klp_wita.mibu.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.RvItemListHomeBinding
import com.klp_wita.mibu.model.FruitListData
import com.klp_wita.mibu.ui.FruitDetailActivity
import com.klp_wita.mibu.ui.Home.HomeViewModel
import com.klp_wita.mibu.ui.MainActivity
import java.text.DecimalFormat
import java.text.NumberFormat


class HomeAdapter(val fruitListData: List<FruitListData>, val itemLayout:Int):
    RecyclerView.Adapter<HomeAdapter.FruitListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FruitListViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_list_home, parent,false)
        return FruitListViewHolder(root)

    }

    override fun onBindViewHolder(holder: FruitListViewHolder, position: Int) {
        holder.bind(fruitListData.get(position))
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,FruitDetailActivity::class.java)
            intent.putExtra("EXTRA_ITEM_ID",fruitListData.get(position).id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = fruitListData.size

    fun nFormatter(n:Int):String{
        val dec = DecimalFormat("#,###")
        return dec.format(n).toString()
    }

    inner class FruitListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = RvItemListHomeBinding.bind(itemView)

        fun bind(fruitListData: FruitListData){
            binding.tvFruitPrice.text = "Rp."+nFormatter(fruitListData.price)
            binding.tvFruitName.text = fruitListData.fruit_name.toString()
            Glide.with(itemView.context)
                .load(fruitListData.photo_url)
                .apply(RequestOptions().override(100,100))
                .into(binding.imageViewFruitPhoto)
        }
    }
}


