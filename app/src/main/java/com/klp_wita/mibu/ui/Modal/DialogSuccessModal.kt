package com.klp_wita.mibu.ui.Modal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.FragmentDialogModalBinding
import com.klp_wita.mibu.databinding.FragmentDialogSuccessModalBinding
import com.klp_wita.mibu.ui.LoginActivity
import com.klp_wita.mibu.ui.MainActivity


class DialogSuccessModal : DialogFragment() {


    private val TAG = "SuccessDialog"
    private lateinit var binding: FragmentDialogSuccessModalBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogSuccessModalBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        mAuth = FirebaseAuth.getInstance()



        binding.btnModalsuccessClose.setOnClickListener {
            this.dismiss()
        }

        return view
    }


}