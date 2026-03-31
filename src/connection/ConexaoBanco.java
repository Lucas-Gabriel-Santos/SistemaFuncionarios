package connection;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    // Dados de conexão com o banco
    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USUARIO = "root";
    private static final String SENHA = "Lks@Root125";

    private static Connection conexao = null;

    //Metodo para obter a conexão
    public static Connection getConexao() {
        try {
            //cria uma nova se não houver conexão ou ela estiver fechada
            if (conexao == null || conexao.isClosed()) {
                // Registra o driver do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println("Conexão com o MySQL realizada com sucesso!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do MySQL não encontrado! Adicione o arquivo .jar ao projeto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados!");
            e.printStackTrace();
        }
        return conexao;
    }

    //Metodo para fechar a conexão (muito importante para não travar o banco de dados)
    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão!");
            e.printStackTrace();
        }
    }
}
