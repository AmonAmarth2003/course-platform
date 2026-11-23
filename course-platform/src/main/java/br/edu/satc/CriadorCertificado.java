package br.edu.satc;

import java.util.Objects;

public abstract class CriadorCertificado {

    public Certificado emitirCertificado(Matricula matricula) {
        Objects.requireNonNull(matricula, "A matrícula é obrigatória.");
        if (matricula.getStatus() != Matricula.Status.CONCLUIDA) {
            throw new IllegalStateException(
                    "O certificado só pode ser emitido após a conclusão da matrícula.");
        }
        return criarCertificado(matricula);
    }

    protected abstract Certificado criarCertificado(Matricula matricula);
}
