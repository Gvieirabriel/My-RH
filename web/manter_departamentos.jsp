<%-- 
    Document   : manter_departamentos
    Created on : May 22, 2017, 5:27:17 PM
    Author     : guilh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:if test="${sessionScope.funcionario.departamento.nomeDepartamento != 'RH' || sessionScope.funcionario.cargo.nomeCargo != 'Gerente'}"> <!--Trcar para RH e Gerente-->
    <c:redirect url="/erro.jsp">
        <c:param name="msg" value="Acesso negado!"/>
    </c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <c:if test="${!empty requestScope.msg}">
            <script>
                alert("${requestScope.msg}");
            </script>
        </c:if>
        <title>Manter Departamentos</title>
    </head>
    <body>
        <center>
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                      <div class="navbar-header">
                        <a class="navbar-brand" href="/RHINDO/manter_funcionarios.jsp"><img src="/RHINDO/css/logo0.png"style="width: 76%; margin: -1vh"></a>
                      </div>
                      <ul class="nav navbar-nav">
                        <li><a href="/RHINDO/manter_funcionarios.jsp">Funcionários</a></li>
                        <li><a href="/RHINDO/manter_departamentos.jsp">Departamentos</a></li>
                        <li><a href="/RHINDO/manter_cargos.jsp">Cargos</a></li>
                        <li><a href="/RHINDO/manter_folhas.jsp">Folhas</a></li>
                        <li><a href="/RHINDO/RelatoriosGerente">Relatórios</a></li>
                      </ul>
                      <ul class="nav navbar-nav navbar-right">
                        <li>
                            <div style="margin-top: 2vh; color: #ccc; ">
                                Bem vindo, <c:out value="${sessionScope.funcionario.nomeFuncionario}"/><span style="float:right;"></span>
                            </div>
                        </li>
                        <li><a href="/RHINDO/ProcessaLogout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                      </ul>
                    </div>
                  </nav>
                  <br/><br/><br/>
            <h1>Manter Departamento</h1>
            <br/>
            <div class="container " style="width: 35%">
                <div align="center" class="form-group jumbotron">
                    <div align="left"><label>Buscar:</label></div>
                    <form action="BuscarDepartamento" method="POST" style="display: flex">
                            <input type="text" name="buscaDepartamento" class="form-control" placeholder="Buscar por nome" style="margin-right: 10px">
                            <input type="submit" class="btn btn-primary" value="Buscar">
                    </form>
                    <br/><br/>
                    <a href="cadastrar_departamento.jsp"><input class="btn btn-primary" type="submit" value="Cadastrar"></a>
                </div>
            </div>
        </center>
    </body>
</html>
