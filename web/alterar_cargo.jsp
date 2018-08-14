<%-- 
    Document   : alterar_cargo
    Created on : May 23, 2017, 8:43:20 PM
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
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Alterar Cargo</title>
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
            <h1>Alterar Cargo</h1>
            <br/>
            <div class="container " style="width: 50%">
                <form action="AlterarCargo" class="form-group jumbotron" method="POST" style="text-align: left">
                    <input type="hidden" name="Id" value="<c:out value="${cargo.idCargo}"/>">
                    <label>Nome:</label> 
                    <input type="text" class="form-control" name="Nome" value="<c:out value="${cargo.nomeCargo}"/>">
                    <br/>
                    <label>Salário:</label> 
                    <input type="text" class="form-control" name="Salario" id="valor" value="<c:out value="${cargo.salario}"/>">
                    <br/>
                    <label>Requisitos:</label> 
                    <input type="text" class="form-control" name="Requisitos" value="<c:out value="${cargo.requisitos}"/>">
                    <br/>
                    <label>Carga Mínima:</label> 
                    <input type="text" class="form-control" name="CargaMinima" id="cm" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="<c:out value="${cargo.cargaMinima}"/>">
                    <br/>
                    <label>Desconto Impostos:</label> 
                    <input type="text" class="form-control" name="DescontoImpostos" id="pc" onkeypress='return event.charCode >= 48 && event.charCode <= 57' onchange="handleChange(this);" value="<c:out value="${cargo.descontoImpostos}"/>">
                    <br/>
                    <input class="btn btn-primary" type="submit" value="Alterar">
                </form>
            </div>
        </center>
        <script src="js/jquery-3.2.1.js"></script>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/jquery.maskMoney.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                var tot = $("#valor").val();
                var tot = 'R$' + parseFloat(tot).toFixed(2).replace(/(\d.)(?=(\d\d\d)+(?!\d))/g, "$1,");
                tot = tot.replace(/[,.]/g, function (m) {
                    return m === ',' ? '.' : ',';
                });
                $("#valor").val(tot);
            });
            $(document).focusin(function(){
                $("#valor").maskMoney({symbol:'R$', showSymbol:true, thousands:'.', decimal:',', symbolStay: true});
            });            
            function handleChange(input) {
                if (input.value < 0) input.value = 0;
                if (input.value > 100) input.value = 100;
            }
        </script>
    </body>
</html>
