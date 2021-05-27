package br.com.prgtapp

import android.content.Intent
import android.util.Log
import br.com.prgtapp.UsuarioService.TAG
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "Novo token: $token")

        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        super.onMessageReceived(mensagemRemota)
        Log.d(TAG, "chegou a mensagem")

        if (mensagemRemota?.notification != null) {
            showNotification(mensagemRemota)

        }
    }

    private fun showNotification(mensagemRemota: RemoteMessage?) {
        val intent = Intent(this, UsuarioDetalheActivity::class.java)
        val titulo = mensagemRemota?.notification?.title
        var corpo = mensagemRemota?.notification?.body
        Log.d(TAG, "Titulo: $titulo")
        Log.d(TAG, "Corpo: $corpo")

        if (mensagemRemota?.data!!.isNotEmpty()) {
            val usuarioID = mensagemRemota?.data!!.get("usuarioID")
            val u = UsuarioService.getById(usuarioID!!.toInt())
            intent.putExtra("usuario", u)

            corpo = usuarioID
        }

        NotificationUtil.create(this, 1, intent, titulo!!, corpo!!)
    }

}