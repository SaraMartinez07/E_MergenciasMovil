package com.example.e_mergenciasmovil

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LoginS (
    @PrimaryKey(autoGenerate = true) val idLogin: Long = 0,
    @ColumnInfo(name = "nombreUsuario") val nombreUsuario: String,
    @ColumnInfo(name = "contraseña") val contraseña: String
) {
    init {
        require(nombreUsuario.isNotBlank()) { "El nombre de usuario no puede estar vacío" }
        require(contraseña.isNotBlank()) { "La contraseña no puede estar vacía" }
    }
}