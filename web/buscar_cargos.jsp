<%-- 
    Document   : buscar_cargos
    Created on : May 22, 2017, 9:24:34 PM
    Author     : guilh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Buscar Cargos</title>
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
            <h1>Busca Cargos</h1>
            <br/>
            <c:choose>
                <c:when test="${!empty lista}">
                <div class="container">
                    <table border="1" cellspacing="1" class="table">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Salário</th>
                            <th>Requisitos</th>
                            <th>Carga Mínima</th>
                            <th>Desconto Impostos</th>
                            <th>Alterar</th>
                            <th>Deletar</th>                            
                        </tr>
                        <c:forEach items="${lista}" var="item">
                            <tr>
                                <td>${item.idCargo}</td>
                                <td>${item.nomeCargo}</td>
                                <td><fmt:setLocale value = "pt_BR"/><fmt:formatNumber value="${item.salario}" type="currency"/></td>
                                <td>${item.requisitos}</td>
                                <td>${item.cargaMinima}h</td>
                                <td>${item.descontoImpostos}%</td>
                                <td><a href="AlterarCargo?car=<c:out value="${item.idCargo}"/>"><span class="glyphicon glyphicon-pencil" name="Alterar" value="Alterar" style="font-size: 1.5vw;"></span></a></td>
                                <td><a href="RemoverCargo?car=<c:out value="${item.idCargo}"/>"><span class="glyphicon glyphicon-remove" name="Remover" value="Remover" onmouseover="this.style.color='#ca0202'" onmouseout="this.style.color='red'" style="color: red; font-size: 1.5vw;"></span></a></td>                                
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                </c:when>
                <c:otherwise>
                    <h4>Não foram encontrados cargos com este nome!</h4>
                </c:otherwise>
            </c:choose>
        </center>
    </body>
</html>
