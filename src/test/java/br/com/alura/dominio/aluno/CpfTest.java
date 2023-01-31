package br.com.alura.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CpfTest {
    @Test
    void naoDeveriaCriarCpfInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new Cpf(null));
        assertThrows(IllegalArgumentException.class, () -> new Cpf(""));
        assertThrows(IllegalArgumentException.class, () -> new Cpf("1"));
        assertThrows(IllegalArgumentException.class, () -> new Cpf("123.456.789-0"));
        assertThrows(IllegalArgumentException.class, () -> new Cpf("123.456.789.10-22"));
        assertThrows(IllegalArgumentException.class, () -> new Cpf("12.345.678.9000.000"));
    }

    @Test
    void criarCpfValido() {
        assertEquals(new Cpf("123.456.789-00").getCpf(), "123.456.789-00");
        assertEquals(new Cpf("12345678900").getCpf(), "12345678900");
    }
}
