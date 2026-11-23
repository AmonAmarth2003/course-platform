package br.edu.satc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MatriculaTest {
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
