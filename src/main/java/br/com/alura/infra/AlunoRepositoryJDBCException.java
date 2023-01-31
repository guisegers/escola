package br.com.alura.infra;

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
