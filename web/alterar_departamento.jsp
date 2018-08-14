<%-- 
    Document   : alterar_departamento
    Created on : May 23, 2017, 7:15:55 PM
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
       <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Alterar Departamento</title>
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
            <h1>Alterar Departamento</h1>
            <br/>
            <div class="container " style="width: 50%">
                <form action="AlterarDepartamento" class="form-group jumbotron" method="POST" style="text-align: left">
                    <input type="hidden" name="Id" value="<c:out value="${departamento.idDepartamento}"/>">
                    <label>Nome:</label> 
                    <input type="text" class="form-control" name="Nome" value="<c:out value="${departamento.nomeDepartamento}"/>">
                    <br/>
                    <label>Localização:</label> 
                    <input type="text" class="form-control" name="Localizacao" value="<c:out value="${departamento.localizacao}"/>">
                    <br/>
                    <input class="btn btn-primary" type="submit" value="Alterar">
                </form>
            </div>
        </center>
    </body>
</html>
