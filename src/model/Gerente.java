package model;

public class Gerente extends Funcionario {

    private double bonus;

    //Construtor
    public Gerente( String nome, String cpf, double salarioBase, double bonus) {
        super(nome, cpf, salarioBase);
        this.bonus = bonus;
    }

    //Getter
    public double getBonus() {
        return bonus;
    }

    //Metodo da superclasse
    @Override
    public double calcularSalario(){
        return super.getSalarioBase() + this.bonus;
    }


}

