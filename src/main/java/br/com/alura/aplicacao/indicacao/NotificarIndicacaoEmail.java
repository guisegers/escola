package br.com.alura.aplicacao.indicacao;

import br.com.alura.dominio.aluno.Aluno;

public interface NotificarIndicacaoEmail {
    void enviarPara(Aluno indicado);
}
