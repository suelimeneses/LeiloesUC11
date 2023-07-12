
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conectaDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

      public boolean connectDB() {

        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leiloes", "root", "Tmzy899999@");
            System.out.println("Conexão realizada com sucesso");
            return true;
        }
        catch (SQLException ex) {
            System.out.println("Sintaxe de conexão inválida ");
            return false;
        }
    }

      public int cadastrar(ProdutosDTO produto) {

        int situacao;
        try {
            prep = conn.prepareStatement("INSERT INTO produto VALUES(?,?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            situacao = prep.executeUpdate();
            return situacao;
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
        
}
