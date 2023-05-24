<%@page import="com.parcial.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Producto prod= (Producto) request.getAttribute("proc");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Producto</h1>
        <form action="Maincontroller" method="post">
             <input type="hidden" name="id" value="${prod.id}">
            <table>
                <tr>
                    <td>DESCRIPCION</td>
                    <td><input type ="text" name="descripcion" value="${prod.descripcion}"></td>
                </tr>
                <tr>
                    <td>CANTIDAD</td>
                    <td><input type ="number" name="cantidad" value="${prod.cantidad}"></td>
                </tr>
                <tr>
                    <td>PRECIO</td>
                    <td><input type ="float" name="precio" value="${prod.precio}"></td>
                </tr>
                <tr>
                    <td>CATEGORIA</td>
                    <td><input type ="text" name="categoria" value="${prod.categoria}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type ="submit"></td>
                </tr>
            </table>
            
        </form>
    </body>
</html>
