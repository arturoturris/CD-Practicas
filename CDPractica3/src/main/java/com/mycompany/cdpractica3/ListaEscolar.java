package com.mycompany.cdpractica3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaEscolar {
    private final ListaEstatica<Alumno> lista;
    private final String pathArchivos;
    private final String archivoAlumnos;
    
    ListaEscolar(int tamano, String pathArchivos, String archivoAlumnos){
        this.lista = new ListaEstatica<Alumno>(tamano);
        this.pathArchivos = pathArchivos;
        this.archivoAlumnos = archivoAlumnos;
    }
    
    public void agregarAlumnoAlInicio(){
        ListaEstatica<Alumno> alumnosEnArchivo = getAlumnosDesdeArchivo(this.pathArchivos + this.archivoAlumnos);
        String []cabeceras = {"Matricula","Promedio","Nombres"};

        if(this.lista.estaLlena()){
            System.out.println("La lista está llena.");
            return;
        }
        
        alumnosEnArchivo.mostrarLista(cabeceras);
        System.out.println("ALUMNO A INGRESAR AL INICIO.");
        
        try {
            this.lista.insertarInicio(getAlumnoDesdeLista(alumnosEnArchivo));
            System.out.println("Se ha insertado el alumno seleccionado al inicio.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void agregarAlumnoAlFinal(){
        ListaEstatica<Alumno> alumnosEnArchivo = getAlumnosDesdeArchivo(this.pathArchivos + this.archivoAlumnos);
        String []cabeceras = {"Matricula","Promedio","Nombres"};

        if(this.lista.estaLlena()){
            System.out.println("La lista está llena.");
            return;
        }
        
        alumnosEnArchivo.mostrarLista(cabeceras);
        System.out.println("ALUMNO A INGRESAR AL FINAL.");
        
        try {
            this.lista.insertarInicio(getAlumnoDesdeLista(alumnosEnArchivo));
            System.out.println("Se ha insertado el alumno seleccionado al final.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarAlumnoInicio(){
        try {
            this.lista.eliminarInicio();
            System.out.println("Se ha eliminado el alumno del inicio.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void eliminarAlumnoFinal(){
        try {
            this.lista.eliminarFinal();
            System.out.println("Se ha eliminado el alumno del final.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modificarAlumno(){
        if(this.lista.estaVacia()){
            System.out.println("No hay alumnos para modificar.");
            return;
        }
        
        String []cabeceras = {"Matricula","Promedio","Nombres"};
        int index, matricula;

        lista.mostrarLista(cabeceras);
        System.out.println("Matricula de alumno a modificar");
        matricula = capturarOpcionMenu();
        index = lista.obtenerIndex(new Alumno(null,matricula,0.0));

        if(index == -1){
            System.out.println("No se econtró al alumno.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Alumno alumno = lista.obtenerElemento(index);
        String nuevoNombre, nuevaMatricula, nuevoPromedio;

        System.out.println("NUEVOS DATOS (dejar en blanco para conservar el anterior)");
        
        System.out.print("Nuevo nombre: ");
        nuevoNombre = scanner.nextLine();
        System.out.print("Nueva matricula: ");
        nuevaMatricula = scanner.nextLine();
        System.out.print("Nuevo promedio: ");
        nuevoPromedio = scanner.nextLine();

        if(!nuevoNombre.equals(""))
            alumno.setNombres(nuevoNombre);
        if(!nuevaMatricula.equals(""))
            alumno.setMatricula(Integer.parseInt(nuevaMatricula));
        if(!nuevoPromedio.equals(""))
            alumno.setPromedio(Float.parseFloat(nuevoPromedio));
        
        System.out.println("Se ha modificado el alumno.");
    }
    
    public void mostrarAlumnos(){
        String []cabeceras = {"Matricula","Promedio","Nombres"};
        
        this.lista.mostrarLista(cabeceras);
    }
    
    public void desplegarMenu(){
        separador();
        
        //CABECERA
        System.out.println("-Lista Estática-");
        System.out.println("Tamaño máximo: " + this.lista.getTamanoMaximo());
        System.out.println("Tamño actual: " + this.lista.getTamanoActual());
        separador();
        //OPCIONES
        System.out.println("1. Insertar al inicio");
        System.out.println("2. Insertar al final");
        System.out.println("3. Eliminar al inicio");
        System.out.println("4. Eliminar al final");
        System.out.println("5. Modificar");
        System.out.println("6. Mostrar lista de alumnos");
        System.out.println("7. Salir");
        separador();
    }
    
    public int capturarOpcionMenu(){
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
    
    public void llamarOpcion(int opcion){
        String archivo = this.pathArchivos + "alumnos.txt";
        
        switch(opcion){
            //1) INSERTAR AL INICIO
            case 1:{
                agregarAlumnoAlInicio();
                break;
            }
            
            //2) INSERTAR AL FINAL
            case 2:{
                agregarAlumnoAlFinal();
                break;
            }
            
            //3) ELIMINAR EL INICIO
            case 3:{
                eliminarAlumnoInicio();
                break;
            }
            
            //4) ELIMINAR EL FINAL
            case 4:{
                eliminarAlumnoFinal();
                break;
            }
            
            //5) MODIFICAR ALUMNO
            case 5:{
                modificarAlumno();
                break;
            }
            
            //6) MOSTRAR ALUMNOS
            case 6:{
                mostrarAlumnos();
                exportarListaAlumnos();
                break;
            }
            
            //7) SALIR
            case 7:{
                System.exit(0);
                break;
            }
            
            default:{
                System.out.println("Seleccione una opción válida.");
                break;
            }
        }
    }
    
    private void exportarListaAlumnos(){
        Scanner scanner = new Scanner(System.in);
        String exportar;
        
        System.out.print("Desea exportar la lista de alumnos a un archivo? (S/N): ");
        
        exportar = scanner.nextLine();
        
        if(!exportar.equals("S"))
            return;
        
        Alumno alumno;
        String nuevoArchivo = this.pathArchivos + "Alumnos-" + new SimpleDateFormat("yyMMddHHmmssZ").format(new Date()) + ".txt";
        File archivo = new File(nuevoArchivo);
        FileWriter writer ;
        
        try{
            archivo.createNewFile();
        }catch(IOException ex){
            Logger.getLogger(ListaEscolar.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido exportar la lista de alumnos.");
        }
        
        
        try {
            writer = new FileWriter(nuevoArchivo);
        } catch (IOException ex) {
            Logger.getLogger(ListaEscolar.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido exportar la lista de alumnos.");
            return;
        }
        
        for(int i=0;i<this.lista.getTamanoActual(); i++){
            alumno = this.lista.obtenerElemento(i);
            
            try {
                writer.write(alumno.getNombres() + ";" + alumno.getMatricula() + ";" + String.format("%.2f", alumno.getPromedio()) + "\n");
            } catch (IOException ex) {
                Logger.getLogger(ListaEscolar.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No se ha podido exportar la lista de alumnos.");
                return;
            }
        }
        
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ListaEscolar.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido exportar la lista de alumnos.");
            return;
        }
        
        System.out.println("La lista de alumnos se ha exportado con éxito.");
    }
    
    public static int capturarTamanoLista(){
        Scanner scanner = new Scanner(System.in);
        int tamano;
        
        System.out.print("Ingrese el tamaño de la lista de alumnos: ");
        
        while(true){
            try{
                tamano = scanner.nextInt();
                
                if(tamano <= 0)
                    throw new Exception();
                
                return tamano;
            }catch(Exception ex){
                System.out.print("Ingrese un tamaño válido: ");
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
    
    public static void main(String []args){
        String pathArchivos = "C:\\Users\\Carmina72\\Documents\\NetBeansProjects\\com.mycompany_CDPractica3_jar_1.0-SNAPSHOT\\src\\main\\java\\com\\mycompany\\cdpractica3\\";
        ListaEscolar listaAlumnos = new ListaEscolar(ListaEscolar.capturarTamanoLista(), pathArchivos, "alumnos.txt");;
        int opcion;
        
        while(true){
            listaAlumnos.desplegarMenu();
            opcion = listaAlumnos.capturarOpcionMenu();
            System.out.println("\n\n\n\n");
            listaAlumnos.llamarOpcion(opcion);
            esperar();
            System.out.println("\n\n\n\n");
        }
    }
    
    public static ListaEstatica<Alumno> getAlumnosDesdeArchivo(String archivo){
        try {
            String textoArchivo = Files.readString(Paths.get(archivo),StandardCharsets.UTF_8);
            String []alumnos = textoArchivo.split("\n");
            ListaEstatica<Alumno> listaAlumnos = new ListaEstatica<Alumno>(alumnos.length);
            String []propiedades;
            
            for(String alumno: alumnos){
                propiedades = alumno.trim().split(";");
                listaAlumnos.insertarFinal(new Alumno(propiedades[0],Integer.parseInt(propiedades[1]),Float.parseFloat(propiedades[2])));
            }
            
            return listaAlumnos;
        } catch (IOException ex) {
            System.out.println("Ha sucedido un error al leer el archivo. Verifique el formato.");
        } catch (Exception ex) {
            System.out.println("No se pueden insertar más alumnos.");
        }
        return null;
    }
    
    public static Alumno getAlumnoDesdeLista(ListaEstatica<Alumno> lista){
        Scanner scanner = new Scanner(System.in);
        int matricula, index;
        
        System.out.print("Matricula de alumno: ");
        while(true){
            try{
                matricula = scanner.nextInt();
                index = lista.obtenerIndex(new Alumno(null,matricula,0.0));
                
                if(index == -1)
                    throw new Exception("El alumno no se encuentra en la lista");
                
                break;
            }catch(Exception ex){
                scanner.nextLine();
                System.out.print("Ingrese una matrícula válida: ");
            }
        }
        
        return lista.obtenerElemento(index);
    }
    
    
}
