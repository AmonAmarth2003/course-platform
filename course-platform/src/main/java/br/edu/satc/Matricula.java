package br.edu.satc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matricula {
    public enum Status {
        EM_ANDAMENTO,
        CONCLUIDA
    }

    private final Aluno aluno;
    private final Curso curso;
    private final LocalDate dataMatricula;
    private Status status;
    private LocalDate dataConclusao;
    private final List<ObservadorMatricula> observadores = new ArrayList<>();

    public Matricula(Aluno aluno, Curso curso) {
        this.aluno = Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        this.curso = Objects.requireNonNull(curso, "O curso é obrigatório.");
        this.dataMatricula = LocalDate.now();
        this.status = Status.EM_ANDAMENTO;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void adicionarObservador(ObservadorMatricula observador) {
        observadores.add(Objects.requireNonNull(observador, "O observador é obrigatório."));
    }

    public void removerObservador(ObservadorMatricula observador) {
        observadores.remove(observador);
    }

    public void concluirCurso() {
        if (status == Status.EM_ANDAMENTO) {
            dataConclusao = LocalDate.now();
            status = Status.CONCLUIDA;
            notificarObservadores();
        }
    }

    private void notificarObservadores() {
        for (ObservadorMatricula observador : List.copyOf(observadores)) {
            observador.matriculaConcluida(this);
        }
    }
}
