package br.com.alura.dominio.aluno;

public class AlunoNaoMatriculadoException extends Exception {

    public AlunoNaoMatriculadoException() {
    }

    public AlunoNaoMatriculadoException(String message) {
        super(message);
    }

    public AlunoNaoMatriculadoException(Throwable cause) {
        super(cause);
    }

    public AlunoNaoMatriculadoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
