<%-- 
    Document   : buscar_funcionarios
    Created on : May 22, 2017, 9:22:04 PM
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
        <title>Buscar Funcionários</title>
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
            <br/><br/><br/><br/>
            <h1>Busca Funcionários</h1>
            <br/>
            <c:choose>
                <c:when test="${!empty lista}">
                <div class="container">
                    <table border="1" cellspacing="1" class="table">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>RG</th>
                            <th>Email</th>
                            <th>Celular</th>
                            <th>Departamento</th>
                            <th>Cargo</th>
                            <th>Endereço</th>
                            <th>Alterar</th>
                            <th>Deletar</th>
                        </tr>
                        <c:forEach items="${lista}" var="item">
                            <tr>
                                <td>${item.idFuncionario}</td>
                                <td>${item.nomeFuncionario}</td>
                                <td id="cpf">${item.cpfFormatado}</td>
                                <td id="rg">${item.rgFormatado}</td>
                                <td>${item.email}</td>
                                <td id="celular">${item.celularFormatado}</td>
                                <td>${item.departamento.nomeDepartamento}</td>
                                <td>${item.cargo.nomeCargo}</td>
                                <td>${item.endereco.rua}, ${item.endereco.numero} - ${item.endereco.bairro}, ${item.endereco.cidade} - ${item.endereco.uf} ${item.endereco.cepFormatado}</td>
                                <td><a href="AlterarFuncionario?fun=<c:out value="${item.idFuncionario}"/>"><span class="glyphicon glyphicon-pencil" name="Alterar" value="Alterar" style="font-size: 1.5vw;"></span></a></td>
                                <td><a href="RemoverFuncionario?fun=<c:out value="${item.idFuncionario}"/>"><span class="glyphicon glyphicon-remove" name="Remover" value="Remover" onmouseover="this.style.color='#ca0202'" onmouseout="this.style.color='red'" style="color: red; font-size: 1.5vw;"></span></a></td>                                
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                </c:when>
                <c:otherwise>
                    <h4>Não foram encontrados funcionarios com este nome!</h4>
                </c:otherwise>
            </c:choose>
        </center>
    </body>
</html>
