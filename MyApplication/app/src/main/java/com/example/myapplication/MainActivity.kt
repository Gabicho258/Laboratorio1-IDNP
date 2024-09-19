package com.example.myapplication

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            if (userName =="admin" && password == "admin"){
                Toast.makeText(getApplicationContext(),"Bienvenido a mi app", Toast.LENGTH_SHORT).show()
                Log.d("Login successful", "Bienvenido a mi app")
            }else {
                Toast.makeText(getApplicationContext(),"Error en la autenticacion", Toast.LENGTH_SHORT).show()
                Log.d("Login failed", "Error en la autenticacion")
            }
        }

    }
}