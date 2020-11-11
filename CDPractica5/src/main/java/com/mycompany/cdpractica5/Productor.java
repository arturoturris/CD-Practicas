package com.mycompany.cdpractica5;

import java.io.IOException;

public class Productor extends Thread{
    private ListaEstatica<Alumno> listaAlumnos;
    private long tiempoFinalizacion;
    private ArchivoAlumnos archivoAlumnos;
    
    public Productor(ListaEstatica<Alumno> listaAlumnos, String pathArchivo){
        this.listaAlumnos = listaAlumnos;
        this.tiempoFinalizacion = 0;
        try{
            this.archivoAlumnos = new ArchivoAlumnos(pathArchivo);
        }catch(IOException ex){
            System.out.println(ex);
            System.exit(0);
        }
    }
    
    public void setTiempoFinalizacion(long tiempoFinalizacion){
        this.tiempoFinalizacion = tiempoFinalizacion;
    }
    
    public void insertarAlumno(){
        Alumno nuevoAlumno = archivoAlumnos.getAlumnoAleatorio();
        int insercion = (int)Math.floor(Math.random() * 2 + 1);

        if(insercion == 1)
            this.listaAlumnos.insertarInicio(nuevoAlumno);
        else
            this.listaAlumnos.insertarFinal(nuevoAlumno);
    }
    
    @Override
    public void run(){
        while(System.currentTimeMillis() < this.tiempoFinalizacion){
            insertarAlumno();
        }
    }
}
