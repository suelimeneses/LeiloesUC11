/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {

    private Connection conn;
    private conectaDAO conectaDAO;
    PreparedStatement prep;
    ResultSet resultset;

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        conectaDAO = new conectaDAO();
        conn = conectaDAO.connectDB();
    }

    public int cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        int situacao;
        try {
            PreparedStatement prep = conn.prepareStatement("INSERT INTO `leiloes`.`produtos` (`nome`, `valor`, `status`)  VALUES(?,?,?)");
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

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";

        try {

            conectaDAO.connectDB();
            Connection conn = conectaDAO.connectDB();

            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            listagem.clear();

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException ex) {

        }
        return listagem;
    }

    public int venderProduto(int id) {
        int status;
        try {
            conectaDAO.connectDB();
            Connection conn = conectaDAO.connectDB();
            String produtoVendido = "Vendido";
            PreparedStatement st = conn.prepareStatement("UPDATE produtos SET status =? WHERE id =? ");
            st.setInt(2, id);
            st.setString(1, produtoVendido);
            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }

    public List<ProdutosDTO> listarProdutosVendidos(String statusVenda) {
        String sql = "SELECT * FROM produtos WHERE status = ? ";
        try {
            conectaDAO.connectDB();
            Connection conn = conectaDAO.connectDB();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, statusVenda);
            ResultSet rs = st.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar: " + ex.getMessage());
            return null;
        }
    }
}
