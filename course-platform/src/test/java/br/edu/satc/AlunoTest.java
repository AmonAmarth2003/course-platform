package br.edu.satc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AlunoTest {
    @Test
    void criaAlunoComDadosDeContato() {
        Aluno aluno = new Aluno(" Ana Souza ", "ana@example.com", "+55 48 99999-1234");

        assertEquals("Ana Souza", aluno.getNome());
        assertEquals("ana@example.com", aluno.getEmail());
        assertEquals("+55 48 99999-1234", aluno.getTelefone());
    }

    @Test
    void rejeitaTelefoneVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aluno("Ana Souza", "ana@example.com", " "));
    }

    @Test
    void rejeitaNomeOuEmailVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aluno(" ", "ana@example.com", "+55 48 99999-1234"));
        assertThrows(IllegalArgumentException.class,
                () -> new Aluno("Ana Souza", null, "+55 48 99999-1234"));
    }
}
