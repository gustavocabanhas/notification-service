# Notification Service - v5.5 📩

Este projeto é um microsserviço especializado, desenvolvido para centralizar e gerenciar o fluxo de notificações de um ecossistema distribuído. O foco principal é o **desacoplamento**, permitindo que aplicações (ERPs, WMS, APIs Financeiras) deleguem a responsabilidade de comunicação sem comprometer a performance do fluxo principal.

---

## 🚀 Diferenciais Técnicos

Diferente de uma integração simples, este serviço foi projetado para ser resiliente e escalável:

* **Arquitetura Baseada em Strategy:** Implementação do Design Pattern *Strategy*, o que permite a expansão imediata para novos canais (WhatsApp, SMS, Push) sem alteração no núcleo do sistema.
* **Processamento Assíncrono com `@Scheduled`:** As solicitações são enfileiradas no banco de dados e processadas em segundo plano, garantindo resposta imediata (Low Latency) para o sistema originador.
* **Retry Logic:** Sistema preparado para lidar com falhas temporárias em provedores SMTP, garantindo que nenhuma notificação importante seja perdida.
* **Controle de Status:** Rastreabilidade total do ciclo de vida da mensagem (PENDING, SENT, ERROR).

---

## 🛠 Tecnologias Utilizadas

- **Java 26 (LTS)** – Performance de última geração.
- **Spring Boot 3.x** – Framework para microsserviços.
- **SQL Server 2025** – Persistência de dados corporativa.
- **JavaMailSender (SMTP)** – Integração segura para envios de e-mail.
- **Spring Data JPA / Hibernate** – Mapeamento objeto-relacional.
- **Maven** – Gestão de build e dependências.

---

## 🗄️ Estrutura do Banco de Dados (Tabela: `notifications`)

| Coluna | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | BIGINT | Chave primária (Identity) |
| `recipient` | VARCHAR(255) | E-mail ou identificador do destino |
| `message` | TEXT | Conteúdo da notificação |
| `status` | VARCHAR(20) | Estado atual (Ex: SENT, PENDING) |
| `channel` | VARCHAR(20) | Canal utilizado (E-MAIL, SMS, etc) |
| `created_at` | DATETIME2 | Data de solicitação |

---

## ⚙️ Configuração e Execução

O projeto utiliza **Variáveis de Ambiente** para garantir a segurança de dados sensíveis, seguindo as recomendações de *12-Factor App*.

### Variáveis Necessárias

| Variável | Descrição |
| :--- | :--- |
| `DB_URL` | String de conexão do SQL Server |
| `DB_USERNAME` | Usuário do banco de dados |
| `DB_PASSWORD` | Senha do banco de dados |
| `MAIL_USERNAME` | Conta de e-mail (SMTP) |
| `MAIL_PASSWORD` | Senha de aplicativo (Google App Password) |

### Como Rodar

1. **Clone o repositório:**
```bash
git clone [https://github.com/SEU-USUARIO/notification-service.git](https://github.com/SEU-USUARIO/notification-service.git)
```
2. **Execute o Build:**
```bash
mvn clean install
```
3. **Inicie o Serviço**
```bash
mvn spring-boot:run
```

### 📊 Endpoints Disponíveis

| Método | Endpoint | Descrição | Status Esperado |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/notifications` | Solicita o envio de uma nova notificação (E-mail/SMS) | `201 Created` |
| `GET` | `/api/notifications` | Consulta o histórico e status de processamento dos envios | `200 OK` |

🚀 **Roadmap de Evolução**

[ ] Integração com Twilio para suporte a WhatsApp/SMS.

[ ] Implementação de Templates HTML dinâmicos para e-mails.

[ ] Migração de fila @Scheduled para RabbitMQ ou Kafka (Mensageria pura).

**Desenvolvido por Gustavo Cabanhas** Analista de TI - Especialista em integração de sistemas e arquitetura de microsserviços.
