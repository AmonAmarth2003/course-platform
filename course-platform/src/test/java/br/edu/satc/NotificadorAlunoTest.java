package br.edu.satc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class NotificadorAlunoTest {
    @Test
    void informaAlunoECursoNaConclusao() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java", "Fundamentos da linguagem", 40));
        matricula.adicionarObservador(new NotificadorAluno());
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;

        try (PrintStream captura = new PrintStream(saida, true, StandardCharsets.UTF_8)) {
            System.setOut(captura);
            matricula.concluirCurso();
        } finally {
            System.setOut(original);
        }

        String texto = saida.toString(StandardCharsets.UTF_8);
        assertTrue(texto.contains("Ana Souza"));
        assertTrue(texto.contains("Java"));
    }
}
