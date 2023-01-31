package dominio.aluno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.dominio.aluno.Telefone;

public class TelefoneTest {
    @Test
    void naoDeveriaCriarTelefonesInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new Telefone(null, null));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("", ""));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("1", "123123123123123123"));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("11", "123456"));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("2", "12345678"));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("11", "12345678910"));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("11", "1234-5678"));
        assertThrows(IllegalArgumentException.class, () -> new Telefone("11", "12345-6789"));
    }

    @Test
    void criarTelefoneValido() {
        Telefone telefone = null;

        telefone = new Telefone("11", "12345678");
        assertEquals(telefone.getDdd() + telefone.getNumero(), "1112345678");

        telefone = new Telefone("11", "123456789");
        assertEquals(telefone.getDdd() + telefone.getNumero(), "11123456789");


    }

}
