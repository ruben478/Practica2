<!--PRACTICA NRO 2-->
<%@page import="com.emergentes.modelo.Tareas"%>
<%Tareas item = (Tareas)request.getAttribute("tarea");
String prio,compl; %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">EDITAR REGISTRO DE TAREA</h1>
        <form action="MainController" method="post">
            <table align="center">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <tr>
                    <td>TAREA: </td>
                    <td><input type="text" name="tarea" value="<%=item.getTarea()%>" required></td>
                </tr>
                <tr>
                    <td>PRIORIDAD: </td>
                    <td><select name="prioridad">
                            <%prio=item.getPrioridad(); 
                            switch (prio){
                                case "Media":
                                   %>
                                    <option value="Alta">Alta</option>
                                    <option value="Media" selected>Media</option>
                                    <option value="Baja">Baja</option>
                                   <%
                                    break;
                                case "Baja":
                                   %>
                                   <option value="Alta">Alta</option>
                                   <option value="Media">Media</option>
                                   <option value="Baja" selected>Baja</option>
                                   <%
                                   break;
                                case "Alta":
                                   %>
                                    <option value="Alta" selected>Alta </option>
                                    <option value="Media">Media</option>
                                    <option value="Baja">Baja</option>
                            <%
                                break;
     
                                }    
                            %>  
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>COMPLETADO: </td>
                    <%compl=item.getCompletado();
                    switch (compl){
                        case "X":
                            %>
                            <td><input type="radio" name=completado value="X" required checked>Concluido</td>
                            <td><input type="radio" name=completado value="" required>Pendiente</td>
                            <%
                            break;
                        case "":
                            %><td><input type="radio" name=completado value="X" required>Concluido</td>
                              <td><input type="radio" name=completado value=" " required checked>Pendiente</td>
                            
                    <%
                        break;
                    }
                    %>
                    
                </tr>
                <tr>
                    <td><a href="index.jsp">VOLVER</a></td>
                    <td><input type="submit" value="ENVIAR"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
