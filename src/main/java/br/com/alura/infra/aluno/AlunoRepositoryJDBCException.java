package br.com.alura.infra.aluno;

public class AlunoRepositoryJDBCException extends AlunoRepositoryException {

    public AlunoRepositoryJDBCException() {
    }

    public AlunoRepositoryJDBCException(String message) {
        super(message);
    }

    public AlunoRepositoryJDBCException(Throwable cause) {
        super(cause);
    }

    public AlunoRepositoryJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
