<%-- 
    Document   : index
    Created on : May 21, 2017, 5:25:13 PM
    Author     : guilh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="height: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
        <title>RH-INDO</title>
    </head>
    <body style="height: 100%;">
        <center style="background-image: url(css/office1.jpg); background-size: 100%; background-position: center; height: 100%;">
            <h4>${msg}</h4>
            <div class="container " style="width: 35%">
                </br></br>
                <img src="css/logo.png" style="width: 80%">
                </br>
                <form action="http://localhost:8080/RHINDO/ProcessaLogin" method="post">
                    <div align="center" class="form-group jumbotron">
                        Email:<input class="form-control" type="text" name="email" id="email"><br/>
                        Senha:<input class="form-control" type="password" name="senha" id="senha"><br/>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="Logar">
                    </div>
                </form>
            </div>
        </center>
    </body>
</html>
