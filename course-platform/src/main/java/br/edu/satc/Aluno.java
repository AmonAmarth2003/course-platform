package br.edu.satc;

/** Aluno que pode se matricular em um curso. */
public class Aluno {
    private final String nome;
    private final String email;
    private final String telefone;

    public Aluno(String nome, String email, String telefone) {
        this.nome = validarTexto(nome, "nome do aluno");
        this.email = validarTexto(email, "e-mail do aluno");
        this.telefone = validarTexto(telefone, "telefone do aluno");
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O " + campo + " é obrigatório.");
        }
        return valor.trim();
    }
}
