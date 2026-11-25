package br.edu.satc;

public class NotificadorAluno implements ObservadorMatricula {
    @Override
    public void matriculaConcluida(Matricula matricula) {
        Aluno aluno = matricula.getAluno();
        Curso curso = matricula.getCurso();
        System.out.println(
                "Notificacao: " + aluno.getNome()
                        + " concluiu o curso \"" + curso.getNome() + "\".");
    }
}
