package com.mycompany.cdpractica5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaHilos {
    public static void main(String []args){
        int tamanoLista = 10;
        ListaEstatica<Alumno> listaAlumnos = new ListaEstatica<Alumno>(tamanoLista);
        String registroAlumnos = "C:\\Users\\Carmina72\\Documents\\NetBeansProjects\\CDPractica5\\src\\main\\java\\com\\mycompany\\cdpractica5\\alumnos.txt";
        Productor productor = new Productor(listaAlumnos, registroAlumnos);
        Consumidor consumidor = new Consumidor(listaAlumnos);
        int segundosEjecucion = 1;
        long finalizacion = System.currentTimeMillis() + segundosEjecucion * 1000;
        
        productor.setTiempoFinalizacion(finalizacion);
        consumidor.setTiempoFinalizacion(finalizacion);
        
        productor.start();
        consumidor.start();
        
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(PruebaHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("SEGUNDOS EN EJECUCIÓN: " + segundosEjecucion);
        System.out.println("Tamaño máximo de la lista: " + tamanoLista);
        System.out.println("Tamaño actual de la lista: " + listaAlumnos.getTamanoActual());
        String []cabeceras = {"Matricula","Promedio","Nombres"};
        listaAlumnos.mostrarLista(cabeceras);
    }
}