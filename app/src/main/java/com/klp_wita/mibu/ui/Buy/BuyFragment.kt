package com.klp_wita.mibu.ui.Buy

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.klp_wita.mibu.R
import com.klp_wita.mibu.adapter.HomeAdapter
import com.klp_wita.mibu.databinding.BuyFragmentBinding
import com.klp_wita.mibu.databinding.FragmentHomeBinding
import com.klp_wita.mibu.model.FruitListData
import com.klp_wita.mibu.ui.Home.HomeViewModel
import com.klp_wita.mibu.ui.Home.HomeViewModelFactory

class BuyFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: BuyFragmentBinding
    private lateinit var factory: HomeViewModelFactory
    private lateinit var mRecyclerViewLocal: RecyclerView
    private lateinit var mRecyclerViewImport: RecyclerView
    private lateinit var mRecyclerViewVegetable: RecyclerView
    private var _fruitlistLocal = ArrayList<FruitListData>()
    private var _fruitlistImport = ArrayList<FruitListData>()
    private var _fruitlistSayur = ArrayList<FruitListData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BuyFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        activity?.actionBar?.setDisplayHomeAsUpEnabled(false)

        factory = HomeViewModelFactory()
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        homeViewModel.fetchCategorizedFruit()

        mRecyclerViewLocal = binding.fruitrecyclerviewLocal
        mRecyclerViewLocal.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        mRecyclerViewLocal.setNestedScrollingEnabled(false);
        mRecyclerViewLocal.adapter = HomeAdapter(_fruitlistLocal, R.layout.rv_item_list_home)

        mRecyclerViewImport = binding.fruitrecyclerviewImport
        mRecyclerViewImport.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        mRecyclerViewImport.setNestedScrollingEnabled(false);
        mRecyclerViewImport.adapter = HomeAdapter(_fruitlistImport, R.layout.rv_item_list_home)

        mRecyclerViewVegetable = binding.fruitrecyclerviewVegetable
        mRecyclerViewVegetable.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        mRecyclerViewVegetable.setNestedScrollingEnabled(false);
        mRecyclerViewVegetable.adapter = HomeAdapter(_fruitlistSayur, R.layout.rv_item_list_home)

        homeViewModel.localFruit.observe(viewLifecycleOwner, Observer { fruitlistl ->
            _fruitlistLocal.removeAll(_fruitlistLocal)
            _fruitlistLocal.addAll(fruitlistl)
            mRecyclerViewLocal.adapter!!.notifyDataSetChanged()
        })

        homeViewModel.importFruit.observe(viewLifecycleOwner, Observer { fruitlisti ->
            _fruitlistImport.removeAll(_fruitlistImport)
            _fruitlistImport.addAll(fruitlisti)
            mRecyclerViewImport.adapter!!.notifyDataSetChanged()
        })

        homeViewModel.sayurFruit.observe(viewLifecycleOwner, Observer { fruitlists ->
            _fruitlistSayur.removeAll(_fruitlistSayur)
            _fruitlistSayur.addAll(fruitlists)
            mRecyclerViewVegetable.adapter!!.notifyDataSetChanged()
        })



        return view
    }

}