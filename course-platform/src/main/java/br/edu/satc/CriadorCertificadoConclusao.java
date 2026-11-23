package br.edu.satc;

public class CriadorCertificadoConclusao extends CriadorCertificado {

    @Override
    protected Certificado criarCertificado(Matricula matricula) {
        return new CertificadoConclusao(matricula);
    }
}
