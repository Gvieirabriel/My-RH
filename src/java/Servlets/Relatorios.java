/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Departamento;
import Beans.Funcionario;
import DAO.DepartamentoDAO;
import DAO.FuncionarioDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author guilh
 */
@WebServlet(name = "Relatorios", urlPatterns = {"/Relatorios"})
public class Relatorios extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        if (session.getAttribute("funcionario") == null) {
            request.setAttribute("msg", "Acesso negado!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
        if (request.getParameter("rel") == null) {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            List<Funcionario> listaFuncionario = new ArrayList<>();
            listaFuncionario = funcionarioDAO.buscarTodos();
            request.setAttribute("listaFuncionario", listaFuncionario);
            DepartamentoDAO departamentoDAO = new DepartamentoDAO();
            List<Departamento> lista = new ArrayList<>();
            lista = departamentoDAO.buscarNomes();
            request.setAttribute("departamentos", lista);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/relatorios_funcionario.jsp");
            rd.forward(request, response);
        }
        else if (request.getParameter("rel").equals("1")) {
            Connection con = null;
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rh?autoReconnect=true&useSSL=false", "root", "root");
                HashMap params = new HashMap();
                String jrxml = "/home/gqueiroz/NetBeansProjects/RHACTS/web/Horas_Trabalhadas.jrxml";
                params.put("fu.idFuncionario", Integer.valueOf(request.getParameter("funcionario")));
                String jasper = JasperCompileManager.compileReportToFile(jrxml);
                JasperPrint print = JasperFillManager.fillReport(jasper, params, con);
                OutputStream saida = response.getOutputStream();
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
                exporter.exportReport();
            }
            catch(SQLException e) {
                request.setAttribute("msg", "Erro de conexão ou query: " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
            catch(JRException e) {
                request.setAttribute("msg", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
            finally {
                if (con!=null)
                try { con.close(); } catch(Exception e) {}
            }
        }
        else if (request.getParameter("rel").equals("2")) {
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rh?autoReconnect=true&useSSL=false", "root", "root");
                String jasper = request.getContextPath() + "/Holerite_doMes.jrxml";
                String host = "http://" + request.getServerName() + ":" + request.getServerPort();
                URL jasperURL = new URL(host + jasper);
                HashMap params = new HashMap();
                Funcionario funcionario = new Funcionario();
                funcionario = (Funcionario)session.getAttribute("funcionario");
                params.put("fu.idFuncionario", funcionario.getIdFuncionario());
                params.put("f.mes", Integer.valueOf(request.getParameter("mes")));
                params.put("f.ano", Integer.valueOf(request.getParameter("ano")));
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
                if (bytes != null) {
                    response.setContentType("application/pdf");
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            }
            catch(SQLException e) {
                request.setAttribute("msg", "Erro de conexão ou query: " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
            catch(JRException e) {
                request.setAttribute("msg", "Erro no Jasper : " + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
            finally {
                if (con!=null)
                try { con.close(); } catch(Exception e) {}
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
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
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
