package br.com.prgtapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_user.*
import kotlinx.android.synthetic.main.toolbar.*
import java.lang.StringBuilder
import java.util.Objects.isNull

class NewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Novo Usuário"

        botao_registrar.setOnClickListener{
            val textoNome = campo_usuario.text.toString()
            val textoSenha = campo_senha.text.toString()
            val textoEmail = campo_email.text.toString()
            val textoUrlFoto = campo_urlFoto.text.toString()

            if (
                TextUtils.isEmpty(textoNome) || TextUtils.isEmpty(textoSenha) || TextUtils.isEmpty(textoEmail) || TextUtils.isEmpty(textoUrlFoto)
            ){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            } else {
                val u = Usuario()
                u.nome = textoNome
                u.senha = textoSenha
                u.email = textoEmail
                u.foto = textoUrlFoto

                Thread {
                    UsuarioService.save(u)
                    runOnUiThread{
                        val intent = Intent(this, TelaInicialActivity::class.java)
                        intent.putExtra("usuario", u)
                        startActivity(intent)
                    }
                }.start()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}