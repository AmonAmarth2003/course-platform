package br.edu.satc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MatriculaTest {
    @Test
    void notificaTodosOsObservadoresUmaVezNaConclusao() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java", "Fundamentos da linguagem", 40));
        final int[] notificacoes = {0};
        ObservadorMatricula primeiro = observada -> notificacoes[0]++;
        ObservadorMatricula segundo = observada -> notificacoes[0]++;
        matricula.adicionarObservador(primeiro);
        matricula.adicionarObservador(segundo);

        matricula.concluirCurso();
        matricula.concluirCurso();

        assertEquals(2, notificacoes[0]);
    }

    @Test
    void removeObservadorAntesDaConclusao() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java", "Fundamentos da linguagem", 40));
        final int[] notificacoes = {0};
        ObservadorMatricula observador = observada -> notificacoes[0]++;
        matricula.adicionarObservador(observador);
        matricula.removerObservador(observador);

        matricula.concluirCurso();

        assertEquals(0, notificacoes[0]);
    }

    @Test
    void rejeitaObservadorNulo() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java", "Fundamentos da linguagem", 40));

        assertThrows(NullPointerException.class,
                () -> matricula.adicionarObservador(null));
    }

    @Test
    void notificaObservadorQuandoAMatriculaEConcluida() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java", "Fundamentos da linguagem", 40));
        final boolean[] notificada = {false};
        matricula.adicionarObservador(observada -> notificada[0] = observada == matricula);

        matricula.concluirCurso();

        assertTrue(notificada[0]);
    }

    @Test
    void registraMatriculaEConclusaoDoCurso() {
        Aluno aluno = new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234");
        Curso curso = new Curso("Java", "Fundamentos da linguagem", 40);
        Matricula matricula = new Matricula(aluno, curso);

        assertSame(aluno, matricula.getAluno());
        assertSame(curso, matricula.getCurso());
        assertEquals(Matricula.Status.EM_ANDAMENTO, matricula.getStatus());
        assertNotNull(matricula.getDataMatricula());
        assertNull(matricula.getDataConclusao());

        matricula.concluirCurso();

        assertEquals(Matricula.Status.CONCLUIDA, matricula.getStatus());
        assertNotNull(matricula.getDataConclusao());
    }

    @Test
    void concluirNovamentePreservaDataDaPrimeiraConclusao() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java", "Fundamentos da linguagem", 40));
        matricula.concluirCurso();
        var primeiraDataConclusao = matricula.getDataConclusao();

        matricula.concluirCurso();

        assertEquals(primeiraDataConclusao, matricula.getDataConclusao());
    }

    @Test
    void exigeAlunoECursoNaMatricula() {
        Curso curso = new Curso("Java", "Fundamentos da linguagem", 40);
        Aluno aluno = new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234");

        assertThrows(NullPointerException.class, () -> new Matricula(null, curso));
        assertThrows(NullPointerException.class, () -> new Matricula(aluno, null));
    }
}
