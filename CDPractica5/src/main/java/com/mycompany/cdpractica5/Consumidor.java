package com.mycompany.cdpractica5;

public class Consumidor extends Thread{
    private ListaEstatica<Alumno> listaAlumnos;
    private long tiempoFinalizacion;
    
    public Consumidor(ListaEstatica<Alumno> listaAlumnos){
        this.listaAlumnos = listaAlumnos;
        this.tiempoFinalizacion = 0;
    }
    
    public void setTiempoFinalizacion(long tiempoFinalizacion){
        this.tiempoFinalizacion = tiempoFinalizacion;
    }
    
    public void eliminarAlumno(){
        int eliminacion = (int)Math.floor(Math.random() * 2 + 1);

        if(eliminacion == 1)
            this.listaAlumnos.eliminarInicio();
        else
            this.listaAlumnos.eliminarFinal();
    }
    
    @Override
    public void run(){
        while(System.currentTimeMillis() < this.tiempoFinalizacion){
            eliminarAlumno();
        }
    }
}
