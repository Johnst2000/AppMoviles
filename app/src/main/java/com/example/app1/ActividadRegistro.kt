package com.example.app1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class ActividadRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etCedula = findViewById<TextInputEditText>(R.id.etCedula)
        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val etApellido = findViewById<TextInputEditText>(R.id.etApellido)
        val etFecha = findViewById<TextInputEditText>(R.id.etFechaNacimiento)
        val etCiudad = findViewById<TextInputEditText>(R.id.etCiudad)
        val etCorreo = findViewById<TextInputEditText>(R.id.etCorreo)
        val etTelefono = findViewById<TextInputEditText>(R.id.etTelefono)
        val rgGenero = findViewById<RadioGroup>(R.id.rgGenero)

        val tilCedula = findViewById<TextInputLayout>(R.id.tilCedula)
        val tilNombre = findViewById<TextInputLayout>(R.id.tilNombre)
        val tilApellido = findViewById<TextInputLayout>(R.id.tilApellido)
        val tilFecha = findViewById<TextInputLayout>(R.id.tilFechaNacimiento)
        val tilCiudad = findViewById<TextInputLayout>(R.id.tilCiudad)
        val tilCorreo = findViewById<TextInputLayout>(R.id.tilCorreo)
        val tilTelefono = findViewById<TextInputLayout>(R.id.tilTelefono)

        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        etFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, anioSel, mesSel, diaSel ->
                val fechaFormateada = "%02d/%02d/%04d".format(diaSel, mesSel + 1, anioSel)
                etFecha.setText(fechaFormateada)
            }, anio, mes, dia)

            datePicker.show()
        }

        btnLimpiar.setOnClickListener {
            etCedula.text?.clear()
            etNombre.text?.clear()
            etApellido.text?.clear()
            etFecha.text?.clear()
            etCiudad.text?.clear()
            etCorreo.text?.clear()
            etTelefono.text?.clear()
            rgGenero.check(R.id.rbMasculino)

            tilCedula.error = null
            tilNombre.error = null
            tilApellido.error = null
            tilFecha.error = null
            tilCiudad.error = null
            tilCorreo.error = null
            tilTelefono.error = null
        }

        btnEnviar.setOnClickListener {
            var hayErrores = false

            val cedula = etCedula.text.toString().trim()
            if (cedula.isEmpty()) {
                tilCedula.error = "Ingrese su cédula"
                hayErrores = true
            } else if (cedula.length != 10) {
                tilCedula.error = "La cédula debe tener 10 dígitos"
                hayErrores = true
            } else {
                tilCedula.error = null
            }

            val nombre = etNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                tilNombre.error = "Ingrese su nombre"
                hayErrores = true
            } else {
                tilNombre.error = null
            }

            val apellido = etApellido.text.toString().trim()
            if (apellido.isEmpty()) {
                tilApellido.error = "Ingrese su apellido"
                hayErrores = true
            } else {
                tilApellido.error = null
            }

            val fecha = etFecha.text.toString().trim()
            if (fecha.isEmpty()) {
                tilFecha.error = "Seleccione su fecha de nacimiento"
                hayErrores = true
            } else {
                tilFecha.error = null
            }

            val ciudad = etCiudad.text.toString().trim()
            if (ciudad.isEmpty()) {
                tilCiudad.error = "Ingrese su ciudad"
                hayErrores = true
            } else {
                tilCiudad.error = null
            }

            val correo = etCorreo.text.toString().trim()
            if (correo.isEmpty()) {
                tilCorreo.error = "Ingrese su correo"
                hayErrores = true
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                tilCorreo.error = "Correo no válido"
                hayErrores = true
            } else {
                tilCorreo.error = null
            }

            val telefono = etTelefono.text.toString().trim()
            if (telefono.isEmpty()) {
                tilTelefono.error = "Ingrese su teléfono"
                hayErrores = true
            } else {
                tilTelefono.error = null
            }

            val genero = when (rgGenero.checkedRadioButtonId) {
                R.id.rbMasculino -> "Masculino"
                R.id.rbFemenino -> "Femenino"
                R.id.rbOtro -> "Otro"
                else -> "No especificado"
            }

            if (!hayErrores) {
                val intent = Intent(this, ActividadDatos::class.java)
                intent.putExtra("cedula", cedula)
                intent.putExtra("nombre", nombre)
                intent.putExtra("apellido", apellido)
                intent.putExtra("fecha", fecha)
                intent.putExtra("ciudad", ciudad)
                intent.putExtra("correo", correo)
                intent.putExtra("telefono", telefono)
                intent.putExtra("genero", genero)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clRegistro)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}