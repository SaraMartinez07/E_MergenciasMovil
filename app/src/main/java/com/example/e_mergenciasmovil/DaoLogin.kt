package com.example.e_mergenciasmovil

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoLogin {
    @Query("SELECT * FROM Usuario WHERE nombre = :nombreUsuario")
    fun obtenerLoginPorNombreUsuario(nombreUsuario: String): Usuario?
}