/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Cargo;
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
public class CargoDAO {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null; 
    private String buscarPorNome = "select idCargo, nomeCargo, salario, requisitos, cargaMinima, descontoImpostos from Cargo where nomeCargo like ? order by idCargo";
    private String buscarTodos = "select idCargo, nomeCargo, salario, requisitos, cargaMinima, descontoImpostos from Cargo order by idCargo";
    private String buscarPorId = "select idCargo, nomeCargo, salario, requisitos, cargaMinima, descontoImpostos from Cargo where idCargo = ?";
    private String atualizarCargo = "update Cargo set nomeCargo = ?, salario = ?, requisitos = ?, cargaMinima = ? , descontoImpostos = ? where idCargo = ?";
    private String removerCargo = "delete from Cargo where idCargo = ?";
    private String cadastrarCargo = "insert into Cargo (nomeCargo, salario, requisitos, cargaMinima, descontoImpostos) values (?, ?, ?, ?, ?)";
    private String buscarNome = "select idCargo, nomeCargo from Cargo order by idCargo";
    
    public List<Cargo> buscarPorNome(String nome) throws SQLException, ClassNotFoundException {
        List<Cargo> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarPorNome);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cargo c = new Cargo();
                c.setIdCargo(rs.getInt("idCargo"));
                c.setNomeCargo(rs.getString("nomeCargo"));
                c.setSalario(rs.getFloat("salario"));
                c.setRequisitos(rs.getString("requisitos"));
                c.setCargaMinima(rs.getInt("cargaMinima"));
                c.setDescontoImpostos(rs.getInt("descontoImpostos"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
        return lista;
    }
    
    public List<Cargo> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Cargo> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarTodos);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cargo c = new Cargo();
                c.setIdCargo(rs.getInt("idCargo"));
                c.setNomeCargo(rs.getString("nomeCargo"));
                c.setSalario(rs.getFloat("salario"));
                c.setRequisitos(rs.getString("requisitos"));
                c.setCargaMinima(rs.getInt("cargaMinima"));
                c.setDescontoImpostos(rs.getInt("descontoImpostos"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
        return lista;
    }
    
    public Cargo buscarPorId(int id) throws SQLException, ClassNotFoundException {
        Cargo c = new Cargo();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarPorId);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                c.setIdCargo(rs.getInt("idCargo"));
                c.setNomeCargo(rs.getString("nomeCargo"));
                c.setSalario(rs.getFloat("salario"));
                c.setRequisitos(rs.getString("requisitos"));
                c.setCargaMinima(rs.getInt("cargaMinima"));
                c.setDescontoImpostos(rs.getInt("descontoImpostos"));
            }
            return c;
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
        return c;
    }
    
    public void atualizarCargo(Cargo cargo) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(atualizarCargo);
            stmt.setString(1, cargo.getNomeCargo());
            stmt.setFloat(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getCargaMinima());
            stmt.setInt(5, cargo.getDescontoImpostos());
            stmt.setInt(6, cargo.getIdCargo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public void removerCargo(int id) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(removerCargo);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao remover Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public void cadastrarCargo(Cargo cargo) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(cadastrarCargo);
            stmt.setString(1, cargo.getNomeCargo());
            stmt.setFloat(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getCargaMinima());
            stmt.setInt(5, cargo.getDescontoImpostos());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public List<Cargo> buscarNomes() throws SQLException, ClassNotFoundException {
        List<Cargo> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarNome);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cargo c = new Cargo();
                c.setIdCargo(rs.getInt("idCargo"));
                c.setNomeCargo(rs.getString("nomeCargo"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            out.println("Erro ao listar Departamentos: " + ex.getMessage());
        } finally {
            stmt.close();
            rs.close();
            con.close();
        }
        return lista;
    }
}
