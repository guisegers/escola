package br.com.alura.infra.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoBuilder;
import br.com.alura.dominio.aluno.AlunoNaoEncontradoException;
import br.com.alura.dominio.aluno.Cpf;
import br.com.alura.infra.AlunoRepositoryException;
import br.com.alura.infra.AlunoRepositoryMemory;
import br.com.alura.infra.AlunoRepositoryMemoryException;

public class AlunoRepositoryMemoryTest {

    private AlunoRepositoryMemory alunoRepository;

    private List<Aluno> criarLista5Alunos() {
        List<Aluno> alunos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Aluno aluno = AlunoBuilder.builder("123.456.789-0" + i, "Eu mesmo" + i, "eumesmo" + i + "@email.com")
                    .comTelefone("11", i + "23456789")
                    .comTelefone("11", i + "2345678").build();
            alunos.add(aluno);
        }
        return alunos;
    }

    private void matricularAlunosParaTest(final List<Aluno> alunos) {
        alunos.forEach(aluno -> {
            try {
                alunoRepository.matricular(aluno);
            } catch (AlunoRepositoryException e) {
                e.printStackTrace();
            }
        });
    }

    @BeforeEach
    public void setUp() {
        alunoRepository = new AlunoRepositoryMemory(new ArrayList<Aluno>());
    }

    @Test
    void matricularAlunoQuandoAlunoJaMatriculado() {
        assertThrows(AlunoRepositoryMemoryException.class, () -> {
            Aluno aluno = AlunoBuilder.builder("123.456.789-01", "Eu mesmo", "eumesmo@email.com")
                    .comTelefone("11", "123456789")
                    .comTelefone("11", "12345678").build();
            alunoRepository.matricular(aluno);
            alunoRepository.matricular(aluno);
        });

    }

    @Test
    void matricularAlunoQuandoNaoExisteAlunoMatriculadoComSucesso() throws AlunoRepositoryException {
        Aluno aluno = AlunoBuilder.builder("123.456.789-01", "Eu mesmo", "eumesmo@email.com")
                .comTelefone("11", "123456789")
                .comTelefone("11", "12345678").build();
        alunoRepository.matricular(aluno);
    }

    @Test
    void consultarAlunoMatriculadoPorCpfAlunoNaoEncontrado() {
        List<Aluno> alunos = criarLista5Alunos();
        matricularAlunosParaTest(alunos);

        assertThrows(AlunoNaoEncontradoException.class, () -> {
            Aluno result = alunoRepository.buscarPorCPF(new Cpf("123.456.789-23"));
            assertNull(result);
        });

    }

    @Test
    void consultarAlunoMatriculadoPorCpfAlunoEncontrado() {
        List<Aluno> alunos = criarLista5Alunos();
        matricularAlunosParaTest(alunos);

        Aluno result = alunoRepository.buscarPorCPF(new Cpf("123.456.789-03"));
        assertEquals(result.getCpf().getCpf(), "123.456.789-03");
    }

    @Test
    void consultarTodosAlunosMatriculadosQuandoNenhumAlunoEncontrado() {
        List<Aluno> todos = alunoRepository.listarTodosAlunosMatriculados();
        assertEquals(todos.size(), 0);
    }

    @Test
    void consultarTodosAlunosMatriculadosQuandoAlunosEncontrados() {
        List<Aluno> alunos = criarLista5Alunos();
        matricularAlunosParaTest(alunos);

        List<Aluno> todos = alunoRepository.listarTodosAlunosMatriculados();

        assertLinesMatch(alunos.stream().map(a -> a.getCpf().getCpf()).toList(),
                todos.stream().map(a -> a.getCpf().getCpf()).toList());
        assertLinesMatch(alunos.stream().map(a -> a.getNome()).toList(), todos.stream().map(a -> a.getNome()).toList());
        assertLinesMatch(alunos.stream().map(a -> a.getEmail().getEndereco()).toList(),
                todos.stream().map(a -> a.getEmail().getEndereco()).toList());

        List<String> todosTelefones = todos.stream()
                .flatMap(a -> a.getTelefones().stream().map(t -> a.getCpf().getCpf() + t.getDdd() + t.getNumero()))
                .toList();
        List<String> telefonesAlunos = alunos.stream()
                .flatMap(a -> a.getTelefones().stream().map(t -> a.getCpf().getCpf() + t.getDdd() + t.getNumero()))
                .toList();

        assertLinesMatch(telefonesAlunos, todosTelefones);

    }

}
