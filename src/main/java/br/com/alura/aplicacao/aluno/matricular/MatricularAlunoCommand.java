package br.com.alura.aplicacao.aluno.matricular;

import java.util.ArrayList;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoNaoMatriculadoException;
import br.com.alura.infra.aluno.AlunoRepositoryMemory;

public class MatricularAlunoCommand {

    public static void main(String[] args) throws AlunoNaoMatriculadoException {
        String cpf = "123.456.789-01";
        String nome = "Fulano de tal";
        String email = "fulanodetal@email.com";


        MatricularAluno matricular = new MatricularAluno(new AlunoRepositoryMemory(new ArrayList<Aluno>()));
    
        matricular.executa(new MatricularAlunoDto(cpf, nome, email));
    }

}
