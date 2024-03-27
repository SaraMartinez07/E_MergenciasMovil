package com.example.e_mergenciasmovil

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoLogin {
    //@Query("SELECT * FROM Usuario WHERE nombre = :nombre")
   // fun obtenerLoginPorNombreUsuario(nombre: String): Usuario?

    //@Insert
   // fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre LIMIT 1")
    fun obtenerLoginPorNombreUsuario(nombre: String): Usuario?
}