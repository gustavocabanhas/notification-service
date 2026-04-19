# Notification Service (Java & Spring Boot)

Este projeto é um microsserviço criado para centralizar o envio de notificações de forma simples, organizada e escalável. A proposta é desacoplar essa responsabilidade de outros sistemas, permitindo que ERPs, WMS ou qualquer outra aplicação apenas solicitem o envio, enquanto o processamento acontece em segundo plano.

Com isso, os sistemas principais continuam performando bem, sem precisar esperar pelo envio de e-mails ou futuras integrações com outros canais.

## Tecnologias Utilizadas

- **Java 21 (LTS)**
- **Spring Boot 3.x**
- **SQL Server 2025**
- **Spring Data JPA / Hibernate**
- **JavaMailSender (SMTP Gmail)**
- **Maven**
- **Variáveis de ambiente para dados sensíveis**

## Estrutura e Decisões Técnicas

Durante o desenvolvimento, a preocupação principal foi criar uma base limpa, reutilizável e preparada para evoluir.

### 1. Suporte para múltiplos canais

O sistema foi estruturado utilizando o padrão **Strategy**, permitindo adicionar novos canais de notificação no futuro, como:

- WhatsApp  
- SMS  
- Push Notification  
- E-mail (canal atual)

Tudo isso sem precisar reescrever a lógica principal do serviço.

### 2. Processamento em segundo plano

As notificações são processadas automaticamente por uma rotina agendada com `@Scheduled`.

Isso traz algumas vantagens importantes:

- **Resposta imediata** para quem solicita o envio  
- **Menor carga** nos sistemas integrados  
- **Reenvio automático** em caso de falha temporária no servidor SMTP  
- **Controle de status** das notificações no banco de dados

### 3. Foco em manutenção

A organização do projeto segue boas práticas de separação de responsabilidades, facilitando leitura, testes e futuras melhorias.

## Como Executar

### Variáveis de Ambiente

Para manter credenciais seguras, o projeto utiliza variáveis de ambiente:

| Variável | Descrição |
|---|---|
| `DB_USERNAME` | Usuário do banco SQL Server |
| `DB_PASSWORD` | Senha do banco SQL Server |
| `MAIL_USERNAME` | Conta de e-mail utilizada nos envios |
| `MAIL_PASSWORD` | Senha de aplicativo da Conta Google |

## Endpoints Principais

- `POST /api/notifications` → Cria uma nova solicitação de envio  
- `GET /api/notifications` → Consulta histórico e status das notificações

---

Projeto desenvolvido com foco em aprendizado prático de arquitetura backend, microsserviços e integração entre sistemas reais.
