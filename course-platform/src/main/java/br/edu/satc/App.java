package br.edu.satc;

public class App {
    public static void main(String[] args) {
        Curso curso = new Curso(
                "Java para iniciantes",
                "Fundamentos da linguagem Java e orientação a objetos.",
                40);
        Aluno aluno = new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234");
        Matricula matricula = new Matricula(aluno, curso);

        System.out.println("Curso: " + curso.getNome());
        System.out.println("Carga horária: " + curso.getCargaHorariaHoras() + " horas");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Matrícula criada: " + matricula.getStatus());
        System.out.println("Data da matrícula: " + matricula.getDataMatricula());

        matricula.concluirCurso();
        System.out.println("Após a conclusão: " + matricula.getStatus());
        System.out.println("Data da conclusão: " + matricula.getDataConclusao());

        CriadorCertificado criadorCertificado = new CriadorCertificadoConclusao();
        Certificado certificado = criadorCertificado.emitirCertificado(matricula);
        System.out.println("Certificado emitido: " + certificado.formatar());
    }
}
