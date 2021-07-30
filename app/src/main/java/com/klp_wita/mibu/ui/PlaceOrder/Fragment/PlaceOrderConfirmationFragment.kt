package com.klp_wita.mibu.ui.PlaceOrder.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.FragmentPlaceOrderConfirmationBinding
import com.klp_wita.mibu.ui.PlaceOrder.PlaceOrderActivity
import com.klp_wita.mibu.ui.PlaceOrder.ViewModel.PlaceOrderViewModel


class PlaceOrderConfirmationFragment : Fragment() {
    private lateinit var binding:FragmentPlaceOrderConfirmationBinding
    private val model : PlaceOrderViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlaceOrderConfirmationBinding.inflate(inflater,container,false)
        val view = binding.root

        updateUI()
        return view
    }

    fun updateUI(){

        val tprice = "Rp. "+(activity as PlaceOrderActivity).nFormatter(model.totalitemprice.value.toString().toInt())
        val shipprice = "Rp. "+(activity as PlaceOrderActivity).nFormatter(model.shippingcharge)
        val itemprice = "Rp. "+(activity as PlaceOrderActivity).nFormatter(model.itemprice.value.toString().toInt())
        binding.tvOrderconfirmationFullname.setText(model.fullname.value)
        binding.tvOrderconfirmationPhone.setText("-")
        binding.tvOrderconfirmationAddress.setText(model.address.value)
        binding.tvOrderconfirmationItemqty.setText(model.itemqty.value.toString())
        binding.tvOrderconfirmationShippingprice.setText(shipprice)
        binding.tvOrderconfirmationTotalprice.setText(tprice)
        binding.tvOrderconfirmationOrderid.setText("#"+model.currentOrderID.value)
        model.itemData.observe(viewLifecycleOwner, Observer {
            binding.tvOrderconfirmationItemname.setText(it.get(0).fruit_name)
            binding.tvOrderconfirmationItemprice.setText(itemprice)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlaceOrderConfirmationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            PlaceOrderConfirmationFragment().apply {

            }
    }
}