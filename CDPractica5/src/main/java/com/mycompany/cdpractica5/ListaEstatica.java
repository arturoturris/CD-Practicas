package com.mycompany.cdpractica5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaEstatica<T> {
    private T []lista;
    private final int tamanoMaximo;
    private int tamanoActual;
    
    public ListaEstatica(int tamanoMaximo){
        this.tamanoMaximo = tamanoMaximo;
        this.lista = (T[])new Object[tamanoMaximo];
        this.tamanoActual = 0;
    }
    
    public synchronized void insertarInicio(T objeto){
        while(estaLlena()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ListaEstatica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(int i=this.tamanoActual;i>0;i--){
            this.lista[i] = this.lista[i-1];
        }

        this.lista[0] = objeto;
        this.tamanoActual++;
        System.out.println("Alumno insertado al inicio.");
        notify();
    }
    
    public synchronized void insertarFinal(T objeto){
        while(estaLlena()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ListaEstatica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.lista[this.tamanoActual] = objeto;
        this.tamanoActual++;
        System.out.println("Alumno insertado al final.");
        notify();
    }
    
    public synchronized void eliminarInicio(){
        while(estaVacia()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ListaEstatica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(int i=0;i<this.tamanoActual-1;i++){
            this.lista[i] = this.lista[i+1];
        }
        
        this.tamanoActual--;
        System.out.println("Alumno removido del inicio.");
        notify();
    }
    
    public synchronized void eliminarFinal(){
        while(estaVacia()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ListaEstatica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.tamanoActual--;
        System.out.println("Alumno removido del final.");
        notify();
    }
    
    public void mostrarLista(String []cabeceras){
        for(int i=0;i<cabeceras.length;i++){
            System.out.print(cabeceras[i] + "\t");
        }
            
        System.out.println("");
        
        for(int i=0;i<this.tamanoActual;i++)
            System.out.println(this.lista[i].toString());
    }
    
    public int getTamanoMaximo(){
        return this.tamanoMaximo;
    }
    
    public int getTamanoActual(){
        return this.tamanoActual;
    }
    
    public T obtenerElemento(int index){
        if(index < 0 || index >= this.tamanoActual)
            return null;
        
        return this.lista[index];
    }
    
    public int obtenerIndex(T objeto){
        int index = -1;
        
        for(int i=0;i<this.tamanoActual;i++){
            if(objeto.equals(this.lista[i])){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public boolean estaVacia(){
        return (this.tamanoActual == 0);
    }
    
    public boolean estaLlena(){
        return (this.tamanoActual == this.tamanoMaximo);
    }
}
