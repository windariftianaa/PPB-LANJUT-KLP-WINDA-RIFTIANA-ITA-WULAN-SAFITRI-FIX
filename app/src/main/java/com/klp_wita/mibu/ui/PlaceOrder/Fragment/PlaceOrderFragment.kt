package com.klp_wita.mibu.ui.PlaceOrder.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.FragmentPlaceOrderBinding
import com.klp_wita.mibu.ui.PlaceOrder.PlaceOrderActivity
import com.klp_wita.mibu.ui.PlaceOrder.ViewModel.PlaceOderViewModelFactory
import com.klp_wita.mibu.ui.PlaceOrder.ViewModel.PlaceOrderViewModel
import java.text.DecimalFormat

class PlaceOrderFragment : Fragment() {

    private lateinit var binding : FragmentPlaceOrderBinding
    private val viewmodel: PlaceOrderViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlaceOrderBinding.inflate(inflater,container,false)
        val view = binding.root
        viewmodel.initCart((activity as PlaceOrderActivity).getItemID())
        updateUI()
        viewmodel.totalitemprice.observe(viewLifecycleOwner, Observer {
            binding.edtPlaceorderTotal.setText("Rp. "+nFormatter(it.toString().toInt()))
        })
        binding.edtPlaceorderQty.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(binding.edtPlaceorderQty.length()==0){
                    binding.edtPlaceorderQty.setText("0")
                }else{
                    viewmodel.updateCartPrice(s.toString().toInt())
                }

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



        binding.btnPlaceorder.setOnClickListener {
            val edtName = binding.edtPlaceorderFullname.text.toString().trim()
            val edtAddress = binding.edtPlaceorderAddress.text.toString().trim()
            if(edtAddress.isEmpty()||edtName.isEmpty()){
                if(edtAddress.isEmpty()){
                    binding.edtPlaceorderFullname.error = "Nama tidak boleh kosong"
                }
                if(edtName.isEmpty()){
                    binding.edtPlaceorderAddress.error = "Alamat tidak boleh kosong"
                }
                return@setOnClickListener
            }else{
                viewmodel.fullname.value = edtName
                viewmodel.address.value = edtAddress
                viewmodel.save()

            }
        }

        viewmodel.savestatus.observe(viewLifecycleOwner, Observer {
            if(it){
                val fm:FragmentManager = (activity as PlaceOrderActivity).supportFragmentManager
                fm.beginTransaction()
                    .replace(R.id.placeordercontainer, PaymentFragment.newInstance())
                    .commitNow()
            }
        })


        return view
    }

    fun updateUI(){
        viewmodel.itemData.observe(viewLifecycleOwner, Observer { data ->
            binding.tvPlaceorderPrice.setText("Rp. "+ nFormatter(data[0].price) + "/kg")
            binding.tvPlaceorderFrname.setText(data[0].fruit_name)
            Glide.with(this)
                .load(data[0].photo_url)
                .override(1800,2000)
                .centerCrop()
                .into(binding.ivPlaceorderFrimage)
        })
    }

    fun nFormatter(n:Int):String{
        val dec = DecimalFormat("#,###")
        return dec.format(n).toString()
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            PlaceOrderFragment().apply {
            }
    }
}