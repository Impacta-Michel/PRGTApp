package br.com.fernandosousa.lmsapp

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import br.com.prgtapp.Projetos
import br.com.prgtapp.R
import  kotlinx.android.synthetic.main.adapter_projeto.*

// define o construtor que recebe a lista de disciplinas e o evento de clique
class ProjetoAdapter (
    val projetos: List<Projetos>,
    val onClick: (Projetos) -> Unit): RecyclerView.Adapter<ProjetoAdapter.ProjetosViewHolder>() {

    // ViewHolder com os elemetos da tela
    class ProjetosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardDescription: TextView
        var cardView: CardView

        init {
            cardNome = view.findViewById(R.id.card_nome)
            cardDescription = view.findViewById(R.id.card_description)
            cardView = view.findViewById(R.id.card_projetos)

        }

    }



    override fun getItemCount() = this.projetos.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjetosViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_projeto, parent, false)

        val holder = ProjetosViewHolder(view)
        return holder
    }



    override fun onBindViewHolder(holder: ProjetosViewHolder, position: Int) {
        val projeto = projetos[position]
        holder.cardNome.text = projeto.nome
        holder.cardDescription.text = projeto.descricao
        holder.itemView.setOnClickListener {onClick(projeto)}

    }
}

