package com.klp_wita.mibu.ui.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.FragmentEditProfileBinding
import com.klp_wita.mibu.ui.MainActivity
import com.klp_wita.mibu.ui.Modal.DialogModalFragment
import com.klp_wita.mibu.ui.Modal.DialogSuccessModal


class EditProfileFragment : Fragment() {
    private lateinit var binding:FragmentEditProfileBinding
    private lateinit var mAuth:FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        firestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        updateUI()
        binding.btnEditprofilCancel.setOnClickListener {
            (activity as MainActivity).switchFragment(ProfileOverviewFragment(),false)
        }
        binding.btnEditprofilSave.setOnClickListener {
            updateUser()
        }


        return view
    }

    fun updateUI(){
        firestore.collection("user_details")
            .document(mAuth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                binding.tiProfileFullname.setText(it.get("name").toString())
                binding.tiProfileEmail.setText(mAuth.currentUser?.email.toString())
                binding.tiProfileHp.setText(it.get("phone").toString())
                binding.tiProfileAddress.setText(it.get("address").toString())
            }
    }

    fun updateUser(){
        val newdata:HashMap<String,String> = hashMapOf(
            "address" to binding.tiProfileAddress.text.toString().trim(),
            "name" to binding.tiProfileFullname.text.toString().trim(),
            "phone" to binding.tiProfileHp.text.toString().trim()
        )
        val email = binding.tiProfileEmail.text.toString().trim()

        firestore.collection("user_details")
            .document(mAuth.currentUser?.uid.toString())
            .set(newdata)

        if(email!=mAuth.currentUser?.email.toString()){
            val udat = hashMapOf("email" to email)
            firestore.collection("user_details")
                .document(mAuth.currentUser?.uid.toString())
                .set(email)

            mAuth.currentUser?.updateEmail(email)
        }
        val dialog = DialogSuccessModal()
        dialog.setTargetFragment(this, 1)
        dialog.show((activity as MainActivity).supportFragmentManager,"SuccessDialog")
        (activity as MainActivity).switchFragment(ProfileOverviewFragment(),false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}