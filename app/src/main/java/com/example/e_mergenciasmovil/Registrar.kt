package com.example.e_mergenciasmovil

import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText

class Registrar : AppCompatActivity() {
    private lateinit var baseDeDatos: AppBaseDeDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar la base de datos
        baseDeDatos = obtenerBaseDeDatos(this)

        // Obtener referencias a los elementos de la interfaz de usuario
        val botonGuardar = findViewById<Button>(R.id.idButtonGuardarReg)


        // Configurar el listener para el botón de guardar
        botonGuardar.setOnClickListener {
            guardarDatos()
        }
    }

    private fun obtenerBaseDeDatos(contexto: Context): AppBaseDeDatos {
        return Room.databaseBuilder(
            contexto.applicationContext,
            AppBaseDeDatos::class.java,
            "AppBaseDeDatos"
        ).build()
    }

    private fun guardarDatos() {
        val nombre = findViewById<TextInputEditText>(R.id.idTextInpEditNombre).text.toString()
        val telefono = findViewById<TextInputEditText>(R.id.idTextInpEdiTelef).text.toString()
        val domicilio = findViewById<TextInputEditText>(R.id.idTextInpEditDairec).text.toString()
        val correo = findViewById<TextInputEditText>(R.id.idTextInpEditCorreo).text.toString()
        val contraseña = findViewById<TextInputEditText>(R.id.idTextInpEditPass).text.toString()
        val confirmarContraseña = findViewById<TextInputEditText>(R.id.idTextInpEditConfirm).text.toString()

        if (nombre.isNotEmpty() && telefono.isNotEmpty() && domicilio.isNotEmpty() && correo.isNotEmpty() && contraseña.isNotEmpty() && confirmarContraseña.isNotEmpty() && contraseña == confirmarContraseña) {
            guardarDatosUsuario(nombre, telefono, domicilio, correo, contraseña)
        } else {
            if (contraseña != confirmarContraseña) {
                mostrarMensaje("Las contraseñas no coinciden")
            } else {
                mostrarMensaje("Por favor, llene todos los campos")
            }
        }
    }

    private fun guardarDatosUsuario(nombre: String, telefono: String, domicilio: String, correo: String, contraseña: String) {
        val usuario = Usuario(0, nombre, telefono, domicilio, correo, contraseña, "")

        Thread {
            baseDeDatos.daoRegistrar().registrarUser(usuario)
            runOnUiThread {
                mostrarMensaje("Datos guardados correctamente")

                val intent = Intent(this@Registrar, LoginS::class.java)
                startActivity(intent)
                // Finalizar la actividad actual (Registrar)
                finish()
            }
        }.start()
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}