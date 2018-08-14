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
public class FuncionarioDAO {
    Connection con = null;
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;            
    PreparedStatement stmt3 = null;
    PreparedStatement stmt4 = null;
    PreparedStatement stmt5 = null;
    ResultSet rs = null;
    private String cadastrarEndereco = "insert into Funcionario (idEndereco, idCargo, idDepartamento, nomeFuncionario, cpf, rg, celular, email, senha) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String buscarTodos = "select f.idFuncionario, f.nomeFuncionario, f.cpf, f.rg, f.celular, f.email, e.rua, e.numero, e.bairro, e.cep, e.cidade, u.sigla, d.idDepartamento, d.nomeDepartamento, c.idCargo, c.nomeCargo, c.cargaMinima, c.salario, c.descontoImpostos from Funcionario f inner join Endereco e on f.idEndereco = e.idEndereco inner join UF u on e.idUF = u.idUF inner join Departamento d on d.idDepartamento = f.idDepartamento inner join Cargo c on f.idCargo = c.idCargo order by f.idFuncionario";
    private String buscarPorNome = "select f.idFuncionario, f.nomeFuncionario, f.cpf, f.rg, f.celular, f.email, e.rua, e.numero, e.bairro, e.cep, e.cidade, u.sigla, d.nomeDepartamento, c.nomeCargo from Funcionario f inner join Endereco e on f.idEndereco = e.idEndereco inner join UF u on e.idUF = u.idUF inner join Departamento d on d.idDepartamento = f.idDepartamento inner join Cargo c on f.idCargo = c.idCargo where f.nomeFuncionario like ? order by f.idFuncionario";
    private String buscarPorId = "select f.idFuncionario, f.nomeFuncionario, f.cpf, f.rg, f.celular, f.email, e.rua, e.numero, e.bairro, e.cep, e.cidade, u.sigla, d.nomeDepartamento, c.nomeCargo, c.salario, c.cargaMinima, c.descontoImpostos from Funcionario f inner join Endereco e on f.idEndereco = e.idEndereco inner join UF u on e.idUF = u.idUF inner join Departamento d on d.idDepartamento = f.idDepartamento inner join Cargo c on f.idCargo = c.idCargo where f.idFuncionario = ?";
    private String removerFuncionario = "delete from Funcionario where idFuncionario = ?";
    private String alterarFuncionario = "update Funcionario set idEndereco = ?, idCargo = ?, idDepartamento = ?, nomeFuncionario = ?, cpf = ?, rg = ?, celular = ?, email = ? where idFuncionario = ?";
    private String alterarEndereco = "update Endereco set idUF = ?, rua = ?, numero = ?, bairro = ?, cep = ?, cidade = ? where idEndereco = ?";
    private String getIdUF = "select idUF from UF where sigla = ?";
    private String getIdCargo = "select idCargo from Cargo where nomeCargo = ?";
    private String getIdDepartamento = "select idDepartamento from Departamento where nomeDepartamento = ?";
    private String buscarLogin = "select f.idFuncionario, f.nomeFuncionario, f.cpf, f.rg, f.celular, f.email, e.rua, e.numero, e.bairro, e.cep, e.cidade, u.sigla, d.idDepartamento, d.nomeDepartamento, c.idCargo, c.nomeCargo from Funcionario f inner join Endereco e on f.idEndereco = e.idEndereco inner join UF u on e.idUF = u.idUF inner join Departamento d on d.idDepartamento = f.idDepartamento inner join Cargo c on f.idCargo = c.idCargo where f.email = ? and f.senha = ?";
    private String buscarPorDepartamento = "select f.idFuncionario, f.nomeFuncionario, f.cpf, f.rg, f.celular, f.email, e.rua, e.numero, e.bairro, e.cep, e.cidade, u.sigla, d.idDepartamento, d.nomeDepartamento, c.idCargo, c.nomeCargo from Funcionario f inner join Endereco e on f.idEndereco = e.idEndereco inner join UF u on e.idUF = u.idUF inner join Departamento d on d.idDepartamento = f.idDepartamento inner join Cargo c on f.idCargo = c.idCargo where d.idDepartamento = ? and c.idCargo != 1";

    
    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(cadastrarEndereco);
            stmt.setInt(1, funcionario.getEndereco().getIdEndereco());
            stmt.setInt(2, funcionario.getCargo().getIdCargo());
            stmt.setInt(3, funcionario.getDepartamento().getIdDepartamento());
            stmt.setString(4, funcionario.getNomeFuncionario());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getRg());
            stmt.setString(7, funcionario.getCelular());
            stmt.setString(8, funcionario.getEmail());
            stmt.setString(9, funcionario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao cadastrar Funcionario: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public List<Funcionario> buscarTodos() throws SQLException, ClassNotFoundException {
        List<Funcionario> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarTodos);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                Endereco e = new Endereco();
                Departamento d = new Departamento();
                Cargo c = new Cargo();
                f.setIdFuncionario(rs.getInt("f.idFuncionario"));
                f.setNomeFuncionario(rs.getString("f.nomeFuncionario"));
                f.setCpf(rs.getString("f.cpf"));
                f.setRg(rs.getString("f.rg"));
                f.setCelular(rs.getString("f.celular"));
                f.setEmail(rs.getString("f.email"));
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setBairro(rs.getString("e.bairro"));
                e.setCep(rs.getString("e.cep"));
                e.setCidade(rs.getString("e.cidade"));
                e.setUf(rs.getString("u.sigla"));
                d.setIdDepartamento(rs.getInt("d.idDepartamento"));
                d.setNomeDepartamento(rs.getString("d.nomeDepartamento"));
                c.setIdCargo(rs.getInt("c.idCargo"));
                c.setNomeCargo(rs.getString("c.nomeCargo"));
                c.setCargaMinima(rs.getInt("c.cargaMinima"));
                c.setSalario(rs.getFloat("c.salario"));
                c.setDescontoImpostos(rs.getInt("c.descontoImpostos"));
                f.setCargo(c);
                f.setEndereco(e);
                f.setDepartamento(d);
                lista.add(f);
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
    
    public List<Funcionario> buscarPorNome(String nome) throws SQLException, ClassNotFoundException {
        List<Funcionario> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarPorNome);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                Endereco e = new Endereco();
                Departamento d = new Departamento();
                Cargo c = new Cargo();
                f.setIdFuncionario(rs.getInt("f.idFuncionario"));
                f.setNomeFuncionario(rs.getString("f.nomeFuncionario"));
                f.setCpf(rs.getString("f.cpf"));
                f.setRg(rs.getString("f.rg"));
                f.setCelular(rs.getString("f.celular"));
                f.setEmail(rs.getString("f.email"));
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setBairro(rs.getString("e.bairro"));
                e.setCep(rs.getString("e.cep"));
                e.setCidade(rs.getString("e.cidade"));
                e.setUf(rs.getString("u.sigla"));
                d.setNomeDepartamento(rs.getString("d.nomeDepartamento"));
                c.setNomeCargo(rs.getString("c.nomeCargo"));
                f.setCargo(c);
                f.setEndereco(e);
                f.setDepartamento(d);
                lista.add(f);
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
    
    public void removerFuncionario(int id) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(removerFuncionario);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao remover Funcionarios: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public Funcionario buscarPorId(int id) throws SQLException, ClassNotFoundException {
        Funcionario f = new Funcionario();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarPorId);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                Departamento d = new Departamento();
                Cargo c = new Cargo();
                f.setIdFuncionario(rs.getInt("f.idFuncionario"));
                f.setNomeFuncionario(rs.getString("f.nomeFuncionario"));
                f.setCpf(rs.getString("f.cpf"));
                f.setRg(rs.getString("f.rg"));
                f.setCelular(rs.getString("f.celular"));
                f.setEmail(rs.getString("f.email"));
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setBairro(rs.getString("e.bairro"));
                e.setCep(rs.getString("e.cep"));
                e.setCidade(rs.getString("e.cidade"));
                e.setUf(rs.getString("u.sigla"));
                d.setNomeDepartamento(rs.getString("d.nomeDepartamento"));
                c.setNomeCargo(rs.getString("c.nomeCargo"));
                c.setCargaMinima(rs.getInt("c.cargaMinima"));
                c.setSalario(rs.getFloat("c.salario"));
                c.setDescontoImpostos(rs.getInt("c.descontoImpostos"));
                f.setCargo(c);
                f.setEndereco(e);
                f.setDepartamento(d);
            }
            return f;
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
        return f;
    }
    
    public void alterarFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        try {
            con = ConnectionFactory.getConnection();
            stmt2 = con.prepareStatement(alterarEndereco);
            stmt5 = con.prepareStatement(alterarFuncionario);
            stmt2.setInt(1, funcionario.getEndereco().getIdUf());
            stmt2.setString(2, funcionario.getEndereco().getRua());
            stmt2.setInt(3, funcionario.getEndereco().getNumero());
            stmt2.setString(4, funcionario.getEndereco().getBairro());
            stmt2.setString(5, funcionario.getEndereco().getCep());
            stmt2.setString(6, funcionario.getEndereco().getCidade());
            stmt2.setInt(7, funcionario.getIdFuncionario());
            stmt2.executeUpdate();
            stmt5.setInt(1, funcionario.getIdFuncionario());
            stmt5.setInt(2, funcionario.getCargo().getIdCargo());
            stmt5.setInt(3, funcionario.getDepartamento().getIdDepartamento());
            stmt5.setString(4, funcionario.getNomeFuncionario());
            stmt5.setString(5, funcionario.getCpf());
            stmt5.setString(6, funcionario.getRg());
            stmt5.setString(7, funcionario.getCelular());
            stmt5.setString(8, funcionario.getEmail());
            stmt5.setInt(9, funcionario.getIdFuncionario());
            stmt5.executeUpdate();
        } catch (SQLException ex) {
            out.println("Erro ao alterar Funcionario: " + ex.getMessage());
        } finally {
            stmt2.close();
            stmt5.close();
            con.close();
        }
    }
    
    public Funcionario buscarLogin(String email, String senha) throws SQLException, ClassNotFoundException {
        Funcionario f = new Funcionario();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarLogin);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                Departamento d = new Departamento();
                Cargo c = new Cargo();
                f.setIdFuncionario(rs.getInt("f.idFuncionario"));
                f.setNomeFuncionario(rs.getString("f.nomeFuncionario"));
                f.setCpf(rs.getString("f.cpf"));
                f.setRg(rs.getString("f.rg"));
                f.setCelular(rs.getString("f.celular"));
                f.setEmail(rs.getString("f.email"));
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setBairro(rs.getString("e.bairro"));
                e.setCep(rs.getString("e.cep"));
                e.setCidade(rs.getString("e.cidade"));
                e.setUf(rs.getString("u.sigla"));
                d.setIdDepartamento(rs.getInt("d.idDepartamento"));
                d.setNomeDepartamento(rs.getString("d.nomeDepartamento"));
                c.setIdCargo(rs.getInt("c.idCargo"));
                c.setNomeCargo(rs.getString("c.nomeCargo"));
                f.setCargo(c);
                f.setEndereco(e);
                f.setDepartamento(d);
            }
            return f;
        } catch (SQLException ex) {
            out.println("Erro ao Buscar Funcionario: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
        }
        return f;
    }
    
    public List<Funcionario> buscarPorDepartamento(int idDep) throws SQLException, ClassNotFoundException {
        List<Funcionario> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(buscarPorDepartamento);
            stmt.setInt(1, idDep);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                Endereco e = new Endereco();
                Departamento d = new Departamento();
                Cargo c = new Cargo();
                f.setIdFuncionario(rs.getInt("f.idFuncionario"));
                f.setNomeFuncionario(rs.getString("f.nomeFuncionario"));
                f.setCpf(rs.getString("f.cpf"));
                f.setRg(rs.getString("f.rg"));
                f.setCelular(rs.getString("f.celular"));
                f.setEmail(rs.getString("f.email"));
                e.setRua(rs.getString("e.rua"));
                e.setNumero(rs.getInt("e.numero"));
                e.setBairro(rs.getString("e.bairro"));
                e.setCep(rs.getString("e.cep"));
                e.setCidade(rs.getString("e.cidade"));
                e.setUf(rs.getString("u.sigla"));
                d.setIdDepartamento(rs.getInt("d.idDepartamento"));
                d.setNomeDepartamento(rs.getString("d.nomeDepartamento"));
                c.setIdCargo(rs.getInt("c.idCargo"));
                c.setNomeCargo(rs.getString("c.nomeCargo"));
                f.setCargo(c);
                f.setEndereco(e);
                f.setDepartamento(d);
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex) {
            out.println("Erro ao listar Cargos: " + ex.getMessage());
        } finally {
            stmt.close();
            con.close();
            rs.close();
        }
        return lista;
    }
}
