package br.edu.satc;

import java.time.LocalDate;

public interface Certificado {
    String getTipo();

    String getCodigo();

    Aluno getAluno();

    Curso getCurso();

    LocalDate getDataEmissao();

    String formatar();
}
