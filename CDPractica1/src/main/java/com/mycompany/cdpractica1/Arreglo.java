package com.mycompany.cdpractica1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;

public class Arreglo {
    private final int []a;
    private final int tamano;
    
    public Arreglo(){
        this.tamano = 5;
        this.a = new int[5];
        
        rellenarArreglo();
    }
    
    public Arreglo(int tamano){
        this.tamano = tamano;
        this.a = new int[tamano];
        
        rellenarArreglo();
    }
    
    private void rellenarArreglo(){
        for(int i=0;i<this.tamano;i++)
            this.a[i] = 0;
    }
    
    public int getTamano(){
        return this.tamano;
    }
    
    public void almacenarDatos(){
        Scanner scanner = new Scanner(System.in);
        
        for(int i=0;i<this.tamano;i++){
            System.out.print("Valor para posición " + i + " :");
            this.a[i] = scanner.nextInt();
        }
    }
    
    public String imprimirDatos(){
        String datos = "";
        
        for(int i=0;i<this.tamano;i++){
            datos += "a[" + i + "] = " + a[i] + "  ";
        }
        
        return datos;
    }
    
    public int obtenerSuma(){
        int suma = 0;
        
        for(int i=0;i<this.tamano;i++){
            suma += a[i];
        }
        
        return suma;
    }
    
    public void buscarDato(){
        Scanner scanner = new Scanner(System.in);
        int valor, posicion;
        
        System.out.print("Valor a buscar en el arreglo: ");
        valor = scanner.nextInt();
        posicion = buscarDato(valor);
        
        if(posicion == -1){
            System.out.println("El valor especificado no se encuentra en el arreglo.");
            return;    
        }
        
        System.out.println("El valor " + valor + " se encuentra en la posición " + posicion + " del arreglo.");
    }
    
    public int buscarDato(int dato){
        int index = -1;
        
        for(int i=0;i<this.tamano;i++){
            if(this.a[i] == dato){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int[] obtenerMayorMenor(){
        int []minMax = new int[2];
        int []tmpArray =Arrays.copyOf(this.a, this.tamano);
        
        Arrays.sort(tmpArray);
        
        minMax[0] = tmpArray[0];
        minMax[1] = tmpArray[this.tamano-1];
        
        return minMax;
    }
    
    public List<Integer> obtenerModa(){
        HashMap<Integer,Integer> contador = new HashMap<>();
        
        //COUNT ELEMENTS
        for(int i=0;i<this.tamano;i++){
            if(contador.get(a[i]) == null)
                contador.put(a[i],1);
            else
                contador.replace(a[i],contador.get(a[i]) + 1);
        }
        
        int valorModal = Collections.max(contador.values());
        List<Integer> clavesModales = new ArrayList<>();
        
        contador.entrySet().forEach((entrada) -> {
            if(entrada.getValue() == valorModal)
                clavesModales.add(entrada.getKey());
        });
        
        return clavesModales;
    }
}
