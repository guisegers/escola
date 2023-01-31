package br.com.alura.dominio.aluno;

import java.util.List;

public interface AlunoRepository {

    void matricular(Aluno aluno) throws AlunoNaoMatriculadoException;

    Aluno buscarPorCPF(Cpf cpf) throws AlunoNaoEncontradoException;

    List<Aluno> listarTodosAlunosMatriculados();

}
