package br.com.prgtapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetalheProjeto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_projeto)
        val projeto = intent.extras?.getSerializable("projeto") as Projetos

        Toast.makeText(this, "Clicou no Projeto ${projeto.nome}", Toast.LENGTH_SHORT).show()
    }
}