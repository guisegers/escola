package br.com.alura.dominio.aluno;

import lombok.Data;

@Data
public class Cpf {
    private String cpf;

    public Cpf(String cpf) {
        String pattern = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
        if (cpf == null || !cpf.matches(pattern)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
    }

}
