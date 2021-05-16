package br.com.prgtapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Usuario::class), version = 2 )
abstract class PRGTDatabase: RoomDatabase() {
    abstract fun usuariosDAO(): UsuariosDAO
}