//PRACTICA NRO 2
package com.emergentes.modelo;
public class Tareas {
    private int id;
    private String tarea;
    private String prioridad;
    private String completado;
    public Tareas() {
        this.id=0;
        this.tarea="";
        this.prioridad="";
        this.completado="";
    }
    public int getId() {
        return id;
    }
    public String getTarea() {
        return tarea;
    }
    public String getPrioridad() {
        return prioridad;
    }
    public String getCompletado() {
        return completado;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTarea(String tarea) {
        this.tarea = tarea;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public void setCompletado(String completado) {
        this.completado = completado;
    }      
}
