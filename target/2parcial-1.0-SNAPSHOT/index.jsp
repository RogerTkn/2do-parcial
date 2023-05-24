<%@page import="java.util.List"%>
<%@page import="com.parcial.modelo.Producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Producto> lista =(List<Producto>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <table border=1>
            <tr><th><h1>SEGUNDO PARCIAL</h1>
        <h2>Nombres: Rogerd silvion tarquino paco</h2>
        <h2>Carnet 12450080 Lp</h2></th></tr>
        </table>
        <h1>GESTION DE PRODUCCION</h1>
         <p><a href="MainController?opcion=nuevo">Nuevo</a></p>
        <table border ="1">
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>CANTIDAD</th>
                <th>PRECIO</th>
                <th>CATEGORIA</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.id}</td>
                <td>${item.descripcion}</td>
                <td>${item.cantidad}</td>
                <td>${item.precio}</td>
                <td>${item.categoria}</td>
                <td>
                    <a href="MainController?opcion=eliminar&id=${item.id}" onclick="return(confirm('estas seguro?'))">Eliminar</a>
                </td>
                <td>
                    <a href="MainController?opcion=eliminar&id=${item.id}">Editar</a>
                </td>
            </tr>
            </c:forEach>
            
    </body>
</html>
