package com.mycompany.cdpractica5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchivoAlumnos {
    private String archivo;
    private ListaEstatica<Alumno> listaAlumnos;
    
    public ArchivoAlumnos(String archivo) throws IOException{
        this.archivo = archivo;
        this.listaAlumnos = getAlumnosDesdeArchivo();
    }
    
    private ListaEstatica<Alumno> getAlumnosDesdeArchivo() throws IOException{
        try {
            String textoArchivo = Files.readString(Paths.get(this.archivo),StandardCharsets.UTF_8);
            String []alumnos = textoArchivo.split("\n");
            ListaEstatica<Alumno> listaAlumnos = new ListaEstatica<Alumno>(alumnos.length);
            String []propiedades;
            
            for(String alumno: alumnos){
                propiedades = alumno.trim().split(";");
                listaAlumnos.insertarFinal(new Alumno(propiedades[0],Integer.parseInt(propiedades[1]),Float.parseFloat(propiedades[2])));
            }
            
            return listaAlumnos;
        } catch (IOException ex) {
            throw new IOException("El archivo " + this.archivo + " no cumple con el formato necesario o no existe. Verifique el formato.");
        }
    }
    
    public Alumno getAlumnoAleatorio(){
        int index = (int)Math.floor(Math.random() * this.listaAlumnos.getTamanoActual());
        
        return this.listaAlumnos.obtenerElemento(index);
    }
}
