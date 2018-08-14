<%-- 
    Document   : alerar_funcionario
    Created on : May 30, 2017, 3:47:30 PM
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
        <script src="js/jquery-3.2.1.js"></script>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/jquery.maskMoney.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Alterar Funcionário</title>
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
            <h1>Alterar Funcionário</h1>
            <br/>
            <div class="container " style="width: 50%">
                <form action="AlterarFuncionario" method="POST" class="form-group jumbotron" role="form" style="text-align: left"> 
                    <input type="hidden" name="Id" value="<c:out value="${funcionario.idFuncionario}"/>">
                    <label> Nome:</label> 
                    <input type="text" class="form-control" name="Nome" value="${funcionario.nomeFuncionario}">
                    <br/>
                    <label>CPF:</label> 
                    <input type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="Cpf" id="cpf" value="${funcionario.cpfFormatado}">
                    <br/>
                    <label>RG:</label> 
                    <input type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="Rg" id="rg" value="${funcionario.rgFormatado}">
                    <br/>
                    <label>Celular:</label> 
                    <input type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="Celular" id="cel" value="${funcionario.celularFormatado}">
                    <br/>
                    <label>Email:</label> 
                    <input type="email" class="form-control" name="Email" value="${funcionario.email}">
                    <br/>
                    <label>Rua:</label> 
                    <input type="text" class="form-control" name="Rua" value="${funcionario.endereco.rua}">
                    <br/>
                    <label>Número:</label> 
                    <input type="text" class="form-control" name="Numero" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="${funcionario.endereco.numero}">
                    <br/>
                    <label>Bairro:</label> 
                    <input type="text" class="form-control" name="Bairro" value="${funcionario.endereco.bairro}">
                    <br/>
                    <label>CEP:</label> 
                    <input type="text" class="form-control" name="Cep" onkeypress='return event.charCode >= 48 && event.charCode <= 57' id="cep" value="${funcionario.endereco.cepFormatado}">
                    <br/>
                    <label>Cidade:</label> 
                    <input type="text" class="form-control" name="Cidade" value="${funcionario.endereco.cidade}">
                    <br/>
                    <label>Estado:</label> 
                    <select class="form-control" name="Estado">
                        <c:forEach items="${listaEndereco}" var="uf">
                            <option value="${uf.idUf}">${uf.uf}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <label>Departamento:</label> 
                    <select class="form-control" name="Departamento">
                        <c:forEach items="${listaDepartamento}" var="departamento">
                            <option value="${departamento.idDepartamento}">${departamento.nomeDepartamento}</option>
                        </c:forEach>
                    </select>
                    <br/>
                   <label> Cargo:</label> 
                    <select class="form-control" name="Cargo">
                        <c:forEach items="${listaCargo}" var="cargo">
                            <option value="${cargo.idCargo}">${cargo.nomeCargo}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <input class="btn btn-primary" type="submit" value="Alterar">
                </form>
            </div>
        </center>
        <script type="text/javascript">
            $(document).ready(function(){
              $('#cep').mask('00000-000');
              $('#cel').mask('(00)00000-0000');
              $('#cpf').mask('000.000.000-00', {reverse: true});
              $('#rg').mask('00.000.000-0', {reverse: true});
            });
        </script>
    </body>
</html>
