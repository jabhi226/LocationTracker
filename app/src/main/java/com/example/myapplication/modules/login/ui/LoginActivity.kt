package com.example.myapplication.modules.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.modules.core.utils.SharedPref
import com.example.myapplication.modules.locationFinder.ui.NewMapActivity
import com.example.myapplication.modules.login.data.LoginApi

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var userName: EditText? = null
    private var passWord: EditText? = null
    private var loginButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userName = findViewById(R.id.driver_UN)
        passWord = findViewById(R.id.driver_PW)
        loginButton = findViewById(R.id.loginButton)
        loginButton?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == loginButton?.id) {
            SharedPref.setString(this, SharedPref.IP_ADDRESS, "192.168.0.105")
            val ip = SharedPref.getString(this, SharedPref.IP_ADDRESS)
            if (ip.isEmpty()) {
                Toast.makeText(this, "Please enter IP address.", Toast.LENGTH_SHORT).show()
            } else {
                LoginApi {
                    when (it) {
                        is LoginApi.LoginResponse.Error -> {
                            Toast.makeText(this, it.data, Toast.LENGTH_SHORT).show()
                        }

                        is LoginApi.LoginResponse.Success -> {
                            val intent = Intent(this, NewMapActivity::class.java)
                            val b = Bundle()
                            b.putParcelable("parent", it.data)
                            intent.putExtra("parent_username", userName?.text.toString())
                            intent.putExtra("bundle", b)
                            startActivity(intent)
                        }
                    }
                }.login(ip, userName?.text.toString(), passWord?.text.toString())
            }
        }
    }
}