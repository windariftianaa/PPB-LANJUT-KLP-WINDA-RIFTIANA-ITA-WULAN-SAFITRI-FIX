package com.klp_wita.mibu.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.klp_wita.mibu.R
import com.klp_wita.mibu.adapter.HomeAdapter
import com.klp_wita.mibu.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel
  private lateinit var binding: FragmentHomeBinding
  private lateinit var adapter:HomeAdapter
  private lateinit var factory: HomeViewModelFactory
  private lateinit var mRecyclerViewLocal: RecyclerView
  private lateinit var mRecyclerViewImport: RecyclerView
  private lateinit var mRecyclerViewVegetable: RecyclerView


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentHomeBinding.inflate(inflater,container,false)

    val view = binding.root

    factory = HomeViewModelFactory()
    homeViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
    homeViewModel.getFruit()

    mRecyclerViewLocal = binding.fruitrecyclerviewLocal
    mRecyclerViewLocal.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    mRecyclerViewLocal.setNestedScrollingEnabled(false);


    mRecyclerViewImport = binding.fruitrecyclerviewImport
    mRecyclerViewImport.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    mRecyclerViewImport.setNestedScrollingEnabled(false);

    mRecyclerViewVegetable = binding.fruitrecyclerviewVegetable
    mRecyclerViewVegetable.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    mRecyclerViewVegetable.setNestedScrollingEnabled(false);


    homeViewModel.lst.observe(viewLifecycleOwner, Observer { fruitList ->

      mRecyclerViewLocal.also {
        it.adapter = HomeAdapter(homeViewModel,fruitList,requireContext())
      }

      mRecyclerViewImport.also {
        it.adapter = HomeAdapter(homeViewModel,fruitList,requireContext())
      }

      mRecyclerViewVegetable.also {
        it.adapter = HomeAdapter(homeViewModel,fruitList,requireContext())
      }

    })

    return view
  }

  private fun initAdapter(){

  }





}