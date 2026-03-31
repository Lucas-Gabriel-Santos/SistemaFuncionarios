package main;

//Imports
import model.Desenvolvedor;
import model.Funcionario;
import dao.FuncionarioDAO;
import model.Gerente;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        FuncionarioDAO dao = new FuncionarioDAO();
        int opcao = 0;

        System.out.println(" Seja bem vindo ao sistema de funcionários ");

        while(opcao != 5){
            System.out.println("-- Menu --");
            System.out.println("1. Cadastrar Gerente");
            System.out.println("2. Cadastrar Desenvolvedor");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Sair do sistema");

            System.out.println("--------------------------");
            System.out.print("Digite uma das opções: ");
            opcao = input.nextInt();
            input.nextLine(); //Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do Gerente: ");
                    String nomeGerente = input.nextLine();
                    System.out.print("Digite o cpf do Gerente (apenas números): ");
                    String cpfGerente = input.nextLine();
                    System.out.print("Digite o salário base do Gerente: ");
                    double salarioBaseGerente = input.nextDouble();
                    System.out.print("Valor do Bonus do Gerente: ");
                    double bonusGerente = input.nextDouble();

                    //Objeto Gerente
                    Funcionario gerente = new Gerente(nomeGerente, cpfGerente, salarioBaseGerente, bonusGerente);

                    //Salvando no MYSQL
                    dao.salvar(gerente);

                    //Exibir dados
                    System.out.println("------- Dados do Gerente -------");
                    gerente.exibirDados();

                    break;
                case 2:
                    System.out.print("Digite o nome do Desenvolvedor: ");
                    String nomeDesenvolvedor = input.nextLine();
                    System.out.print("Digite o cpf do Desenvolvedor (apenas números): ");
                    String cpfDesenvolvedor = input.nextLine();
                    System.out.print("Digite o salário base do Desenvolvedor: ");
                    double salarioBaseDesenvolvedor = input.nextDouble();
                    System.out.print("Quantidade de Tecnologias: ");
                    int QuantTecDesenvolvedor = input.nextInt();

                    //Objeto Desenvolvedor
                    Funcionario desenvolvedor = new Desenvolvedor(nomeDesenvolvedor, cpfDesenvolvedor, salarioBaseDesenvolvedor, QuantTecDesenvolvedor);

                    //Salvando no MYSQL
                    dao.salvar(desenvolvedor);

                    //Exibir dados
                    System.out.println("------- Dados do Desenvolvedor -------");
                    desenvolvedor.exibirDados();

                    break;
                case 3:
                    System.out.println("--- Listar Funcionário ---");

                    List<Funcionario> funcionarios = dao.buscar();

                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado no banco.");
                    } else {
                        for (Funcionario f : funcionarios) {
                            //Chamando o exibirDados de cada um
                            f.exibirDados();
                        }
                    }

                    break;
                case 4:
                    System.out.println("--- Exluir Funcionário ---");
                    System.out.print("Digite o Id do Funcionário que deseja exluir: ");
                    int idExluir = input.nextInt();
                    input.nextLine(); //Limpar o buffer

                    //Chamando o metodo excluir
                    dao.excluir(idExluir);

                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        input.close();
    }
}
