package com.example.e_mergenciasmovil

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["nombre"], unique = true)])
data class Usuario (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @ColumnInfo(name = "domicilio") val domicilio: String,
    @ColumnInfo(name = "correo") val correo: String,
    @ColumnInfo(name = "contraseña") val contraseña: String, // Aquí se almacenará la contraseña cifrada
    @ColumnInfo(name = "confirmar_contraseña") val confirmarContraseña: String
)