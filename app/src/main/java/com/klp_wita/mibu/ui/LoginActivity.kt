package com.klp_wita.mibu.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewbinding.ViewBinding
import com.klp_wita.mibu.R
import com.klp_wita.mibu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener(this)

    }


    public fun goRegisterPage(v: View){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_login -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}