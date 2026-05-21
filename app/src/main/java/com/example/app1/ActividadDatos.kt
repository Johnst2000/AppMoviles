package com.example.app1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActividadDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_datos)

        val cedula = intent.getStringExtra("cedula") ?: ""
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val fecha = intent.getStringExtra("fecha") ?: ""
        val ciudad = intent.getStringExtra("ciudad") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val telefono = intent.getStringExtra("telefono") ?: ""
        val genero = intent.getStringExtra("genero") ?: ""

        findViewById<TextView>(R.id.tvCedula).text = cedula
        findViewById<TextView>(R.id.tvNombre).text = nombre
        findViewById<TextView>(R.id.tvApellido).text = apellido
        findViewById<TextView>(R.id.tvFecha).text = fecha
        findViewById<TextView>(R.id.tvCiudad).text = ciudad
        findViewById<TextView>(R.id.tvCorreo).text = correo
        findViewById<TextView>(R.id.tvTelefono).text = telefono
        findViewById<TextView>(R.id.tvGenero).text = genero

        findViewById<Button>(R.id.btnRegresar).setOnClickListener {
            finish()
        }
    }
}