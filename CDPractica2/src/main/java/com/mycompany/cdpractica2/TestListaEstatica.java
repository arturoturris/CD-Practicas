package com.mycompany.cdpractica2;

import java.util.Scanner;

public class TestListaEstatica {
    public static void main(String []args){
        int opcion;
        ListaEstatica<Alumno> listaAlumnos = new ListaEstatica<Alumno>(5);
        
        while(true){
            separador();
            System.out.println("-Lista Estática-");
            System.out.println("Tamaño máximo: " + listaAlumnos.getTamanoMaximo());
            System.out.println("Tamaño actual: " + listaAlumnos.getTamanoActual());
            separador();
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Eliminar al inicio");
            System.out.println("4. Eliminar al final");
            System.out.println("5. Mostrar lista de alumnos");
            System.out.println("6. Salir");
            separador();
            
            opcion = capturarOpcion();
            
            if(opcion == 6)
                return;
            
            llamarOpcion(listaAlumnos,opcion);
            esperar();
            System.out.println("\n\n\n\n");
        }
    }
    
    public static int capturarOpcion(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Opcion: ");
        
        while(true){
            try{
                return scanner.nextInt();
            }catch(Exception ex){
                System.out.print("Capture una opción válida: ");
                scanner.nextLine();
            }
        }
    }
    
    public static void separador(){
        for(int i=0;i<60;i++)
            System.out.print("-");
        System.out.println("");
    }
    
    public static void esperar(){
        System.out.println("\nPresione una tecla para continuar...");
        new Scanner(System.in).nextLine();
    }
    
    public static void llamarOpcion(ListaEstatica lista, int opcion){
        switch(opcion){
            case 1:{
                try {
                    lista.insertarInicio(Alumno.capturarAlumno());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
            
            case 2:{
                try {
                    lista.insertarFinal(Alumno.capturarAlumno());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
            
            case 3:{
                try {
                    lista.eliminarInicio();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
            
            case 4:{
                try {
                    lista.eliminarFinal();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
            
            case 5:{
                String []cabeceras = {"Matricula","Promedio","Nombres"};
                lista.mostrarLista(cabeceras);
                break;
            }
            
            default:{
                System.out.println("Seleccione una opción válida.");
                break;
            }
        }
    }
}
