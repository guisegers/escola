package br.com.alura.dominio.aluno;

import java.util.List;

import br.com.alura.infra.aluno.AlunoRepositoryException;

public interface AlunoRepository {
    
    void matricular(Aluno aluno) throws AlunoRepositoryException ;

    Aluno buscarPorCPF(Cpf cpf) throws AlunoRepositoryException ;

    List<Aluno> listarTodosAlunosMatriculados() throws AlunoRepositoryException ;

}
