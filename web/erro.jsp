<%-- 
    Document   : erro
    Created on : May 22, 2017, 5:06:54 PM
    Author     : guilh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
        <title>Erro</title>
    </head>
    <body>
        <center style="margin-top: 20vh;">
            <img src="css/logo.png"> 
            <div class="container " style="width: 35%">
                <div align="center" class="form-group jumbotron">
                    <c:choose>
                        <c:when test="${!empty param.msg}">
                            <h3>${param.msg}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>${requestScope.msg}</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </center>
    </body>
</html>