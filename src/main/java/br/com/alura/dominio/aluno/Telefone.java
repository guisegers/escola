package br.com.alura.dominio.aluno;

import lombok.Data;

@Data
public class Telefone {
    
    private String ddd;
    private String numero;
    
    public Telefone(final String ddd, final String numero) {
        if(ddd == null || !ddd.matches("([0-9]{2})")){
            throw new IllegalArgumentException("DDD Invalido");
        }
        this.ddd = ddd;
        if(numero == null || !numero.matches("([0-9]{8,9})")){
            throw new IllegalArgumentException("Numero Invalido");
        }
        this.numero = numero;
    }



}
