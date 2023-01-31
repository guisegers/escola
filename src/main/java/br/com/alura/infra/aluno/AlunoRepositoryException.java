package br.com.alura.infra.aluno;

public class AlunoRepositoryException extends Exception {

    public AlunoRepositoryException() {
    }

    public AlunoRepositoryException(String message) {
        super(message);
    }

    public AlunoRepositoryException(Throwable cause) {
        super(cause);
    }

    public AlunoRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
