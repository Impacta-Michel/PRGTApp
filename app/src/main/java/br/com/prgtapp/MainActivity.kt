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

        register.setOnClickListener {
            Toast.makeText(this, "Registre-se no site", Toast.LENGTH_LONG).show()
        }

        botao_login.setOnClickListener {
            val nome_usuario = campo_usuario.text.toString()
            val senha_usuario = campo_senha.text.toString()

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