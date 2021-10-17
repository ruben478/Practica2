<!--PRACTICA NRO 2-->
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Tareas"%>
<%ArrayList<Tareas> lista = (ArrayList<Tareas>)session.getAttribute("listatareas"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">LISTA DE TAREAS</h1>
        <a href="MainController?opcion=nuevo">NUEVO</a>
        <table align="center" border="2" cellspacing="0" cellpadding="0">
            <tr>
                <th>ID</th>
                <th>TAREA</th>
                <th>PRIORIDAD</th>
                <th>COMPLETADO</th>
                <th></th>
                <th></th>
            </tr>
            <%if (lista!=null) {
                  for (Tareas i:lista) {                        
            %>
            <tr>
                <td><%=i.getId()%></td>
                <td><%=i.getTarea()%></td>
                <td><%=i.getPrioridad()%></td>
                <td><%=i.getCompletado()%></td>
                <td><a href="MainController?opcion=editar&id=<%=i.getId()%>">Editar</a></td>
                <td><a href="MainController?opcion=eliminar&id=<%=i.getId()%>" onclick="return confirm('Está seguro de Eliminar el Registro?');">Eliminar</a></td>
            </tr>
            <%    }
              }
            %> 
        </table>
    </body>
</html>
