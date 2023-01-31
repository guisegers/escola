package br.com.alura.dominio.aluno;

public class AlunoNaoEncontradoException extends RuntimeException {
    
    public AlunoNaoEncontradoException(String msg){
        super(msg);
    }

}
