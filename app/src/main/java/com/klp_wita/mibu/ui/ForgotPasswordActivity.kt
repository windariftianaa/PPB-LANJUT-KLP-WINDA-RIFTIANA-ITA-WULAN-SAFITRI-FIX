package com.klp_wita.mibu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klp_wita.mibu.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()
    }
}