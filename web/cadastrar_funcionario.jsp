<%-- 
    Document   : cadastrar_funcionario
    Created on : May 26, 2017, 7:34:57 PM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Cadastrar Funcionário</title>
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
            <h1>Cadastrar Funcionário</h1>
            <br/>
            <div class="container " style="width: 50%">
                <form action="CadastrarFuncionario" class="form-group jumbotron" onsubmit="return checarSenha()" method="POST" role="form" style="text-align: left">
                    <input type="hidden" name="ver" value="1">
                    <label>Nome:</label> <input type="text" class="form-control" name="Nome" placeholder="Nome">
                    <br/>
                    <label>CPF:</label> <input type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="Cpf" id="cpf" placeholder="999.999.999-99">
                    <br/>
                    <label>RG:</label> <input type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="Rg" id="rg" placeholder="99.999.999-9">
                    <br/>
                    <label>Celular:</label> <input type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="Celular" id="cel" placeholder="(99)99999-9999">
                    <br/>
                    <label>Email:</label> <input type="email" class="form-control" name="Email" placeholder="email@example.com">
                    <br/>
                    <label>Rua:</label> <input type="text" class="form-control" name="Rua" placeholder="Av. das Torres">
                    <br/>
                    <label>Número:</label> <input type="text" class="form-control" name="Numero" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="999">
                    <br/>
                    <label>Bairro:</label> <input type="text" class="form-control" name="Bairro" placeholder="Jardim das Americas">
                    <br/>
                    <label>CEP:</label> <input type="text" class="form-control" name="Cep" onkeypress='return event.charCode >= 48 && event.charCode <= 57' id="cep" placeholder="99999-999">
                    <br/>
                    <label>Cidade:</label> <input type="text" class="form-control" name="Cidade" placeholder="Cidade">
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
                    <label>Cargo:</label> 
                    <select class="form-control" name="Cargo">
                        <c:forEach items="${listaCargo}" var="cargo">
                            <option value="${cargo.idCargo}">${cargo.nomeCargo}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <label>Senha:</label> <input type="password" class="form-control" name="Senha" id="senha" placeholder="*********">
                    <br/>
                    <label>Confirmar Senha:</label> <input type="password" class="form-control" name="Confsenha" id="senhaconf" placeholder="*********">
                    <br/>
                    <input type="reset" class="btn btn-primary" value="Limpar">
                    <input type="submit" class="btn btn-primary" value="Cadastrar">
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
            function checarSenha() {
                var senha = document.getElementById("senha").value;
                var senhaconf = document.getElementById("senhaconf").value;
                var check = true;
                if (senha != senhaconf) {
                    alert("Senhas não conferem!");
                    check = false;                    
                }
                return check;
            }
        </script>
    </body>
</html>
