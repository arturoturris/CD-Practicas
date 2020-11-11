package com.mycompany.cdpractica1;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestArreglo {
    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        Arreglo arreglo = new Arreglo();
        int opcion;
        
        while(true){
            separador();
            System.out.println("Arreglo: " + arreglo.imprimirDatos());
            System.out.println("Tamaño: " + arreglo.getTamano());
            separador();
            System.out.println("1. Almacenar datos");
            System.out.println("2. Obtener la suma de los elementos");
            System.out.println("3. Buscar un dato");
            System.out.println("4. Obtener elemento mayor y menor");
            System.out.println("5. Obtener la moda");
            System.out.println("6. Imprimir elementos del arreglo");
            System.out.println("7. Salir");
            separador();
            
            System.out.print("Opcion: ");
            opcion = scanner.nextInt();
            System.out.println("");
            
            if(opcion == 7)
                return;
            
            llamarOpcion(arreglo,opcion);
            esperar();
        }
    }
    
    public static void llamarOpcion(Arreglo arreglo, int opcion){
        switch (opcion){
            case 1:{
                arreglo.almacenarDatos();
                break;
            }
            case 2:{
                System.out.println("La suma de todos los elementos del arreglo es: " + arreglo.obtenerSuma());
                break;
            }
            case 3:{
                System.out.println("");
                arreglo.buscarDato();
                break;
            }
            case 4:{
                int []mayorMenor = arreglo.obtenerMayorMenor();
                
                System.out.println("El menor elemento del arreglo es: " + mayorMenor[0]);
                System.out.println("El mayor elemento del arreglo es: " + mayorMenor[1]);
                
                break;
            }
            case 5:{
                System.out.print("El/los elemento/s modal/es es/son: ");
                arreglo.obtenerModa().forEach((elemento) -> {
                    System.out.print(elemento + "  ");
                });
                
                System.out.println("");
                
                break;
            }
            case 6:{
                break;
            }
            case 7:{
                
                break;
            }
        };
    }
    
    public static void esperar(){
        System.out.println("\nPresione una tecla para continuar...");
        new Scanner(System.in).nextLine();
    }
    
    public static void separador() {  
        for(int i=0;i<60;i++)
            System.out.print("-");
        System.out.println("");
    }  
}
