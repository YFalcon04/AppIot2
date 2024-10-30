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
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnIniciarSesion = findViewById<Button>(R.id.btninicio_sesion)
        val nombreUsuarioInput = findViewById<EditText>(R.id.nombreUsuario)
        val passwordInput = findViewById<EditText>(R.id.contra)

        //Boton para iniciar sesion, con los datos que se ingresaron en el registro con SharedPreferences

        btnIniciarSesion.setOnClickListener {
            try {
                val nombreIngresado = nombreUsuarioInput.text.toString().trim() // Eliminar espacios en blanco
                val passwordIngresado = passwordInput.text.toString().trim()    // Eliminar espacios en blanco

                if (nombreIngresado.isEmpty() || passwordIngresado.isEmpty()) {
                    Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Cargar los datos guardados en SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val nombreGuardado = sharedPreferences.getString("nombre", "")
                val passwordGuardado = sharedPreferences.getString("password", "")

                // Verificar si los valores ingresados coinciden con los guardados
                if (nombreIngresado == nombreGuardado && passwordIngresado == passwordGuardado) {
                    // Inicio de sesión exitoso
                    val intent = Intent(this, principal::class.java)
                    startActivity(intent)
                } else {
                    // Nombre o contraseña incorrectos
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception) {
                Log.e("Error", e.message.toString())
            }

        }


        //Boton para ir a la pantalla para registrar usuario
       try {
           val cambiarPantalla2: Button = findViewById(R.id.btnregistrarse1)
           cambiarPantalla2.setOnClickListener{
               val intent = Intent(this,registro::class.java)
               startActivity(intent)
           }
       } catch (e:Exception) {
           Log.e("Error", e.message.toString())
       }

    }
}