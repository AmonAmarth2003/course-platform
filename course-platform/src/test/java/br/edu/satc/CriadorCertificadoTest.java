package br.edu.satc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class CriadorCertificadoTest {
    @Test
    void emiteCertificadoDeConclusaoParaMatriculaConcluida() {
        Aluno aluno = new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234");
        Curso curso = new Curso("Java para iniciantes", "Fundamentos da linguagem", 40);
        Matricula matricula = new Matricula(aluno, curso);
        matricula.concluirCurso();

        CriadorCertificado criador = new CriadorCertificadoConclusao();
        Certificado certificado = criador.emitirCertificado(matricula);

        assertInstanceOf(CertificadoConclusao.class, certificado);
        assertEquals(CertificadoConclusao.TIPO, certificado.getTipo());
        assertNotNull(certificado.getCodigo());
        assertTrue(certificado.getCodigo().startsWith("CERT-"));
        assertSame(aluno, certificado.getAluno());
        assertSame(curso, certificado.getCurso());
        assertEquals(matricula.getDataConclusao(), certificado.getDataEmissao());

        String texto = certificado.formatar();
        assertTrue(texto.contains("Ana Souza"));
        assertTrue(texto.contains("Java para iniciantes"));
        assertTrue(texto.contains("40h"));
        assertTrue(texto.contains(certificado.getCodigo()));
    }

    @Test
    void rejeitaMatriculaNaoConcluidaAoEmitirCertificado() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java para iniciantes", "Fundamentos da linguagem", 40));

        CriadorCertificado criador = new CriadorCertificadoConclusao();

        assertThrows(IllegalStateException.class, () -> criador.emitirCertificado(matricula));
        assertThrows(IllegalStateException.class, () -> new CertificadoConclusao(matricula));
    }

    @Test
    void rejeitaMatriculaNula() {
        CriadorCertificado criador = new CriadorCertificadoConclusao();

        assertThrows(NullPointerException.class, () -> criador.emitirCertificado(null));
        assertThrows(NullPointerException.class, () -> new CertificadoConclusao(null));
    }

    @Test
    void permiteEstenderComNovoTipoDeCertificadoSemAlterarFluxo() {
        Matricula matricula = new Matricula(
                new Aluno("Ana Souza", "ana@example.com", "+55 48 99999-1234"),
                new Curso("Java para iniciantes", "Fundamentos da linguagem", 40));
        matricula.concluirCurso();

        CriadorCertificado criadorPersonalizado = new CriadorCertificado() {
            @Override
            protected Certificado criarCertificado(Matricula m) {
                return new Certificado() {
                    @Override
                    public String getTipo() {
                        return "Certificado Personalizado";
                    }

                    @Override
                    public String getCodigo() {
                        return "CUSTOM-001";
                    }

                    @Override
                    public Aluno getAluno() {
                        return m.getAluno();
                    }

                    @Override
                    public Curso getCurso() {
                        return m.getCurso();
                    }

                    @Override
                    public LocalDate getDataEmissao() {
                        return m.getDataConclusao();
                    }

                    @Override
                    public String formatar() {
                        return getTipo() + ": " + getAluno().getNome();
                    }
                };
            }
        };

        Certificado certificado = criadorPersonalizado.emitirCertificado(matricula);

        assertEquals("Certificado Personalizado", certificado.getTipo());
        assertEquals("CUSTOM-001", certificado.getCodigo());
        assertEquals("Certificado Personalizado: Ana Souza", certificado.formatar());
    }
}
