package com.klp_wita.mibu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.children
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()
        binding.btnRegister.setOnClickListener {
            register()
        }

    }

    private fun register(){
        val edtName = binding.tiRegisterName.text.toString().trim()
        val edtEmail = binding.tiRegisterEmail.text.toString().trim()
        val edtPasswod = binding.tiPassword.text.toString().trim()
        val edtPhone = binding.tiPhone.text.toString().trim()
        if(edtName.isEmpty()||edtEmail.isEmpty()||edtPasswod.isEmpty()||edtPhone.isEmpty()){
            if(edtName.isEmpty()){
                binding.registerFullnameEdtText.error = "Nama Harus diisi"
            }
            if(edtEmail.isEmpty()){
                binding.registerEmailEdtText.error = "Email Harus diisi"
            }
            if(edtPasswod.isEmpty()){
                binding.registerPasswordEdtText.error = "Password Harus diisi"
            }
            if(edtPhone.isEmpty()){
                binding.registerPhoneEdtText.error = "Nomor HP Harus diisi"
            }
            return
        }else{
            val userData = hashMapOf(
                "email" to edtEmail,
                "name" to edtName,
                "phone" to edtPhone,
                "address" to "Purwokerto"
            )
            auth.createUserWithEmailAndPassword(edtEmail,edtPasswod)
                .addOnCompleteListener{

                    if(it.isSuccessful){
                        val user = auth.currentUser
                        register2(userData,user?.uid.toString())

                    }else{
                        Toast.makeText(this,"Register Failed",Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener {
                    Log.e("REGISTER","Failed register",it)
                }
        }

    }

    private fun register2(userData:HashMap<String, String>,UID:String){

        firestore.collection("user_details")
            .document(UID)
            .set(userData)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Register Failed",Toast.LENGTH_LONG).show()
                }
            }

    }
}