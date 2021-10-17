//PRACTICA NRO 2
package com.emergentes.controlador;
import com.emergentes.modelo.Tareas;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtenemos los datos de la sesion
        HttpSession sesion = request.getSession();
        //Creamos el objeto tareaone de tipo Tareas()
        Tareas tareaone = new Tareas();
        //Buscar si existe una lista de datos en el sistema (por 1ra vez luego se obviará este paso) si no existe, entonces lo creará
        if (sesion.getAttribute("listatareas")==null) {
            ArrayList<Tareas> listaauxiliar = new ArrayList<Tareas>();
            sesion.setAttribute("listatareas", listaauxiliar);
        }
        //Extraemos los datos que existen en la sesion, con el nombre de listatareas. y lo guardamos en un objeto lista
        ArrayList<Tareas> lista = (ArrayList<Tareas>)sesion.getAttribute("listatareas");
        //Guardamos el valor que se manda en la variable opcion de Index.jsp, en una variable Opciones y a partir de esto
        //creamos casos para cada resultado
        String opciones=request.getParameter("opcion");
        //Opcion 1: NUEVO; Opcion 2: EDITAR; Opcion 3: ELIMINAR
        String opcion =(opciones != null)? opciones:"view";
        int id,position;
        switch (opcion){
            case "nuevo":
                //NUEVO: Agregamos un atributo, con el objeto tareaone con el nombre:tarea
                request.setAttribute("tarea", tareaone);
                //Luego de enviados los datos, redirigimos a la pagina Nuevo
                request.getRequestDispatcher("nuevo.jsp").forward(request, response);
                break;
            case "editar":
                //EDITAR: Editamos algun registro de la lista. Recibimos el Id y buscamos la Posicion con el metodo
                id=Integer.parseInt(request.getParameter("id"));
                position=BuscarIndice(request,id);
                //Guardamos el objeto en la lista, ubicado en la posicion rescatada
                tareaone=lista.get(position);
                //Agregamos un atributo, con el objeto tareaone con el nombre:tarea
                request.setAttribute("tarea", tareaone);
                //Luego de enviados los datos, redirigimos a la pagina Editar
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                //ELIMINAR: Eliminamos un registro, a traves del Id que se mandó y la posicion obtenida con el metodo
                id=Integer.parseInt(request.getParameter("id"));
                position=BuscarIndice(request,id);
                //Utilizamos una funcion para remover los datos de la lista, a traves del nombre que fueron guardados
                lista.remove(position);
                //Agregamos el valor de la nueva lista de datos en la sesion
                sesion.setAttribute("listatareas", lista);
                //Redirigimos a la pagina principal
                response.sendRedirect("index.jsp");
                break;
            default:
                //Por defecto se ejecuta la pagina principal
                response.sendRedirect("index.jsp");
                break;      
        }
               
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtenemos los datos de la sesion
        HttpSession sesion = request.getSession();
        //Extraemos los datos que existen en la sesion, con el nombre de listatareas. y lo guardamos en un objeto lista
        ArrayList<Tareas> lista = (ArrayList<Tareas>)sesion.getAttribute("listatareas");
        //Creamos el objeto tareaone de tipo Tareas()
        Tareas tareaone = new Tareas();
        //Obtenemos el valor correspondiente a los campos introducidos en el formulario y los agregamos al Modelo
        tareaone.setId(Integer.parseInt(request.getParameter("id")));
        tareaone.setTarea(request.getParameter("tarea"));
        tareaone.setPrioridad(request.getParameter("prioridad"));
        tareaone.setCompletado(request.getParameter("completado"));
        //Creamos un idauxiliar para rescatar el ultimo valor de la lista de registros, a traves de un metodo
        //y añadir al final de la lista, un nuevo conjunto de valores o actualizando estos valores
        int idauxiliar=tareaone.getId();
        if (idauxiliar==0) {
            int ultimoid=BuscarUltimo(request);
            tareaone.setId(ultimoid);
            lista.add(tareaone);
        }else{
            lista.set(BuscarIndice(request,idauxiliar), tareaone);
        }
        //Agregamos el valor de la nueva lista de datos en la sesion
        sesion.setAttribute("listatareas", lista);
        //Redirigimos a la pagina principal
        response.sendRedirect("index.jsp");   
    }
     //CREAMOS UN METODO PARA OBTENER EL ID DE UN VALOR: ESTO PARA LAS OPCIONES DE EDITAR Y ELIMINAR REGISTROS
    private int BuscarIndice(HttpServletRequest request,int id){
        HttpSession ses = request.getSession();
        int indice=0;
        ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("listatareas");
        if (lista.size()>0) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId()==id) {
                    indice=i;
                }
            }
        }
        return indice;
    }
    //CREAMOS UN METODO PARA OBTENER EL ULTIMO ID DE UN VALOR: ESTO PARA EL FORMULARIO NUEVO O DE EDICION
    private int BuscarUltimo(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("listatareas");
        int idaux=0;
        for (Tareas i:lista) {
            idaux=i.getId();
        }
        return idaux+1;
    }

}
