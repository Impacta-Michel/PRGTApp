package br.com.prgtapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fernandosousa.lmsapp.ProjetoAdapter
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        supportActionBar?.title = "Projetos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler_projetos?.layoutManager = LinearLayoutManager(this)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private var projetos = listOf<Projetos>()
    override fun onResume() {
        super.onResume()
        projetos = ProjetosService.getProjetos()
        recycler_projetos?.adapter = ProjetoAdapter(projetos){
            onClickProjeto(it)
        }
    }

    fun onClickProjeto(projeto: Projetos){
        val intent = Intent(this, DetalheProjeto::class.java)
        intent.putExtra("projeto", projeto)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}