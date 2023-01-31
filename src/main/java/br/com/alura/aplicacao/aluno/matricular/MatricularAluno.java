package br.com.alura.aplicacao.aluno.matricular;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoBuilder;
import br.com.alura.dominio.aluno.AlunoNaoMatriculadoException;
import br.com.alura.dominio.aluno.AlunoRepository;

public class MatricularAluno {

    private AlunoRepository alunoRepository;

    public MatricularAluno(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void matricular(MatricularAlunoDto dados) throws AlunoNaoMatriculadoException{
        Aluno aluno = AlunoBuilder.builder(dados.cpf(), dados.nome(), dados.email()).build();
        alunoRepository.matricular(aluno);
    }

}
