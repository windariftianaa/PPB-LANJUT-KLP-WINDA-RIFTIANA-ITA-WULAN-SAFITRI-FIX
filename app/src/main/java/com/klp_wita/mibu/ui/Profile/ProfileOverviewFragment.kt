package com.klp_wita.mibu.ui.Profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.klp_wita.mibu.databinding.FragmentProfileOverviewBinding
import com.klp_wita.mibu.ui.Modal.DialogModalFragment
import com.klp_wita.mibu.ui.MainActivity

class ProfileOverviewFragment : Fragment() {
    private lateinit var binding: FragmentProfileOverviewBinding
    private lateinit var mAuth:FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileOverviewBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        binding.btnProfileoverviewEdit.setOnClickListener {
            Log.d("Profile","Clicked")
            (activity as MainActivity).switchFragment(EditProfileFragment(),false)
        }

        binding.btnProfileLogout.setOnClickListener(View.OnClickListener {
            val dialog = DialogModalFragment()
            dialog.setTargetFragment(this, 1)
            dialog.show((activity as MainActivity).supportFragmentManager,"MyCustomDialog")
        })

        updateUI()
        return view
    }

    private fun updateUI(){
        firestore.collection("user_details")
            .document(mAuth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                binding.tvOverviewprofileName.setText(it.get("name").toString())
                binding.tvOverviewprofileEmail.setText(mAuth.currentUser?.email.toString())
                binding.tvOverviewprofileAddress.setText(it.get("address").toString())
                binding.tvOverviewprofileHp.setText(it.get("phone").toString())
            }
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileOverviewFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}