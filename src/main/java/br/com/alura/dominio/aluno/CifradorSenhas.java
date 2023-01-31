package br.com.alura.dominio.aluno;

public interface CifradorSenhas {
    
    String cifrarSenha(String senha);

    boolean validarSenhaCifrada(String senhaCifrada, String senha);

}
