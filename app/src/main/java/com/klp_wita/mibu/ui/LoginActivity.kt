package com.klp_wita.mibu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener{
            login()
        }
        binding.registerBtn.setOnClickListener {
            goRegisterPage()
        }
        binding.tbForgotPassword.setOnClickListener {
            goForgotPasswordPage()
        }

    }


    public fun goRegisterPage(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    public fun goForgotPasswordPage(){
        val intent = Intent(this,ForgotPasswordActivity::class.java)
        startActivity(intent)
    }



    private fun login(){
        val edtPasswod = binding.tiLoginPassword.text.toString().trim()
        val edtEmail = binding.tiLoginEmail.text.toString().trim()

        if(edtEmail.isEmpty()||edtPasswod.isEmpty()||edtPasswod.length<6){
            if(edtEmail.isEmpty()){
                binding.emailEditText.error = "Email harus diisi"
            }
            if(edtPasswod.isEmpty()){
                binding.passwordEditText.error = "Password harus diisi"
            }
            if(edtPasswod.length<6){
                binding.passwordEditText.error = "Password minimal 6 karakter"
            }
            return
        }else{

            auth.signInWithEmailAndPassword(edtEmail,edtPasswod)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        user?.sendEmailVerification()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{

                        binding.passwordEditText.error = "Email atau Password salah"
                    }
                }
        }
    }

}