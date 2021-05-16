package br.com.prgtapp

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.prgtapp.UsuarioService.getUsuario
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.activity_usuario_detalhe.*
import kotlinx.android.synthetic.main.login.*

class UsuarioDetalheActivity : DebugActivity() {

    private val context: Context get() = this
    private var usuario = Usuario()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_detalhe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        usuario = intent.getSerializableExtra("usuario") as Usuario

        supportActionBar?.title = "Usuário: ${usuario.nome} ID: ${usuario.id}"


        botao_remover_usuario.setOnClickListener {
            AlertDialog.Builder(this).setTitle(R.string.app_name)
                .setMessage("Deseja excluir o Usuário")
                .setPositiveButton("Sim") { dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") { dialog, which ->
                    dialog.dismiss()
                }.create().show()
        }
    }


    private fun taskExcluir() {
        if (this.usuario != null && this.usuario is Usuario) {
            Thread {
                UsuarioService.delete(this.usuario)
                runOnUiThread{
                    finish()
                }
            }.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, query, Toast.LENGTH_LONG).show()
                return false
            }

        })
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