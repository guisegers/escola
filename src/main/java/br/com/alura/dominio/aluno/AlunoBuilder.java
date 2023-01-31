package br.com.alura.dominio.aluno;

public class AlunoBuilder {
    
    private Aluno aluno;

    private AlunoBuilder(final String cpf, final String nome, final String email) {
        this.aluno = new Aluno(new Cpf(cpf), nome, new Email(email));
    }

    public static AlunoBuilder builder(final String cpf, final String nome, final String email){
        return new AlunoBuilder(cpf, nome, email);
    }

    public AlunoBuilder comEmail(String email){
        this.aluno.setEmail(new Email(email));
        return this;
    }

    public AlunoBuilder comTelefone(String ddd, String numero){
        this.aluno.addTelefone(ddd, numero);
        return this;
    }

    public Aluno build(){
        return this.aluno;
    }

}
