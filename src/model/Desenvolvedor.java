package model;

public class Desenvolvedor extends Funcionario {

    private int quantTecnologia;
    private double bonusPorTec = 250.00;

    //Construtor
    public Desenvolvedor(String nome, String cpf, double salarioBase, int quantTecnologia) {
        super(nome, cpf, salarioBase);
        this.quantTecnologia = quantTecnologia;
    }

    //Getter
    public int getQuantTecnologia() {
        return this.quantTecnologia;
    }

    public void setQuantTecnologia(int quantTecnologia) {
        this.quantTecnologia = quantTecnologia;
    }

    //Metodo da Superclasse
    @Override
    public double calcularSalario() {
        double bonusNoTotal = this.getQuantTecnologia() * this.bonusPorTec;
        return super.getSalarioBase() + bonusNoTotal;
    }
}
