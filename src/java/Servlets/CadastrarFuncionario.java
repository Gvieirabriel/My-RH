/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Cargo;
import Beans.Departamento;
import Beans.Endereco;
import Beans.Funcionario;
import DAO.CargoDAO;
import DAO.DepartamentoDAO;
import DAO.EnderecoDAO;
import DAO.FuncionarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guilh
 */
@WebServlet(name = "CadastrarFuncionario", urlPatterns = {"/CadastrarFuncionario"})
public class CadastrarFuncionario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("funcionario") == null) {
            request.setAttribute("msg", "Acesso negado!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
        Funcionario funcionario = new Funcionario();
        Departamento departamento = new Departamento();
        Endereco endereco = new Endereco();
        Cargo cargo = new Cargo();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        CargoDAO cargoDAO = new CargoDAO();
        if (request.getParameter("ver") == null) {
            List<Departamento> listaDepartamentos = new ArrayList<>();
            List<Cargo> listaCargos = new ArrayList<>();
            List<Endereco> listaEnderecos = new ArrayList<>();
            listaDepartamentos = departamentoDAO.buscarNomes();
            listaCargos = cargoDAO.buscarNomes();
            request.setAttribute("listaDepartamento", listaDepartamentos);
            request.setAttribute("listaCargo", listaCargos);
            request.setAttribute("listaEndereco", listaEnderecos);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrar_funcionario.jsp");
            rd.forward(request, response);
        }
        else {
            try {
                endereco.setRua(request.getParameter("Rua"));
                endereco.setNumero(Integer.valueOf(request.getParameter("Numero")));
                endereco.setBairro(request.getParameter("Bairro"));
                String cep = request.getParameter("Cep").replaceAll("[^\\d.]+", "");
                cep = cep.replaceAll("[.]","");
                endereco.setCep(cep);
                endereco.setCidade(request.getParameter("Cidade"));
                endereco.setIdUf(Integer.valueOf(request.getParameter("Estado")));
                enderecoDAO.cadastrarEndereco(endereco);
                int idEndereco = enderecoDAO.buscarIdEndereco();
                endereco.setIdEndereco(idEndereco);
                departamento.setIdDepartamento(Integer.valueOf(request.getParameter("Departamento")));
                cargo.setIdCargo(Integer.valueOf(request.getParameter("Cargo")));
                funcionario.setNomeFuncionario(request.getParameter("Nome"));
                String cpf = request.getParameter("Cpf").replaceAll("[^\\d.]+", "");
                cpf = cpf.replaceAll("[.]","");
                funcionario.setCpf(cpf);
                String rg = request.getParameter("Rg").replaceAll("[^\\d.]+", "");
                rg = rg.replaceAll("[.]","");
                funcionario.setRg(rg);
                String celular = request.getParameter("Celular").replaceAll("[^\\d.]+", "");
                celular = celular.replaceAll("[.]","");
                funcionario.setCelular(celular);
                funcionario.setEmail(request.getParameter("Email"));
                funcionario.setEndereco(endereco);
                funcionario.setDepartamento(departamento);
                funcionario.setCargo(cargo);
                if (request.getParameter("Senha") == null || request.getParameter("Senha").equals("")) {
                    request.setAttribute("msg", "Senha nao pode ser vazia!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = algorithm.digest(request.getParameter("Senha").getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }
                String senha = hexString.toString();
                funcionario.setSenha(senha);
                if (funcionario.validaFuncionario(funcionario) && endereco.validaEndereco(endereco)) {
                    funcionarioDAO.cadastrarFuncionario(funcionario);
                    request.setAttribute("msg", "Funcionário " + funcionario.getNomeFuncionario() + " cadastrado com sucesso!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/manter_funcionarios.jsp");
                    rd.forward(request, response);
                }
            }
            catch (Exception e) {
                request.setAttribute("msg", "Valores inválidos!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);                
            }
            finally {
                request.setAttribute("msg", "Valores inválidos!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
