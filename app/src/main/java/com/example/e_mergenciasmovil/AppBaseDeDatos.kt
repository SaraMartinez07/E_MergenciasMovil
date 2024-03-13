package com.example.e_mergenciasmovil

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class, LoginS::class], version = 1, exportSchema = false)

abstract class AppBaseDeDatos : RoomDatabase(){
    abstract fun daoRegistrar(): DaoRegistrar

    abstract fun daoLogin(): DaoLogin

}