package com.example.e_mergenciasmovil

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class, LoginS::class], version = 2, exportSchema = false)
abstract class AppBaseDeDatos : RoomDatabase() {
    abstract fun daoRegistrar(): DaoRegistrar
    abstract fun daoLogin(): DaoLogin

    companion object {
        @Volatile
        private var INSTANCE: AppBaseDeDatos? = null

        fun getInstance(context: Context): AppBaseDeDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppBaseDeDatos::class.java,
                    "AppBaseDeDatos"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}