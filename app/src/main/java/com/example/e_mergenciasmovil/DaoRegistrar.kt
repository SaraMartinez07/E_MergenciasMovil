package com.example.e_mergenciasmovil

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoRegistrar {
    @Query("SELECT * FROM usuario")
    fun obtenerUsuariosRegistrados(): List<Usuario>

    @Insert
    fun registrarUser(usuario: Usuario)
}