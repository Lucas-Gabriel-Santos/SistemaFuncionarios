package model;

public abstract class Funcionario {
    public int id;
    private String nome;
    private String cpf;
    private double salarioBase;

    //CONSTRUTOR 1: para quando formos CADASTRAR (Não precisa de ID)
    public Funcionario(String nome, String cpf, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    //CONSTRUTOR 2: Para usar no "DAO" na hora de BUSCAR do banco (Com ID)
    public Funcionario(int id, String nome, String cpf, double salarioBase){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    //Metodo
    public abstract double calcularSalario();

    public void exibirDados(){
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.printf("Salário Base: R$ %.2f\n", this.salarioBase);
        System.out.printf("Salário Final Calculado: R$ %.2f\n", this.calcularSalario());
        System.out.println("----------------------------------");
    }
}


