package com.klp_wita.mibu.ui.PlaceOrder.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.FragmentPaymentBinding
import com.klp_wita.mibu.ui.PlaceOrder.PlaceOrderActivity
import com.klp_wita.mibu.ui.PlaceOrder.ViewModel.PlaceOrderViewModel


class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater,container,false)
        val view = binding.root
        val model : PlaceOrderViewModel by activityViewModels()

        model.totalitemprice.observe(viewLifecycleOwner, Observer {
            val nominal = ((activity as PlaceOrderActivity).nFormatter(it))
            binding.tvPaymentNominal.setText("Rp. "+nominal)
        })
        binding.btnPaymentPaid.setOnClickListener {
            val fm: FragmentManager = (activity as PlaceOrderActivity).supportFragmentManager
            fm.beginTransaction()
                .replace(R.id.placeordercontainer, PlaceOrderConfirmationFragment.newInstance())
                .commitNow()
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaymentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            PaymentFragment().apply {
            }
    }
}