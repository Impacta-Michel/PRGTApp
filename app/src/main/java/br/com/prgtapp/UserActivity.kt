package br.com.prgtapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private val context: Context get() = this
    private var usuarios = listOf<Usuario>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.title = "Lista de Usuários"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler_usuarios?.layoutManager = LinearLayoutManager(context)
        recycler_usuarios?.itemAnimator = DefaultItemAnimator()
        recycler_usuarios?.setHasFixedSize(true)

        taskUsuario()
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

    fun taskUsuario() {
        Thread {
            this.usuarios = UsuarioService.getUsuarios(context)
            runOnUiThread{
                recycler_usuarios?.adapter = UsuarioAdapter(this.usuarios){ onClickUsuario(it) }

                enviaNotificacao(this.usuarios.get(0))
            }
        }.start()
    }

    fun enviaNotificacao(usuario: Usuario) {

        val intent = Intent(this, UserActivity::class.java)

        intent.putExtra("usuario", usuario)
        NotificationUtil.create(this, 1, intent, "PRGTApp", "Você tem nova atividade no ${usuario.nome}")
    }


    fun onClickUsuario(usuario: Usuario){
//        Toast.makeText(context, "Clicou no usuário id: ${usuario.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, UsuarioDetalheActivity::class.java)
        intent.putExtra("usuario", usuario)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE) {
            // atualizar lista de usuarios
            taskUsuario()
        }
    }

}