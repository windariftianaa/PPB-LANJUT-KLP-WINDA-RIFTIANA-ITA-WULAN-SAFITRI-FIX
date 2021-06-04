package com.klp_wita.mibu.adapter

import android.content.Context
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
import com.klp_wita.mibu.ui.Home.HomeViewModel


class HomeAdapter(val viewModel: HomeViewModel,val fruitListData: List<FruitListData>,val context: Context):
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
    }




    override fun getItemCount(): Int = fruitListData.size


    inner class FruitListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = RvItemListHomeBinding.bind(itemView)
        fun bind(fruitListData: FruitListData){
            binding.tvFruitPrice.text = "Rp."+fruitListData.price.toString()
            binding.tvFruitName.text = fruitListData.name
            Glide.with(itemView.context)
                .load(fruitListData.pictureURL)
                .apply(RequestOptions().override(100,100))
                .into(binding.imageViewFruitPhoto)
        }
    }
}