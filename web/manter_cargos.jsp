<%-- 
    Document   : manter_cargos
    Created on : May 22, 2017, 5:27:26 PM
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
        <title>Manter Cargos</title>
    </head>
    <body>
        <center>
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                      <div class="navbar-header">
                        <a class="navbar-brand" href="/RHACTS/manter_funcionarios.jsp"><img src="/RHACTS/css/logo0.png"style="width: 76%; margin: -1vh"></a>
                      </div>
                      <ul class="nav navbar-nav">
                        <li><a href="/RHACTS/manter_funcionarios.jsp">Funcionários</a></li>
                        <li><a href="/RHACTS/manter_departamentos.jsp">Departamentos</a></li>
                        <li><a href="/RHACTS/manter_cargos.jsp">Cargos</a></li>
                        <li><a href="/RHACTS/manter_folhas.jsp">Folhas</a></li>
                        <li><a href="/RHACTS/RelatoriosGerente">Relatórios</a></li>
                      </ul>
                      <ul class="nav navbar-nav navbar-right">
                        <li>
                            <div style="margin-top: 2vh; color: #ccc; ">
                                Bem vindo, <c:out value="${sessionScope.funcionario.nomeFuncionario}"/><span style="float:right;"></span>
                            </div>
                        </li>
                        <li><a href="/RHACTS/ProcessaLogout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                      </ul>
                    </div>
                  </nav>
                  <br/><br/><br/>
            <h1>Manter Cargos</h1>
            <br/>
            <div class="container " style="width: 35%;">
                <div align="center" class="form-group jumbotron">
                    <div align="left"><label>Buscar:</label></div>
                    <form action="BuscarCargo" method="POST" style="display: flex;" role="form" style="text-align: left">
                        <input type="text" name="buscaCargo" class="form-control" placeholder="Buscar por nome" style="margin-right: 10px">
                        <input type="submit" class="btn btn-primary" value="Buscar">
                    </form>
                    <br/><br/>
                    <a href="cadastrar_cargo.jsp"><input class="btn btn-primary" type="submit" value="Cadastrar"></a>
                </div>
            </div>
        </center>
    </body>
</html>
