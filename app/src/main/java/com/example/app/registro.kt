package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registro)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombreInput = findViewById<EditText>(R.id.ingrese_nombre)
        val rutInput = findViewById<EditText>(R.id.ingrese_rut)
        val passwordInput = findViewById<EditText>(R.id.ingrese_contra)
        val numeroInput = findViewById<EditText>(R.id.ingrese_num)

        // Botón para registrarse, usando  SharedPreferences
        val btnRegistrar = findViewById<Button>(R.id.elevatedButton)
        btnRegistrar.setOnClickListener {
            val nombre = nombreInput.text.toString()
            val rut = rutInput.text.toString()
            val password = passwordInput.text.toString()
            val numero = numeroInput.text.toString()

            try {
                if (nombre.isNotEmpty() && rut.isNotEmpty() && password.isNotEmpty() && numero.isNotEmpty()) {
                    // Guardar los datos en SharedPreferences
                    val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    editor.putString("nombre", nombre)
                    editor.putString("rut", rut)
                    editor.putString("password", password)
                    editor.putString("numero", numero)
                    editor.apply()

                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception) {
                Log.e("Error", e.message.toString())
            }


        }

        // Boton para ir a la pantalla de inicio de sesión
        try {
            val btnInicio: Button = findViewById(R.id.btn_a_inicio)
            btnInicio.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        } catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }

    }
}