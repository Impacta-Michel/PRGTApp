package br.com.prgtapp

object ProjetosService {

    fun getProjetos(): List<Projetos> {

        var projetos = mutableListOf<Projetos>()
        for (i in 1..10) {
            val d = Projetos()
            d.nome = "Projeto $i"
            d.descricao = "Descrição:  $i"
            d.responsavel = "Responsável pelo projeto:  $i"
            projetos.add(d)

        }
        return projetos
    }
}