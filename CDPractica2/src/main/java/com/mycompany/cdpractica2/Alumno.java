package com.mycompany.cdpractica2;

import java.util.Scanner;

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
    
    public String toString(){
        return String.format("%d\t%.2f\t%s", this.matricula, this.promedio, this.nombres);
    }
    
    public static Alumno capturarAlumno(){
        Scanner scanner = new Scanner(System.in);
        String nombres;
        int matricula;
        double promedio;
        
        System.out.println("REGISTRO DE ALUMNO");
        System.out.print("Nombres: ");
        nombres = scanner.nextLine();
        System.out.print("Matricula: ");
        matricula = scanner.nextInt();
        System.out.print("Promedio: ");
        promedio = scanner.nextDouble();
        
        return new Alumno(nombres,matricula,promedio);
    }
}
