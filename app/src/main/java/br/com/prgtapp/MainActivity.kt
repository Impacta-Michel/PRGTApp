package br.com.prgtapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*


class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        campo_usuario.setText(Prefs.getString("nomeusuario"))
        campo_senha.setText(Prefs.getString("senhausuario"))
        checkbox.isChecked = Prefs.getBoolean("checkusuario")

        register.setOnClickListener {
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }

        botao_login.setOnClickListener {
            val nome_usuario = campo_usuario.text.toString()
            val senha_usuario = campo_senha.text.toString()
            val check_login = checkbox.isChecked

            if (check_login) {
                Prefs.setString("nomeusuario", nome_usuario)
                Prefs.setString("senhausuario", senha_usuario)
            } else {
                Prefs.setString("nomeusuario", "")
                Prefs.setString("senhausuario", "")
            }
            Prefs.setBoolean("checkusuario", check_login)

            val intent = Intent(this, TelaInicialActivity::class.java)

            if (nome_usuario == "aluno" && senha_usuario == "impacta"){
                val params = Bundle()
                params.putString("nome", nome_usuario)
                intent.putExtras(params)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
            }
        }
    }
}