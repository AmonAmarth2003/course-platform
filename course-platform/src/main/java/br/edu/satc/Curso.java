package br.edu.satc;

public class Curso {
    private final String nome;
    private final String descricao;
    private final int cargaHorariaHoras;

    public Curso(String nome, String descricao, int cargaHorariaHoras) {
        this.nome = validarTexto(nome, "nome do curso");
        this.descricao = validarTexto(descricao, "descrição do curso");
        if (cargaHorariaHoras <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser maior que zero.");
        }
        this.cargaHorariaHoras = cargaHorariaHoras;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCargaHorariaHoras() {
        return cargaHorariaHoras;
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O " + campo + " é obrigatório.");
        }
        return valor.trim();
    }
}
