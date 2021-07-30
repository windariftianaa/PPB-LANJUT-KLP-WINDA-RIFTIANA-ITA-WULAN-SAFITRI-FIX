package com.klp_wita.mibu.ui.Modal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.klp_wita.mibu.databinding.FragmentDialogModalBinding
import com.klp_wita.mibu.ui.LoginActivity
import com.klp_wita.mibu.ui.MainActivity


class DialogModalFragment : DialogFragment() {
    private val TAG = "MyCustomDialog"
    private lateinit var binding:FragmentDialogModalBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogModalBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        mAuth = FirebaseAuth.getInstance()
        binding.btnLogoutYes.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(activity as MainActivity,LoginActivity::class.java)
            (activity as MainActivity).startActivity(intent)

        }


        binding.btnLogoutNo.setOnClickListener {
            this.dismiss()
        }

        return view
    }

}