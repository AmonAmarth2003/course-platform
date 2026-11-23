package br.edu.satc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CursoTest {
    @Test
    void criaCursoComDescricaoECargaHoraria() {
        Curso curso = new Curso("Java", "Fundamentos da linguagem", 40);

        assertEquals("Java", curso.getNome());
        assertEquals("Fundamentos da linguagem", curso.getDescricao());
        assertEquals(40, curso.getCargaHorariaHoras());
    }

    @Test
    void rejeitaCargaHorariaInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> new Curso("Java", "Fundamentos da linguagem", 0));
    }
}
