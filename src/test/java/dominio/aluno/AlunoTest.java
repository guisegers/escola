package dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.alura.dominio.aluno.Aluno;
import br.com.alura.dominio.aluno.AlunoBuilder;

public class AlunoTest {
    
    @Test
    public void criarAlunoUsandoAlunoBuilderComDadosErrados(){
      
        assertThrows(IllegalArgumentException.class, () -> AlunoBuilder.builder("", "", null).build());
    }

    @Test
    public void criarAlunoUsandoAlunoBuilderComSucesso(){
        Aluno aluno = AlunoBuilder.builder("123.456.789-01", "Eu mesmo", "eumesmo@email.com").comTelefone("11", "123456789").build();
        
        assertEquals(aluno.getCpf().getCpf(), "123.456.789-01");
        assertEquals(aluno.getNome(), "Eu mesmo");
        assertEquals(aluno.getEmail().getEndereco(), "eumesmo@email.com");
        assertEquals(aluno.getTelefones().stream().findFirst().get().getDdd(), "11");
        assertEquals(aluno.getTelefones().stream().findFirst().get().getNumero(), "123456789");
    }

}
