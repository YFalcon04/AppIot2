package com.example.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Boton para ir al historial de uso del porton
        try {
            val cambiarPantalla3: Button = findViewById(R.id.btnHistorial)

            cambiarPantalla3.setOnClickListener{
                val intent = Intent(this,historial::class.java)
                startActivity(intent)
            }
        } catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }


        //Boton para ir a la configuración
        try {
            val cambiarAConfi: Button = findViewById(R.id.btnConfiguracion2)

            cambiarAConfi.setOnClickListener{
                val intent = Intent(this,configuracion::class.java)
                startActivity(intent)
            }
        } catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }

        //Boton para cerrar sesión
        try {
            val cambiarPantalla7: Button = findViewById(R.id.btnCerrarSesión)

            cambiarPantalla7.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }catch (e:Exception){
            Log.e("Error", e.message.toString())
        }

    }
}