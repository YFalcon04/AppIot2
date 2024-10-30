package com.example.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class configuracion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configuracion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.configuracion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Boton para cerrar sesión
        try {
            val cambiarPantalla6: Button = findViewById(R.id.btnCerrarSesión)

            cambiarPantalla6.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }catch (e:Exception){
            Log.e("Error", e.message.toString())
        }

        //Boton para volver a la vista principal
        try {
            val cambiarPantalla5: Button = findViewById(R.id.btnVolverPrincipal)

            cambiarPantalla5.setOnClickListener{
                val intent = Intent(this,principal::class.java)
                startActivity(intent)
            }
        }catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }

    }
}