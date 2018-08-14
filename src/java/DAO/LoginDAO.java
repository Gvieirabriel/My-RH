/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Cargo;
import Beans.Departamento;
import Beans.Endereco;
import Beans.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guilh
 */
public class LoginDAO {
    private String stmtBuscaFuncionario = "SELECT f.idFuncionario, f.nomeFuncionario, f.cpf, f.rg, f.celular, f.email, d.nomeDepartamento, d.localizacao, c.nomeCargo, c.salario, c.requisitos, c.cargaMinima, c.descontoImpostos, e.rua, e.numero, e.bairro, e.cep, e.cidade "
            + "FROM Funcionario f "
            + "INNER JOIN Departamento d ON f.idDepartamento = d.idDepartamento "
            + "INNER JOIN Cargo c ON f.idCargo = c.idCargo "
            + "INNER JOIN Endereco e ON f.idEnd = e.id "
            + "WHERE f.email = ? and f.senha = ?";
    
    public Funcionario lerFuncionario (String email, String senha){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtBuscaFuncionario);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();   
             while (rs.next()) {
                Funcionario f = new Funcionario();
                Cargo c = new Cargo();
                Departamento d = new Departamento();
                Endereco e = new Endereco();
                c.setNomeCargo(rs.getString("c.nomeCargo"));
                c.setSalario(rs.getFloat("c.salario"));
                c.setRequisitos(rs.getString("c.requisitos"));
                c.setCargaMinima(rs.getInt("c.cargaMinima"));
                c.setDescontoImpostos(rs.getInt("c.descontoImpostos"));
                d.setNomeDepartamento(rs.getString("d.nomeDepartamento"));
                d.setLocalizacao(rs.getString("d.localizacao"));
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setBairro(rs.getString("e.bairro"));
                e.setCep(rs.getString("e.cep"));
                e.setCidade(rs.getString("e.cidade"));
                f.setIdFuncionario(rs.getInt("f.idFuncionario"));
                f.setNomeFuncionario(rs.getString("f.nomeFuncionario"));
                f.setCpf(rs.getString("f.cpf"));
                f.setRg(rs.getString("f.rg"));
                f.setCelular(rs.getString("f.celular"));
                f.setEmail(rs.getString("f.email"));
                f.setCargo(c);
                f.setDepartamento(d);
                f.setEndereco(e);
                return f;
            }
            return null;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar funcionario" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }
}
