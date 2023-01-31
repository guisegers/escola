package dominio.aluno;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.dominio.aluno.Email;

class EmailTest {

    @Test
    void naoDeveriaCriarEmailsComEnderecosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
        assertThrows(IllegalArgumentException.class, () -> new Email("emailInvalido"));
        assertThrows(IllegalArgumentException.class, () -> new Email("email.invalido.com"));
        assertThrows(IllegalArgumentException.class, () -> new Email("email@invalido"));
        assertThrows(IllegalArgumentException.class, () -> new Email("email@invalido@com"));
    }

    @Test
    void criaEmailsComEnderecosValidos() {
        assertEquals(new Email("teste@email.com").getEndereco(), "teste@email.com");
        assertEquals(new Email("teste.email@email.com.br").getEndereco(), "teste.email@email.com.br");
        assertEquals(new Email("teste.teste-teste@email.com").getEndereco(), "teste.teste-teste@email.com");
    }

}
