package com.klp_wita.mibu.ui.Profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.FragmentProfileOverviewBinding
import com.klp_wita.mibu.ui.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileOverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileOverviewFragment : Fragment(), View.OnClickListener {
    private lateinit var binding:FragmentProfileOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileOverviewBinding.inflate(inflater,container,false)
        val view = binding.root


        binding.btnEditProfile.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)

        return view
    }

    override fun onClick(v:View) {
        when(v.id){
            R.id.btn_edit_profile -> {
                (context as MainActivity).switchFragment(ProfileChangeFragment(),false)
            }
            R.id.btn_logout -> {
                Log.d("Profile","Logout")
            }
        }
    }


}