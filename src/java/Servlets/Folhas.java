/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Atividade;
import Beans.Departamento;
import Beans.Folha;
import Beans.DiaHoras;
import Beans.Funcionario;
import DAO.DepartamentoDAO;
import DAO.FolhaDAO;
import DAO.FuncionarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author guilh
 */
@WebServlet(name = "Folhas", urlPatterns = {"/Folhas"})
public class Folhas extends HttpServlet {

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
        int mes = Integer.valueOf(request.getParameter("mes"));
        int ano = Integer.valueOf(request.getParameter("ano"));
        FolhaDAO folhaDAO = new FolhaDAO();
        boolean check = false;
        boolean check2 = false;
        if (folhaDAO.verifica(mes, ano)) {
            List<DiaHoras> listaHoras = new ArrayList<>();
            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
                for (int i = 1; i <= 31; i++){
                    DiaHoras d = new DiaHoras();
                    d.setDia(i);
                    d.setHoras(0);
                    listaHoras.add(d);
                }
            else if (mes == 2)
                for (int i = 1; i <= 28; i++){
                    DiaHoras d = new DiaHoras();
                    d.setDia(i);
                    d.setHoras(0);
                    listaHoras.add(d);
                }
            else
                for (int i = 1; i <= 30; i++){
                    DiaHoras d = new DiaHoras();
                    d.setDia(i);
                    d.setHoras(0);
                    listaHoras.add(d);
                }         
            List<Atividade> lista = new ArrayList<>();
            Client client = ClientBuilder.newClient();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            DepartamentoDAO departamentoDAO = new DepartamentoDAO();
            Response resp = client.target("http://localhost:21836/ATOA/webresources/atividades").request(MediaType.APPLICATION_JSON).get();
            lista = resp.readEntity(new GenericType<List<Atividade>>() {});        
            for (Atividade a:lista) {
                a.setFuncionario(funcionarioDAO.buscarPorId(a.getFuncionario().getIdFuncionario()));
                a.setDepartamento(departamentoDAO.buscarPorId(a.getDepartamento().getIdDepartamento()));
            }
            List<Folha> listaFolha = new ArrayList<>();
            List<Funcionario> listaFuncionario = new ArrayList<>();
            List<String> listaEmAndamento = new ArrayList<>();
            listaFuncionario = funcionarioDAO.buscarTodos();
            int inif = 0;
            int fimf = 0;
            float salarioLiquido = 0;
            for (Funcionario f:listaFuncionario) {
                Folha folha = new Folha();
                folha.setFuncionario(f);
                listaFolha.add(folha);
            }
            for (Funcionario f:listaFuncionario) { //Para cada funcionario
                if (f.getCargo().getIdCargo() != 1) { // Se nao for gerente
                    int horas = 0;                
                    for (Folha fo:listaFolha) { //Precorre as folhas
                        if (f.getIdFuncionario() == fo.getFuncionario().getIdFuncionario()) { //Se a folha for do funcionario
                            for (Atividade a:lista) { //Percorre todas as listas
                                if (a.getFuncionario().getIdFuncionario() == f.getIdFuncionario()) { // Se a atividade eh do funcionario                                
                                    if (a.getFim() != null) { // Se a atividade foi encerrada
                                        if (a.getIdEstado() == 3) { // Se a atividade foi aprovada
                                            Calendar cal2 = Calendar.getInstance();
                                            Calendar cal3 = Calendar.getInstance();
                                            cal2.setTime(a.getInicio());
                                            cal3.setTime(a.getFim());
                                            int mesi = cal2.get(Calendar.MONTH) + 1;
                                            int mesf = cal3.get(Calendar.MONTH) + 1;
                                            if (cal2.get(Calendar.YEAR) == ano || cal3.get(Calendar.YEAR) == ano){//Se a atividade pertence ao ano
                                                if (mesi == mes || mesf == mes) { //Se o mes do inicio ou fim da atividade eh o escolhido  
                                                    if (mesf == mes && mesi == mes) {// Se a atividade inicia e termina no mes
                                                        Calendar cal = Calendar.getInstance();
                                                        cal.setTime(a.getInicio());
                                                        inif = cal.get(Calendar.DAY_OF_MONTH);//seta inicio
                                                        cal.setTime(a.getFim());                                    
                                                        fimf = cal.get(Calendar.DAY_OF_MONTH);//seta fim
                                                        for (DiaHoras dh:listaHoras) { // Percorre os dias do mes com horas trabalhadas
                                                            if (dh.getDia() >= inif && dh.getDia() <= fimf) { //Se o dia for dentro do periodo de execucao da atividade
                                                                dh.setHoras(8);//Seta como 8 horas trabalhadas
                                                            }
                                                        }
                                                    }
                                                    else if(mesf != mes) { //Se a atividade termina no mes seguinte
                                                        for (DiaHoras dh:listaHoras) { // Percorre os dias do mes com horas trabalhadas
                                                            if (dh.getDia() >= cal2.get(Calendar.DAY_OF_MONTH)) { //Se o dia for dentro do periodo de execucao da atividade
                                                                dh.setHoras(8);//Seta como 8 horas trabalhadas
                                                            }
                                                        }
                                                    }
                                                    else if (cal2.get(Calendar.MONTH) != mes) { // Se a atividade comeca no mes anterior
                                                        for (DiaHoras dh:listaHoras) { // Percorre os dias do mes com horas trabalhadas
                                                            if (dh.getDia() <= cal3.get(Calendar.DAY_OF_MONTH)) { //Se o dia for dentro do periodo de execucao da atividade
                                                                dh.setHoras(8);//Seta como 8 horas trabalhadas
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }                                    
                                    }
                                    else {// Se a atividade nao foi encerrada
                                        check = true;
                                        client.target("http://localhost:21836/ATOA/webresources/aviso").request(MediaType.APPLICATION_JSON).post(Entity.json(f));
                                    }
                                }
                            }
                            for (DiaHoras dh:listaHoras) {
                                horas += dh.getHoras();// Soma as horas
                            }
                            fo.setMes(mes);
                            fo.setAno(ano);
                            fo.setHorasTrabalhadas(horas);
                            fo.setSalarioBruto(f.getCargo().getSalario() * ((float) horas / f.getCargo().getCargaMinima()));
                            salarioLiquido = (fo.getSalarioBruto() - (fo.getSalarioBruto() * f.getCargo().getDescontoImpostos() / 100));
                            if (salarioLiquido > f.getCargo().getSalario())
                                salarioLiquido = f.getCargo().getSalario();
                            fo.setSalarioLiquido(salarioLiquido);
                            if (!check) {
                                folhaDAO.inserirFolha(fo);
                            }
                        }
                        for (DiaHoras dh:listaHoras) {
                            dh.setHoras(0); // Zera as horas
                        }
                        horas = 0;
                    }
                }
                else {// Se for gerente
                    for (Folha fo:listaFolha) {
                        if (f.getIdFuncionario() == fo.getFuncionario().getIdFuncionario()) {
                            fo.setMes(mes);
                            fo.setAno(ano);
                            fo.setHorasTrabalhadas(f.getCargo().getCargaMinima());
                            fo.setSalarioBruto(f.getCargo().getSalario());
                            salarioLiquido = f.getCargo().getSalario() - (f.getCargo().getSalario() * f.getCargo().getDescontoImpostos() / 100);
                            fo.setSalarioLiquido(salarioLiquido);
                            folhaDAO.inserirFolha(fo);
                        }
                    }
                }
            }
        }
        else {
            check2 = true;
        }
        if (check)
            request.setAttribute("msg", "Folhas fechada com sucesso! Alguns funcionários possuem atividade em andamento!");
        else if (check2){
            request.setAttribute("msg", "Folha desse mês ja foi fechada!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/manter_folhas.jsp");
            rd.forward(request, response);
        }
        else
            request.setAttribute("msg", "Folhas fechada com sucesso!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/manter_funcionarios.jsp");
        rd.forward(request, response);
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
            Logger.getLogger(Folhas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Folhas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Folhas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Folhas.class.getName()).log(Level.SEVERE, null, ex);
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
