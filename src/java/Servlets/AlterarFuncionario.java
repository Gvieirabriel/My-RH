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
@WebServlet(name = "AlterarFuncionario", urlPatterns = {"/AlterarFuncionario"})
public class AlterarFuncionario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
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
        if (request.getParameter("fun") != null) {
            List<Departamento> listaDepartamentos = new ArrayList<>();
            List<Cargo> listaCargos = new ArrayList<>();
            List<Endereco> listaEnderecos = new ArrayList<>();
            listaDepartamentos = departamentoDAO.buscarNomes();
            listaCargos = cargoDAO.buscarNomes();
            int id = Integer.valueOf(request.getParameter("fun"));            
            funcionario = funcionarioDAO.buscarPorId(id);
            funcionario.setCpfFormatado(funcionario.getCpf());
            funcionario.setRgFormatado(funcionario.getRg());
            funcionario.setCelularFormatado(funcionario.getCelular());
            funcionario.getEndereco().setCepFormatado(funcionario.getEndereco().getCep());
            request.setAttribute("listaDepartamento", listaDepartamentos);
            request.setAttribute("listaCargo", listaCargos);
            request.setAttribute("listaEndereco", listaEnderecos);
            request.setAttribute("funcionario", funcionario);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/alterar_funcionario.jsp");
            rd.forward(request, response);
        }
        else {
            try{
                endereco.setRua(request.getParameter("Rua"));
                endereco.setNumero(Integer.valueOf(request.getParameter("Numero")));
                endereco.setBairro(request.getParameter("Bairro"));
                String cep = request.getParameter("Cep").replaceAll("[^\\d.]+", "");
                cep = cep.replaceAll("[.]","");
                endereco.setCep(cep);
                endereco.setCidade(request.getParameter("Cidade"));
                endereco.setIdUf(Integer.valueOf(request.getParameter("Estado")));
                departamento.setIdDepartamento(Integer.valueOf(request.getParameter("Departamento")));
                cargo.setIdCargo(Integer.valueOf(request.getParameter("Cargo")));
                funcionario.setIdFuncionario(Integer.valueOf(request.getParameter("Id")));
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
                if (funcionario.validaFuncionarioAlterar(funcionario) && endereco.validaEndereco(endereco)) {
                    funcionarioDAO.alterarFuncionario(funcionario);
                    request.setAttribute("msg", "Funcionário " + funcionario.getNomeFuncionario() + " alterado com sucesso!");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/manter_funcionarios.jsp");
                    rd.forward(request, response);
                }
            }
            catch (Exception e) {
                request.setAttribute("msg", "Valores Invalidos!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            finally {
                request.setAttribute("msg", "Valores Inválidos!");
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
            Logger.getLogger(AlterarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlterarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlterarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlterarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
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
