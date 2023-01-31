package br.com.alura.dominio.indicacao;

import java.time.LocalDateTime;

import br.com.alura.dominio.aluno.Aluno;

public class Indicacao {

    private Aluno indicado;
    private Aluno indicante;
    private LocalDateTime dataIndicacao;
    
    public Indicacao(final Aluno indicado, final Aluno indicante, final LocalDateTime dataIndicacao) {
        this.indicado = indicado;
        this.indicante = indicante;
        this.dataIndicacao = dataIndicacao;
    }

    public Aluno getIndicado() {
        return indicado;
    }

    public void setIndicado(Aluno indicado) {
        this.indicado = indicado;
    }

    public Aluno getIndicante() {
        return indicante;
    }

    public void setIndicante(Aluno indicante) {
        this.indicante = indicante;
    }

    public LocalDateTime getDataIndicacao() {
        return dataIndicacao;
    }

    public void setDataIndicacao(LocalDateTime dataIndicacao) {
        this.dataIndicacao = dataIndicacao;
    }

    
}
