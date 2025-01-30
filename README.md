# 📦 Sistema de Gestão Industrial  

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.2-brightgreen.svg)  
![Java](https://img.shields.io/badge/Java-17-blue.svg)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.3-blue.svg)  
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)  
![License](https://img.shields.io/badge/License-MIT-green.svg)  

Sistema completo para gestão integrada de processos industriais com controle de estoque em tempo real, cadastro de fornecedores e monitoramento de linhas de produção.  

---

## ✨ Funcionalidades Principais  

### **Cadastros Avançados**  
- 👥 **Pessoas**: Múltiplos endereços, documentos e contatos  
- 🏭 **Fornecedores**: Avaliação de desempenho e status de crédito  
- 📦 **Produtos**: Controle de estoque mínimo/máximo e categorização  

### **Controle Operacional**  
- 🔄 **Estoque**: Movimentações com histórico reversível  
- 🏗️ **Produção**: Ordens com cálculo automático de progresso  
- 📊 **Linhas**: Monitoramento de eficiência em tempo real  

### **Segurança & Rastreabilidade**  
- 🔍 **Auditoria**: Registro completo de todas as alterações  
- ✅ **Validação**: 3 camadas (DTO, Entidade, Banco)  
- 🌐 **Multi-ambiente**: Configurações independentes para DEV/PROD  

---

## 🛠 Stack Tecnológica  

| Camada           | Tecnologias                                                                 |  
|------------------|-----------------------------------------------------------------------------|  
| **Backend**      | Spring Boot 3.4, JPA/Hibernate, Lombok, Bean Validation 3.0                 |  
| **Banco de Dados**| MySQL 8 (Desenvolvimento), PostgreSQL 15 (Produção)                        |  
| **Documentação** | OpenAPI 3, Swagger UI                                                       |  
| **Testes**       | JUnit 5, Mockito, Testcontainers                                            |  
| **Utilitários**  | ModelMapper, MapStruct, Spring Boot DevTools                                |  

---

## 🚀 Primeiros Passos  

### **Pré-requisitos**  
- JDK 17+  
- Maven 3.9+  
- Docker (opcional para testes com containers)  

### **Instalação**  
```bash  
# 1. Clone o repositório  
git clone https://github.com/seu-usuario/gestao-industrial.git  

# 2. Acesse o diretório  
cd gestao-industrial  

# 3. Instale as dependências  
mvn clean install  

# 4. Execute em ambiente de desenvolvimento  
mvn spring-boot:run -Dspring-boot.run.profiles=dev  
