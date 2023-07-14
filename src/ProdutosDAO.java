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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


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
     
     
    public int cadastrarProduto (ProdutosDTO produto){
                
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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

