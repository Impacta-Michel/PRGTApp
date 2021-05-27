package br.com.prgtapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object UsuarioService {

    val host = "https://michel.pythonanywhere.com/"
    val TAG = "WS_App"

    fun getUsuarios(context: Context): List<Usuario> {
        var usuarios = ArrayList<Usuario>()
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "${host}/usuarios"
            val json = HttpHelper.get(url)
            usuarios = parserJson(json)
            // para salvar offline
            for (d in usuarios) {
                saveOffline(d)
            }
            return usuarios
        } else {
            val dao = DatabaseManager.getUsuarioDAO()
            val usuarios = dao.findAll()
            return usuarios
        }
    }

    fun getUsuario(context: Context, id: Long): Usuario? {
        if(AndroidUtils.isInternetDisponivel()) {
            val url = "$host/usuarios/${id}"
            val json = HttpHelper.get(url)
            val usuarios = parserJson<Usuario>(json)

            return usuarios
        } else {
            val dao = DatabaseManager.getUsuarioDAO()
            val usuarios = dao.getById(id)
            return usuarios
        }

    }

    fun save(usuario: Usuario): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/usuarios", usuario.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(usuario)
            return Response("OK", "Usuário salvo no dispositivo")
        }
    }

    fun saveOffline(usuario: Usuario) : Boolean {
        val dao = DatabaseManager.getUsuarioDAO()

        if (! existeUsuario(usuario)) {
            dao.insert(usuario)
        }
        return true

    }

    fun existeUsuario(usuario: Usuario): Boolean {
        val dao = DatabaseManager.getUsuarioDAO()
        return dao.getById(usuario.id) != null
    }

    fun delete(usuario: Usuario): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/usuarios/${usuario.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getUsuarioDAO()
            dao.delete(usuario)
            return Response("OK", "Dados salvos localmente")
        }

    }

    fun getById(id: Int) : Usuario {
        val json = HttpHelper.get("$host/usuarios/$id")

        return parserJson(json)
    }

    inline fun <reified T> parserJson(json:String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }


}