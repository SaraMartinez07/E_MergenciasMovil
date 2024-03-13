package com.example.e_mergenciasmovil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import java.security.MessageDigest

class login2 : AppCompatActivity() {
    private lateinit var baseDeDatos: AppBaseDeDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.idLogin2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar la base de datos
        baseDeDatos = obtenerBaseDeDatos(this)

        // Obtener referencias a los elementos de la interfaz de usuario
        val botonLogin = findViewById<Button>(R.id.idButtonLogin)
        val botonRegistrar = findViewById<Button>(R.id.idButtonRegistrar)

        // Configurar el listener para el botón de inicio de sesión
        botonLogin.setOnClickListener {
            iniciarSesion()
        }
        botonRegistrar.setOnClickListener {
            Registrar()
        }
    }

    private fun obtenerBaseDeDatos(contexto: Context): AppBaseDeDatos {
        return Room.databaseBuilder(
            contexto.applicationContext,
            AppBaseDeDatos::class.java,
            "AppBaseDeDatos"
        ).build()
    }

    private fun iniciarSesion() {
        val nombreUsuario = findViewById<TextInputEditText>(R.id.textInpEditUsser).text.toString()
        val contraseña = findViewById<TextInputEditText>(R.id.idtextinpeditPass).text.toString()

        Thread {
            val usuario = baseDeDatos.daoLogin().obtenerLoginPorNombreUsuario(nombreUsuario)
            runOnUiThread {
                if (usuario != null && verificarContraseña(contraseña, usuario.contraseña)) {
                    // Inicio de sesión exitoso
                    mostrarMensaje("Inicio de sesión exitoso")
                    // Redirigir al usuario a la actividad de Inicio
                    irAActividadInicio()
                } else {
                    // Inicio de sesión fallido
                    Log.d("Login", "Inicio de sesión fallido para el usuario: $nombreUsuario")
                    mostrarMensaje("Nombre de usuario o contraseña incorrectos")
                }
            }
        }.start()
    }

    private fun hashPassword(password: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val bytes = messageDigest.digest(password.toByteArray())
        val hexString = StringBuilder()
        for (byte in bytes) {
            val hex = Integer.toHexString(0xff and byte.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }

    private fun verificarContraseña(contraseñaIngresada: String, contraseñaAlmacenada: String): Boolean {
        val hashedPassword = hashPassword(contraseñaIngresada)
        return hashedPassword == contraseñaAlmacenada
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun irAActividadInicio() {
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
        finish()
    }

    private fun Registrar() {
        val intent = Intent(this, Registrar::class.java)
        startActivity(intent)
    }
}