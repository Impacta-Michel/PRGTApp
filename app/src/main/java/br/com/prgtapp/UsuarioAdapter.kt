package br.com.prgtapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UsuarioAdapter (
    val usuarios: List<Usuario>,
    val onClick: (Usuario) -> Unit): RecyclerView.Adapter<UsuarioAdapter.UsuariosViewHolder>() {

    // ViewHolder com os elemetos da tela
    class UsuariosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg: ImageView
        var cardView: CardView
        var cardProgress: ProgressBar

        init {
            cardNome = view.findViewById<TextView>(R.id.card_usuario_nome)
            cardView = view.findViewById<CardView>(R.id.card_usuarios)
            cardImg = view.findViewById<ImageView>(R.id.card_usuario_Img)
            cardProgress = view.findViewById<ProgressBar>(R.id.card_Progress)
        }

    }


    override fun getItemCount() = this.usuarios.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_usuario, parent, false)

        val holder = UsuariosViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val context = holder.itemView.context

        val usuario = usuarios[position]
        holder.cardNome.text = usuario.nome
        holder.cardProgress.visibility = View.VISIBLE


        Picasso.with(context).load(usuario.foto).fit().into(holder.cardImg,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        holder.itemView.setOnClickListener { onClick(usuario) }
    }
}