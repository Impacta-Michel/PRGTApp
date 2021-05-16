package br.com.prgtapp

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: PRGTDatabase

    init {
        val context = PRGTApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            PRGTDatabase::class.java,
            "app.sqlite"
        )   .fallbackToDestructiveMigrationFrom(1)
            .build()
    }

    fun getUsuarioDAO(): UsuariosDAO {
        return dbInstance.usuariosDAO()
    }
}