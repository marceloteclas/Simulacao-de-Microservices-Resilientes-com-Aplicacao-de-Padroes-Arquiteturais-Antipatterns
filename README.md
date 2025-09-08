# Simulacao-de-Microservices-Resilientes-com-Aplicacao-de-Padroes-Arquiteturais-Antipatterns
Vivenciar na prática a aplicação de padrões arquiteturais modernos em cenários de microservices. • Implementar Circuit Breaker, Bulkhead e API Gateway em um protótipo distribuído. • Avaliar violações SOLID e anti-patterns comuns em arquiteturas distribuídas. • Usar refatoração guiada e táticas de modularização para corrigir fragilidades.

# Trabalho de Padrões de Projeto – Simulacao-de-Microservices-Resilientes-com-Aplicacao-de-Padroes-Arquiteturais-Antipatterns
- **Instituição:** IFBA - Instituto Federal da Bahia
- **Curso:** Análise e Desenvolvimento de Sistemas (ADS)
- **Disciplina:** Padrões de Projeto 
- **Projeto:** Simulacao-de-Microservices-Resilientes-com-Aplicacao-de-Padroes-Arquiteturais-Antipatterns
- **Professor:** Felipe de Souza Silva
- **Semestre:** 5
- **Ano:** 2025.1

# 📌Simulacao-de-Microservices-Resilientes-com-Aplicacao-de-Padroes-Arquiteturais-Antipatterns

## Integrantes do Projeto

<table>
  <tr>
        <td align="center">
      <img src="https://avatars.githubusercontent.com/u/129338943?v=4" width="100px;" alt="Foto da Integrante Ronaldo"/><br />
      <sub><b><a href="https://github.com/Ronaldo-Correia">Ronaldo Correia</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/114780494?v=4" width="100px;" alt="Foto da Integrante Marcelo"/><br />
      <sub><b><a href="https://github.com/marceloteclas">Marcelo Jesus</a></b></sub>
  </tr>
</table>

## Estrutura do Projeto

- **versao_inicial/**	Versão propositalmente mal projetada. Forte acoplamento, ausência de padrões, responsabilidades misturadas.
- **versao_refatorada/**	
---

# Sistema Mal Projetado – Versão Inicial

## Etapa 1 – Implementação Inicial

**Objetivo:**  
Criar um sistema funcional, porém propositalmente mal projetado, responsabilidades misturadas, forte acoplamento e ausência de padrões de projeto.

### Más práticas propositalmente criadas
- God Object
- Forte acoplamento
- Quebra de encapsulamento
- Mistura de responsabilidades
  
# Sistema Reestruturado – IoC e Service Locator

## Etapa 2 – Arquitetura Moderna

**Objetivo:**
- Vivenciar na prática a aplicação de padrões arquiteturais modernos em cenários 
de microservices. 
- Implementar Circuit Breaker, Bulkhead e API Gateway em um protótipo 
distribuído. 
- Avaliar violações SOLID e anti-patterns comuns em arquiteturas distribuídas. 
- Usar refatoração guiada e táticas de modularização para corrigir fragilidades. .



## 📁 Estrutura de pacotes e arquivos
```
versao_inicial/
  ├── usuarioService/
  └── pedidoService/

versao_refatorada/
  ├── usuarioService/
  ├── pedidoService/
  ├── PagamentoService/
  └── gatewayService/



```

---

## 👨‍💻Como Executar

###📦 Pré-requisitos

Java 17+ instalado (java -version)

Maven 3.8+ instalado (mvn -v)

Git para clonar o projeto

IDE (IntelliJ, Eclipse ou VS Code) ou terminal

1. Clone este repositório:
   ```bash
   git clone https://github.com/marceloteclas/Reestrutura-o-de-sistema-mal-projetado-com-Ioc-Service-Locator.git 
   ```
2. Navegue até o local que foi clonado o repositório:
   Escolha o local ,seja a versão incial ou refatorada
   ```bash
   cd "Local do projeto com pom na raiz"
   ```
4. Compile o código:
   Dentro do terminal, na raiz de cada serviço (usuarioService, pedidoService, gatewayService), execute:
   ```bash
   mvn clean install
   ```
 
6. Suba os microservices (cada um em um terminal separado)
   Versão incial:
     ```bash
   cd versao_inicial/usuarioService
   mvn spring-boot:run
   
   ```
   ```bash
   cd versao_inicial/pedidoService
   mvn spring-boot:run
   
   ```
  

   Versão Refatorada:
   ```bash
   cd versao_refatorada/usuarioService
   mvn spring-boot:run
   
   ```
   ```bash
   cd versao_refatorada/pedidoService
   mvn spring-boot:run
   ```
   
   ```bash
   cd versao_refatorada/PagamentoService
   mvn spring-boot:run
   ```
   
   ```bash
   cd versao_refatorada/gatewayService
   mvn spring-boot:run
   
   ```

8. Testando no navegado ou postman
  1. Listar usuários:
👉 http://localhost:8083/usuarios

  2. Listar pedidos:
👉 http://localhost:8084/pedidos

 3. Microservice de pagamentos:
👉http://localhost:8086/pagamentos

 5. Consultar pedido + usuário via Gateway:
👉 http://localhost:8085/api/pedidos/1

Body para POST:
```json
{
  "pedidoId": 1,
  "valor": 49.90
}

```

##⚠️ Dica: Sempre suba Usuário e Pedido antes do Gateway, senão o Gateway não vai conseguir chamar os serviços.

