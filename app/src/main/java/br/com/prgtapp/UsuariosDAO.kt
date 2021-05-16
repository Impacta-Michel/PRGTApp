package br.com.prgtapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuariosDAO {
    @Query("SELECT * FROM usuarios WHERE id = :id")
    fun getById(id: Long) : Usuario?

    @Query("SELECT * FROM usuarios")
    fun findAll(): List<Usuario>

    @Insert
    fun insert(usuarios: Usuario)

    @Delete
    fun delete(usuarios: Usuario)
}