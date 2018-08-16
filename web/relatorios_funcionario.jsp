<%-- 
    Document   : relatorios_funcionario
    Created on : May 21, 2017, 7:15:22 PM
    Author     : guilh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:if test="${sessionScope.funcionario == null || (sessionScope.funcionario.departamento.nomeDepartamento == 'RH' && sessionScope.funcionario.cargo.nomeCargo == 'Gerente')}"> 
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
        <title>Relatórios</title>
    </head>
    <body>
        <center>
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                      <div class="navbar-header">
                        <a class="navbar-brand" href="/RHACTS/Relatorios">RH-INDO</a>
                      </div>
                      <ul class="nav navbar-nav">
                        <li><a href="/RHACTS/Relatorios">Relatórios</a></li>
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
            <h1>Relatórios</h1>
            <br/>
            <div class="container " style="width: 28%">
                <div align="center" class="form-group jumbotron">
                    <a href="Relatorios?rel=1"><input class="btn btn-primary" type="submit" value="Horas Trabalhadas"></a>
                </div>
                <div align="center" class="form-group jumbotron">
                    <form action="Relatorios">
                        <input type="hidden" name="rel" value="2">
                        <label>Mês: </label>
                        <select name="mes">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <label>Ano: </label>
                        <select name="ano">
                            <option value="2006">2006</option>
                            <option value="2007">2007</option>
                            <option value="2008">2008</option>
                            <option value="2009">2009</option>
                            <option value="2010">2010</option>
                            <option value="2011">2011</option>
                            <option value="2012">2012</option>
                            <option value="2013">2013</option>
                            <option value="2014">2014</option>
                            <option value="2015">2015</option>
                            <option value="2016">2016</option>
                            <option value="2017" selected="true">2017</option>
                        </select>
                        <br/><br/>
                        <input class="btn btn-primary" type="submit" value="Folha de pagamento">
                    </form>
                </div>
                <br/>
            </div>
        </center>
    </body>
</html>
