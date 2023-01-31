package br.com.alura.infra.aluno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoNaoEncontradoException;
import br.com.alura.dominio.aluno.AlunoRepository;
import br.com.alura.dominio.aluno.Cpf;

public class AlunoRepositoryMemory implements AlunoRepository {

    private Collection<Aluno> alunos = new ArrayList<Aluno>();

    public AlunoRepositoryMemory(Collection<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public void matricular(final Aluno aluno) throws AlunoRepositoryMemoryException {
        if (this.alunos.stream().anyMatch(a -> a.getCpf().getCpf().equals(aluno.getCpf().getCpf()))) {
            throw new AlunoRepositoryMemoryException("Aluno já matriculado");
        }
        this.alunos.add(aluno);
    }

    @Override
    public Aluno buscarPorCPF(Cpf cpf) throws AlunoNaoEncontradoException {
        return this.alunos.stream()
                .filter(a -> a.getCpf().getCpf().equals(cpf.getCpf())).findFirst()
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado"));
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        return this.alunos.stream().toList();
    }

}
