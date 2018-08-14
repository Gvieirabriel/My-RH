    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Folha;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guilh
 */
public class FolhaDAO {Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    private String inserirFolha = "insert into Folha (idFuncionario, horasTrabalhadas, mes, ano, salarioLiquido, salarioBruto) values (?, ?, ?, ?, ?, ?)";
    private String verifica = "select idFolha from Folha where mes = ? and ano = ?";
    
    public void inserirFolha(Folha folha) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(inserirFolha);
            stmt.setInt(1, folha.getFuncionario().getIdFuncionario());
            stmt.setInt(2, folha.getHorasTrabalhadas());
            stmt.setInt(3, folha.getMes());
            stmt.setInt(4, folha.getAno());
            stmt.setFloat(5, folha.getSalarioLiquido());
            stmt.setFloat(6, folha.getSalarioBruto());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao cadastrar Folha: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public boolean verifica(int mes, int ano) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(verifica);
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            rs = stmt.executeQuery();
            if (rs.next())
                return false;
            return true;
        } catch (SQLException ex) {
            out.println("Erro ao verificar Folha: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
            rs.close();
        }
        return true;
    }
}
