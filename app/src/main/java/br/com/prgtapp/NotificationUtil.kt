package br.com.prgtapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.nio.channels.Channel

object NotificationUtil {

    internal val CHANNEL_ID = "1"

    // canal para exibição
    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val appName = context.getString(R.string.app_name)
            val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_HIGH)

            manager.createNotificationChannel(c)
        }
    }

    fun create(context: Context, id: Int, intent: Intent, titulo: String, texto: String){

        // Canal para exibicao
        createChannel(PRGTApplication.getInstance())

        // Intent para disparar broadcast
        val p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // cria notificação
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(p)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        // disparar notificacao
        with(NotificationManagerCompat.from(PRGTApplication.getInstance())){
            val n = builder.build()
            notify(id, n)
        }
    }
}