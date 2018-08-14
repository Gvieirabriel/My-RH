/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Beans.Departamento;
import Beans.Endereco;
import Beans.Funcionario;
import DAO.DepartamentoDAO;
import DAO.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author guilh
 */
@Path("funcionarios")
public class FuncionariosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionariosResource
     */
    public FuncionariosResource() {
    }

    /**
     * Retrieves representation of an instance of WebServices.FuncionariosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/login/{email}/{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario buscaLogin(@PathParam("email") String email, @PathParam("senha") String senha) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        funcionario = funcionarioDAO.buscarLogin(email, senha);
        return funcionario;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscaTodos() throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = funcionarioDAO.buscarTodos();
        GenericEntity<List<Funcionario>> lista = new GenericEntity<List<Funcionario>>(funcionarios) {};
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @GET
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterPorNome(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        Endereco endereco = new Endereco();
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = funcionarioDAO.buscarPorDepartamento(id);
        GenericEntity<List<Funcionario>> lista = new GenericEntity<List<Funcionario>>(funcionarios) {};
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @GET
    @Path("/departamento/nome/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Departamento obterPorId(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        Departamento departamento = new Departamento();
        departamento = departamentoDAO.buscarPorId(id);
        return departamento;
    }
    
    @GET
    @Path("/nome/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario obterNomePorId(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        funcionario = funcionarioDAO.buscarPorId(id);
        return funcionario;
    }

    /**
     * PUT method for updating or creating an instance of FuncionariosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
