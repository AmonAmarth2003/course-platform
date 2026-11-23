package br.edu.satc;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class CertificadoConclusao implements Certificado {
    public static final String TIPO = "Certificado de Conclusão";

    private final String codigo;
    private final Aluno aluno;
    private final Curso curso;
    private final LocalDate dataEmissao;

    public CertificadoConclusao(Matricula matricula) {
        Objects.requireNonNull(matricula, "A matrícula é obrigatória.");
        if (matricula.getStatus() != Matricula.Status.CONCLUIDA) {
            throw new IllegalStateException(
                    "O certificado só pode ser emitido para uma matrícula concluída.");
        }
        this.codigo = gerarCodigo();
        this.aluno = matricula.getAluno();
        this.curso = matricula.getCurso();
        this.dataEmissao = matricula.getDataConclusao() != null
                ? matricula.getDataConclusao()
                : LocalDate.now();
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public Aluno getAluno() {
        return aluno;
    }

    @Override
    public Curso getCurso() {
        return curso;
    }

    @Override
    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    @Override
    public String formatar() {
        return String.format(
                "[%s - %s] Certificamos que %s concluiu o curso \"%s\" (%dh) em %s.",
                TIPO,
                codigo,
                aluno.getNome(),
                curso.getNome(),
                curso.getCargaHorariaHoras(),
                dataEmissao);
    }

    private static String gerarCodigo() {
        return "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
