## Contribuições da equipe

Divisão de contribuições informada pela equipe para a Parte 1:

| Integrante | PR sugerido | Contribuição |
| --- | --- | --- |
| João Vitor Oliveira | `feat: implementar fluxo de matrícula` | Criar `Curso`, `Aluno` e `Matricula` e implementar o fluxo básico de matrícula e conclusão. |
| Leonardo Da Cruz Ramos | `feat: emitir certificado com Factory Method` | Implementar o Factory Method para criar e emitir o certificado de conclusão, preparando a inclusão de outro tipo de certificado na Parte 2. |
| André Luiz Oenning | `feat: notificar aluno com Observer` | Implementar o Observer para notificar o aluno quando a matrícula for concluída, incluindo registro, remoção e emissão do evento. |
| Manoel Bitencourt Mesquita | `feat: integrar fluxo e documentar aplicação` | Integrar o fluxo executável, documentar execução, exemplos, patterns e contribuições no README. |

# Plataforma de cursos

Projeto acadêmico de Design Patterns 2026.2. A aplicação demonstra o fluxo básico de uma plataforma de cursos: cadastrar um curso e um aluno, criar uma matrícula e registrar a conclusão do curso. Os dados ficam em memória; a aplicação não usa banco de dados.

## Tecnologias e pré-requisitos

- Java JDK 17 ou superior.
- Apache Maven 3.9 ou superior.
- JUnit Jupiter 5.13.4 para os testes automatizados.

Na primeira execução, o Maven precisa de acesso à internet para resolver as dependências declaradas no `pom.xml`.

## Execução

No terminal, a partir da pasta que contém o `pom.xml`:

```bash
mvn test
mvn compile
java -cp target/classes br.edu.satc.App
```

O primeiro comando compila e executa os testes. Os comandos seguintes compilam e iniciam o exemplo no console.

## Exemplo de uso

O programa cria o curso “Java para iniciantes”, com descrição e carga horária, e a aluna Ana Souza, com e-mail e telefone. Em seguida, cria a matrícula, inicialmente `EM_ANDAMENTO`, e registra a conclusão, alterando o status para `CONCLUIDA`.

As datas de matrícula e conclusão são exibidas no formato `AAAA-MM-DD`. A data de conclusão permanece igual caso `concluirCurso()` seja chamado novamente.

## Entidades e fluxo

- `Curso`: nome, descrição e carga horária em horas. Nome e descrição são obrigatórios; a carga horária deve ser positiva.
- `Aluno`: nome, e-mail e telefone, todos obrigatórios.
- `Matricula`: associa um aluno a um curso e registra status, data de matrícula e, após a conclusão, data de conclusão.
- `App`: demonstra o fluxo completo no console.

## Patterns

Os dois patterns definidos para o projeto são Factory Method e Observer. Nesta etapa, ambos estão implementados.

### Factory Method — emissão de certificado

- **Problema resolvido:** separar a emissão do certificado do fluxo de conclusão da matrícula e permitir incluir novos tipos de certificado na Parte 2 sem alterar esse fluxo.
- **Classes implementadas:** `Certificado` (produto), `CertificadoConclusao` (produto concreto), `CriadorCertificado` (criador com o factory method `criarCertificado` e operação `emitirCertificado`) e `CriadorCertificadoConclusao` (criador concreto).

### Observer — notificação de conclusão

- **Problema resolvido:** notificar o aluno quando a matrícula for concluída sem acoplar a regra da matrícula diretamente a cada canal de notificação; isso permite adicionar outra notificação na Parte 2.
- **Classes implementadas:** `ObservadorMatricula` (observador), `NotificadorAluno` (observador concreto) e `Matricula` (origem do evento de conclusão).
- **Comportamento:** ao concluir uma matrícula pela primeira vez, `Matricula` notifica todos os observadores registrados. Concluir novamente não gera uma nova notificação.

## Testes

Os testes em `src/test/java` verificam os dados das entidades, validações, vínculo da matrícula e transição para concluída. Execute `mvn test` para rodá-los.


## Declaração de uso de IA

Foi utilizado o modelo **OpenAI GPT-6 (Codex)** como apoio na implementação das entidades e do fluxo básico, na criação dos testes automatizados e na atualização desta documentação. O código e a documentação devem ser revisados pela equipe antes da entrega. Esta declaração cobre o trabalho da Parte 1 registrado neste repositório.
