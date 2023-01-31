package br.com.alura.infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoBuilder;
import br.com.alura.dominio.aluno.AlunoRepository;
import br.com.alura.dominio.aluno.Cpf;
import br.com.alura.dominio.aluno.Telefone;

public class AlunoRepositoryJDBC implements AlunoRepository {

    private final Connection connection;

    public AlunoRepositoryJDBC(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void matricular(Aluno aluno) throws AlunoRepositoryJDBCException {
        try {
            String sql = "INSERT INTO ALUNO (CPF, NOME, EMAIL) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getCpf().getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getEmail().getEndereco());
            stmt.execute();

            sql = "INSERT INTO TELEFONE (DDD, NUMERO, CPF) VALUES (?,?,?)";
            stmt = connection.prepareStatement(sql);
            for (Telefone telefone : aluno.getTelefones()) {
                stmt.setString(1, telefone.getDdd());
                stmt.setString(2, telefone.getNumero());
                stmt.setString(3, aluno.getCpf().getCpf());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new AlunoRepositoryJDBCException(e);
        }
    }

    @Override
    public Aluno buscarPorCPF(Cpf cpf) {
        Aluno aluno = null;
        try {
            String sql = "SELECT A.CPF, A.NOME, A.EMAIL, T.DDD, T.NUMERO FROM ALUNO A JOIN TELEFONE T ON A.CPF = T.CPF WHERE A.CPF = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf.getCpf());

            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                aluno = AlunoBuilder.builder(rs.getString(1), rs.getString(2), rs.getString(3)).build();
                aluno.addTelefone(rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return aluno;
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            final String sql = "SELECT A.CPF, A.NOME, A.EMAIL, T.DDD, T.NUMERO FROM ALUNO A JOIN TELEFONE T ON A.CPF = T.CPF";
            final PreparedStatement stmt = connection.prepareStatement(sql);
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aluno aluno = AlunoBuilder.builder(rs.getString(1), rs.getString(2), rs.getString(3)).build();
                aluno.addTelefone(rs.getString(4), rs.getString(5));
                alunos.add(aluno);
            }

            List<Aluno> result = alunos
                .stream().collect(Collectors.groupingBy(Aluno::getCpf)).entrySet().stream().map(a -> addTelefoneAoAluno(a)).sorted((a1, a2) -> a1.getCpf().getCpf().compareTo(a2.getCpf().getCpf())).toList();
               
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private Aluno addTelefoneAoAluno(Entry<Cpf, List<Aluno>> a) {
        Aluno aluno = a.getValue().get(0);
        for (Aluno value : a.getValue()) {
            aluno.getTelefones().addAll(value.getTelefones());
        }
        return aluno;
    }

}
