package com.mycompany.cdpractica2;

public class ListaEstatica<T> {
    private T []lista;
    private final int tamanoMaximo;
    private int tamanoActual;
    
    public ListaEstatica(int tamanoMaximo){
        this.tamanoMaximo = tamanoMaximo;
        this.lista = (T[])new Object[tamanoMaximo];
        this.tamanoActual = 0;
    }
    
    public void insertarInicio(T objeto) throws Exception{
        if(this.tamanoMaximo == this.tamanoActual)
            throw new Exception("No se pueden agregar más elementos a la lista.");
        
        for(int i=this.tamanoActual;i>0;i--){
            this.lista[i] = this.lista[i-1];
        }

        this.lista[0] = objeto;
        this.tamanoActual++;
    }
    
    public void insertarFinal(T objeto) throws Exception{
        if(this.tamanoMaximo == this.tamanoActual)
            throw new Exception("No se pueden agregar más elementos a la lista.");
        
        this.lista[this.tamanoActual] = objeto;
        this.tamanoActual++;
    }
    
    public void eliminarInicio() throws Exception{
        if(this.tamanoActual == 0)
            throw new Exception("No hay elementos en la lista para eliminar.");
        
        for(int i=0;i<this.tamanoActual;i++){
            this.lista[i] = this.lista[i+1];
        }
        
        this.tamanoActual--;
    }
    
    public void eliminarFinal() throws Exception{
        if(this.tamanoActual == 0)
            throw new Exception("No hay elementos en la lista para eliminar.");
        
        this.tamanoActual--;
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
}
