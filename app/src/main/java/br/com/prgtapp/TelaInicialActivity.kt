package br.com.prgtapp


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.tela_inicial.*



class TelaInicialActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

        var params = intent.extras
        val nome = params?.getString("nome")
        Toast.makeText(this, "Seja bem vindo, $nome", Toast.LENGTH_LONG).show()
        supportActionBar?.title = "Menu do Usuário"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        button_projects.setOnClickListener {
            val intent = Intent(this, ProjectActivity::class.java)
            startActivity(intent)
        }
        button_crew.setOnClickListener {
            val intent = Intent(this, CrewActivity::class.java)
            startActivity(intent)
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
       if (id == R.id.action_logout) {
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ConfigActivity::class.java))
        } else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)

    }
}
