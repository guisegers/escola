package br.com.alura.infra.aluno;

public class AlunoRepositoryMemoryException extends AlunoRepositoryException {

    public AlunoRepositoryMemoryException() {
    }

    public AlunoRepositoryMemoryException(String message) {
        super(message);
    }

    public AlunoRepositoryMemoryException(Throwable cause) {
        super(cause);
    }

    public AlunoRepositoryMemoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
