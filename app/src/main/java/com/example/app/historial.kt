package com.example.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//CLASE
data class Tabla_Historial(
    val usuario: String, var accion: String, var fecha: String
){
    override fun toString(): String {
        return "$usuario - $accion - $fecha"
    }
}

class historial : AppCompatActivity() {

    //inicio de variables

    private lateinit var editUsuario: EditText
    private lateinit var editAccion: EditText
    private lateinit var editFecha: EditText
    private lateinit var botonAgregar: Button
    private lateinit var botonActualizar: Button
    private lateinit var botonEliminar: Button
    private lateinit var lista: ListView
    private val estList = ArrayList<Tabla_Historial>()
    private lateinit var adapter: ArrayAdapter<Tabla_Historial>
    private var selectedIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.historial)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editUsuario = findViewById(R.id.edit_text_usuario)
        editAccion = findViewById(R.id.edit_text_accion)
        editFecha = findViewById(R.id.edit_text_fecha)
        botonAgregar = findViewById(R.id.btn_agregar)
        botonActualizar = findViewById(R.id.btn_actualizar)
        botonEliminar = findViewById(R.id.btn_eliminar)

        lista = findViewById(R.id.lista_historial)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, estList)
        lista.adapter = adapter

        botonAgregar.setOnClickListener { agregarUsoPorton() }
        botonActualizar.setOnClickListener { actualizarUsoPorton() }
        botonEliminar.setOnClickListener { eliminarUsoPorton() }

        lista.setOnItemClickListener { _, _,  position, _ ->
            selectedIndex = position
            val usoSeleccionado = estList[position]
            editUsuario.setText(usoSeleccionado.usuario)
            editAccion.setText(usoSeleccionado.accion)
            editFecha.setText(usoSeleccionado.fecha)
        }

        //Boton para volver a la pantalla principal
        try {
            val cambiarPantalla4: Button = findViewById(R.id.btn_volverMenu)

            cambiarPantalla4.setOnClickListener{
                val intent = Intent(this,principal::class.java)
                startActivity(intent)
            }
        }catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }


        //Boton para ir a la configuracion
        try {
            val cambiarAConfig: Button = findViewById(R.id.btnConfiguracion)

            cambiarAConfig.setOnClickListener{
                val intent = Intent(this,configuracion::class.java)
                startActivity(intent)
            }
        }catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }

    }


    private fun agregarUsoPorton() {
        val usuario = editUsuario.text.toString()
        val accion = editAccion.text.toString()
        val fecha = editFecha.text.toString()


        if (usuario.isNotEmpty() && accion.isNotEmpty() && fecha.isNotEmpty()) {
            estList.add(Tabla_Historial(usuario, accion, fecha))
            adapter.notifyDataSetChanged()
            editUsuario.text.clear()
            editAccion.text.clear()
            editFecha.text.clear()
            Toast.makeText(this, "Acción agregada al historial", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Complete todos los campos para agregar la acción", Toast.LENGTH_SHORT).show()
        }
    }


    private fun actualizarUsoPorton() {
        if (selectedIndex >= 0) {
            val usuario = editUsuario.text.toString()
            val accion = editAccion.text.toString()
            val fecha = editFecha.text.toString()
            if (usuario.isNotEmpty() && accion.isNotEmpty() && fecha.isNotEmpty()) {

                estList[selectedIndex] = Tabla_Historial(usuario, accion, fecha)
                adapter.notifyDataSetChanged()

                editUsuario.text.clear()
                editAccion.text.clear()
                editFecha.text.clear()
                selectedIndex = -1
                Toast.makeText(this, "Información actualizada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Complete todos los campos para actualizar la información", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Selecciona una acción para actualizar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eliminarUsoPorton() {
        if (selectedIndex >= 0) {

            estList.removeAt(selectedIndex)
            adapter.notifyDataSetChanged()

            editUsuario.text.clear()
            editAccion.text.clear()
            editFecha.text.clear()
            selectedIndex = -1
            Toast.makeText(this, "Acción eliminada del historial", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Selecciona una acción para eliminar", Toast.LENGTH_SHORT).show()
        }
    }

}



