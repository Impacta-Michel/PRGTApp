package br.com.prgtapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "usuarios")
class Usuario: Serializable {

    @PrimaryKey
    var id: Long = 0
    var nome = ""
    var email = ""
    var senha = ""
    var foto = ""

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}