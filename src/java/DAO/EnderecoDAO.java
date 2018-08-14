/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Endereco;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilh
 */
public class EnderecoDAO {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    private String cadastrarEndereco = "insert into Endereco (id, rua, numero, bairro, cep, cidade) values (?, ?, ?, ?, ?, ?)";
    private String buscarIdEndereco = "select max(idEndereco) from Endereco";
    
    public void cadastrarEndereco(Endereco endereco) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(cadastrarEndereco);
            stmt.setInt(1, endereco.getIdEndereco());
            stmt.setString(2, endereco.getRua());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getCidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao cadastrar Ufs: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public int buscarIdEndereco() throws SQLException, ClassNotFoundException {
        int idEnd = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarIdEndereco);
            rs = stmt.executeQuery();
            while(rs.next()) {
                idEnd = rs.getInt(1);
            }
            return idEnd;
        } catch (SQLException ex) {
            out.println("Erro ao listar Enderecos: " + ex.getMessage());
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
        return idEnd;
    }
}
