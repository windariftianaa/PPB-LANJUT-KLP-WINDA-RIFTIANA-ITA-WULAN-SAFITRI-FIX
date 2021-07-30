package com.klp_wita.mibu.ui.Profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.ProfileFragmentBinding
import com.klp_wita.mibu.ui.MainActivity

class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: ProfileFragmentBinding

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container_profileDetails,ProfileOverviewFragment())
            .commit()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onClick(v: View) {
        TODO("Not yet implemented")
    }

}