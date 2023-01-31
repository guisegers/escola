package br.com.alura.infra;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.alura.dominio.aluno.CifradorSenhas;

public class CifradorSenhasServiceMD5 implements CifradorSenhas {

    @Override
    public String cifrarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(senha.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar HASH SHA-1", e);
        }
    }

    @Override
    public boolean validarSenhaCifrada(final String senhaCifrada, final String senha) {
        return senhaCifrada.equals(this.cifrarSenha(senha));
    }

}
