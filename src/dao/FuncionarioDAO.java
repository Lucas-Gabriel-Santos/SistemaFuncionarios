package dao;

//Imports
import model.Desenvolvedor;
import model.Funcionario;
import model.Gerente;

import connection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAO {

    public void salvar(Funcionario f) {
        String sql = "INSERT INTO funcionarios (nome, cpf, salarioBase, bonus, quantTecnologia, tipoFuncionario) VALUES (?, ?, ?, ?, ?, ?)";

        //O try-with-resources abre e fecha a conexão e o statement automaticamente
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            //Esses dados todos funcionarios tem
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf());
            stmt.setDouble(3, f.getSalarioBase());

            if (f instanceof Gerente) {
                Gerente g = (Gerente) f; //Coerção para acessar os dados de Gerente
                stmt.setDouble(4, g.getBonus());
                stmt.setNull(5, java.sql.Types.INTEGER); //Desenvolvedor não tem tecnologia
                stmt.setString(6, "GERENTE");

            } else if (f instanceof Desenvolvedor) {
                Desenvolvedor d = (Desenvolvedor) f; // Coerção para acessar os dados de Desenvolvedor
                stmt.setNull(4, java.sql.Types.DOUBLE); // Gerente não tem bônus de Desenvolvedor
                stmt.setInt(5, d.getQuantTecnologia());
                stmt.setString(6, "DESENVOLVEDOR");
            }

            //Executa o comando no banco de dados
            stmt.executeUpdate();

            //Retorna o Id do banco ao objeto java
            try (java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    f.setId(idGerado);
                }
            }

            System.out.println("Funcionário " + f.getNome() + " salvo com sucesso no MySQL!");

        }
        catch (SQLException e) {
            System.err.println("Erro ao salvar o funcionário no banco de dados.");
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try(Connection conn = ConexaoBanco.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            //Passamos o Id recebido para a interrogação do comando SQL
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário com Id " + id + " excluído com sucesso!");
            }
            else{
                System.out.println("Aviso: Nenhum funcionário foi encontrado com o Id " + id);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao excluir do banco: " + e.getMessage());
        }
    }

    public List<Funcionario> buscar() {
        List<Funcionario> funcionarioslista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try(Connection conn = ConexaoBanco.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()) {
                int idFuncionario = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                double salarioBase = rs.getDouble("salarioBase");
                String tipoFuncionario = rs.getString("tipoFuncionario");

                if(tipoFuncionario.equals("GERENTE")) {
                    double bonus = rs.getDouble("bonus");
                    Gerente g = new Gerente(nome, cpf, salarioBase, bonus);
                    g.setId(idFuncionario);
                    funcionarioslista.add(g);
                }
                else if(tipoFuncionario.equals("DESENVOLVEDOR")) {
                    int quantTec = rs.getInt("quantTecnologia");
                    Desenvolvedor d = new Desenvolvedor(nome, cpf, salarioBase, quantTec);
                    d.setId(idFuncionario);
                    funcionarioslista.add(d);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao buscar funcionários: " + e.getMessage());
        }

        return funcionarioslista;

    }
}
