# SistemaFuncionários
Projeto de sistema de funcionários em Java com integração ao banco de dados MySQL.

## 📝 Conceitos usados para o projeto
- Programação Orientada a Objetos (Herança, Polimorfismo e Encapsulamento);
- Padrão de Projeto DAO (Data Access Object);
- Arquitetura em Camadas;
- JDBC e Driver MySQL.

## 💻 Funcionalidades
- Cadastro de Funcionários por Categoria (Gerente ou Desenvolvedor);
- Listagem em Tempo Real;
- Exclusão com base no Id do funcionário;
- Persistência de Dados Automatizada.

## ⚒️ Tecnologias
- Java 25
- MySQL
- Intellij IDE
  
## 📁 Estrutura
```text
SistemaFuncionarios/
├── lib/                       
│   └── mysql-connector-j-9.6.0.jar
│
├── src/                        
│   │
│   ├── connection/             
│   │   └── ConexaoBanco.java   
│   │
│   ├── dao/                    
│   │   └── FuncionarioDAO.java
│   │
│   ├── main/                  
│   │   └── Main.java          
│   │
│   └── model/                  
│       ├── Desenvolvedor.java  
│       ├── Funcionario.java    
│       └── Gerente.java
```
