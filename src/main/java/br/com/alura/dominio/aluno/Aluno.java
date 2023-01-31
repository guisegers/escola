package br.com.alura.dominio.aluno;

import java.util.Collection;
import java.util.HashSet;

public class Aluno {

    private Cpf cpf;
    private String nome;
    private Email email;
    private Collection<Telefone> telefones = new HashSet<>();
    private String senha;

    public void addTelefone(final String ddd, final String numero) {
        this.telefones.add(new Telefone(ddd, numero));
    }

    public Aluno(Cpf cpf, String nome, Email email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Collection<Telefone> getTelefones() {
        return telefones;
    }

    @Override
    public String toString() {
        return "Aluno [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", telefones=" + telefones + "]";
    }

}
