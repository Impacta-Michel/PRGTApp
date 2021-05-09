package br.com.prgtapp


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*


class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Menu do Usuário"


        var params = intent.extras
        val nome = params?.getString("nome")
        Toast.makeText(this, "Seja bem vindo, $nome", Toast.LENGTH_LONG).show()

        button_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        button_crew.setOnClickListener {
            val intent = Intent(this, EquipeActivity::class.java)
            startActivity(intent)
        }

        button_users.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        configuraMenuLateral()
//
//        val nomeShared = Prefs.getString("nomeusuario")
//        Toast.makeText(this, "Valor do SaredPreferences: $nomeShared", Toast.LENGTH_LONG).show()
    }

    private fun configuraMenuLateral(){
        var toogle = ActionBarDrawerToggle(
            this,
            layout_menu_lateral,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )

        layout_menu_lateral.addDrawerListener(toogle)
        toogle.syncState()

        nav_menu_lateral.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_config -> {
                Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ConfigActivity::class.java))

            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))

            }
        }
        layout_menu_lateral.closeDrawer(GravityCompat.START)
        return true
    }


}
