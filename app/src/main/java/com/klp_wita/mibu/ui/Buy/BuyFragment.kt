package com.klp_wita.mibu.ui.Buy

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klp_wita.mibu.R

class BuyFragment : Fragment() {

    companion object {
        fun newInstance() = BuyFragment()
    }

    private lateinit var viewModel: BuyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}