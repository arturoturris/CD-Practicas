package com.mycompany.cdpractica5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alumno {
    private String nombres;
    private int matricula;
    private double promedio;
    
    public Alumno(String nombres, int matricula, double promedio){
        this.nombres = nombres;
        this.matricula = matricula;
        this.promedio = promedio;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    @Override
    public String toString(){
        return String.format("%d\t%.2f\t%s", this.matricula, this.promedio, this.nombres);
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        
        if(!(o instanceof Alumno))
            return false;
        
        Alumno alumno = (Alumno) o;
        
        return (this.matricula == alumno.matricula);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.matricula;
        return hash;
    }
    
    public static Alumno capturarAlumno(){
        Scanner scanner = new Scanner(System.in);
        String nombres;
        int matricula;
        double promedio;
        
        System.out.print("Nombres: ");
        nombres = scanner.nextLine();
        System.out.print("Matricula: ");
        matricula = scanner.nextInt();
        System.out.print("Promedio: ");
        promedio = scanner.nextDouble();
        
        return new Alumno(nombres,matricula,promedio);
    }
}
