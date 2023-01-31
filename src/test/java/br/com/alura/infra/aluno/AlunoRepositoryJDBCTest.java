package br.com.alura.infra.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.radcortez.flyway.test.annotation.DataSource;
import com.radcortez.flyway.test.annotation.FlywayTest;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoBuilder;
import br.com.alura.dominio.aluno.Cpf;
import br.com.alura.infra.AlunoRepositoryException;
import br.com.alura.infra.AlunoRepositoryJDBC;
import br.com.alura.infra.AlunoRepositoryJDBCException;

@FlywayTest(@DataSource(url = "jdbc:h2:file:./.h2/test", username = "sa", password = "test123"))
public class AlunoRepositoryJDBCTest {

    private Connection connection;

    private AlunoRepositoryJDBC alunoRepositoryJDBC;

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
                alunoRepositoryJDBC.matricular(aluno);
            } catch (AlunoRepositoryJDBCException e) {
                e.printStackTrace();
            }
        });
    }

    @BeforeEach
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection("jdbc:h2:file:./.h2/test", "sa", "test123");
        connection.setSchema("PUBLIC");
        alunoRepositoryJDBC = new AlunoRepositoryJDBC(connection);
    }

    @Test
    void matricularAlunoQuandoAlunoJaMatriculado() {
        assertThrows(AlunoRepositoryException.class, () -> {
            Aluno aluno = AlunoBuilder.builder("123.456.789-01", "Eu mesmo", "eumesmo@email.com")
                    .comTelefone("11", "123456789")
                    .comTelefone("11", "12345678").build();
            alunoRepositoryJDBC.matricular(aluno);
            alunoRepositoryJDBC.matricular(aluno);
        });

    }

    @Test
    void matricularAlunoQuandoNaoExisteAlunoMatriculadoComSucesso() throws AlunoRepositoryException {
        Aluno aluno = AlunoBuilder.builder("123.456.789-01", "Eu mesmo", "eumesmo@email.com")
                .comTelefone("11", "123456789")
                .comTelefone("11", "12345678").build();
        alunoRepositoryJDBC.matricular(aluno);
    }

    @Test
    void consultarAlunoMatriculadoPorCpfAlunoNaoEncontrado() {
        List<Aluno> alunos = criarLista5Alunos();
        matricularAlunosParaTest(alunos);

        Aluno result = alunoRepositoryJDBC.buscarPorCPF(new Cpf("555.555.555-55"));
        assertNull(result);

    }

    @Test
    void consultarAlunoMatriculadoPorCpfAlunoEncontrado() {
        List<Aluno> alunos = criarLista5Alunos();
        matricularAlunosParaTest(alunos);

        Aluno result = alunoRepositoryJDBC.buscarPorCPF(new Cpf("123.456.789-03"));
        assertEquals(result.getCpf().getCpf(), "123.456.789-03");
    }

    @Test
    void consultarTodosAlunosMatriculadosQuandoNenhumAlunoEncontrado() {
        List<Aluno> todos = alunoRepositoryJDBC.listarTodosAlunosMatriculados();
        assertEquals(todos.size(), 0);
    }

    @Test
    void consultarTodosAlunosMatriculadosQuandoAlunosEncontrados() {
        List<Aluno> alunos = criarLista5Alunos();
        matricularAlunosParaTest(alunos);

        List<Aluno> todos = alunoRepositoryJDBC.listarTodosAlunosMatriculados();

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
