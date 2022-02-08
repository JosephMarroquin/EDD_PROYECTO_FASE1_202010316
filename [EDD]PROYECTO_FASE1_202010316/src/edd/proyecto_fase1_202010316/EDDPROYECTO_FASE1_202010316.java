/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase1_202010316;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFileChooser;

//Librerias JSON.SIMPLE 1.1.1
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

import org.json.JSONObject;

/**
 *
 * @author josep
 */
public class EDDPROYECTO_FASE1_202010316 {
    
    private static ColaRecepcion cola_recepcion=new ColaRecepcion();
    private static ListaVentanillas lista_ventanillas=new ListaVentanillas();
    private static PilaImg pila_imagenes=new PilaImg();
    private static ListaClientesAtendidos lista_clienteAtendido=new ListaClientesAtendidos();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sn= new Scanner(System.in);
        boolean salir=false;
        int opcion;
        
        while(!salir){
            
            System.out.println("-------------------------------------------");
            System.out.println("| 1. Parámetros iniciales                 |");
            System.out.println("| 2. Ejecutar paso                        |");
            System.out.println("| 3. Estado en memoria de las estructuras |");
            System.out.println("| 4. Reportes                             |");
            System.out.println("| 5. Acerca de                            |");
            System.out.println("| 6. Salir                                |");
            System.out.println("-------------------------------------------");
            
            try{
            
                System.out.println("Introduce una opcion");
                opcion=sn.nextInt();

                switch(opcion){
                    case 1:
                        parametrosIniciales();
                        break;
                    case 2:
                        System.out.println("opcion 2");
                        pruebCola();
                        pruebaPila();
                        break;
                    case 3:
                        System.out.println("opcion 3");
                        ClientesAtendidos as=new ClientesAtendidos("Juan",2,3,56);
                        ClientesAtendidos as2=new ClientesAtendidos("Pedrop",3,43,256);
                        ClientesAtendidos as3=new ClientesAtendidos("PEPE",52,73,856);
                        lista_clienteAtendido.InsertarClienteAtendido(as);
                        lista_clienteAtendido.InsertarClienteAtendido(as2);
                        lista_clienteAtendido.InsertarClienteAtendido(as3);       
                        lista_clienteAtendido.Mostrar_clienteAtendido();
                        lista_ventanillas.Mostrar_ventanilla();
                        break;
                    case 4:
                        System.out.println("opcion 4");
                        Imagenes img=new Imagenes(1,2,3);
                        Imagenes img2=new Imagenes(44,32,32);
                        Imagenes img3=new Imagenes(31,62,83);
                        pila_imagenes.InsertarPilaImg(img);
                        pila_imagenes.InsertarPilaImg(img2);
                        pila_imagenes.InsertarPilaImg(img3);
                        break;
                    case 5:
                        System.out.println("--------DATOS DEL ESTUDIANTE--------");
                        System.out.println("| Joseph Jeferson Marroquín Monroy |");
                        System.out.println("| 202010316                        |");
                        System.out.println("| 5to semestre                     |");
                        System.out.println("| Proyecto Fase 1                  |");
                        System.out.println("| Estructura de datos Seccion C    |");
                        System.out.println("------------------------------------");
                        break;
                    case 6:
                        salir=true;
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            
            }catch(InputMismatchException e){
                System.out.println("Opcion Invalida");
                sn.next();
            }
            
        }
        
    }
    
    public static void parametrosIniciales(){
        Scanner sn= new Scanner(System.in);
        boolean salir=false;
        String opcion=" ";
        
        while(!salir){
            
            System.out.println("-------------------------------------------");
            System.out.println("| a. Carga masiva de clientes             |");
            System.out.println("| b. Cantidad de ventanillas              |");
            System.out.println("| c. Regresar                             |");
            System.out.println("-------------------------------------------");
            
            try{
            
                System.out.println("Introduce una opcion");
                opcion=sn.nextLine();

                switch(opcion){
                    case "a":
                        cargaMasiva();
                        break;
                    case "b":
                        Scanner n = new Scanner (System.in);
                        System.out.println("Ingrese el numero de ventanillas: ");
                        int totalVentanas=n.nextInt();
                        cargarVentanillas(totalVentanas);
                        break;
                    case "c":
                        salir=true;
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            
            }catch(InputMismatchException e){
                System.out.println("Opcion Invalida");
                sn.next();
            }
            
        }
    }
    
    public static void cargaMasiva(){
        String path;
        JFileChooser fc = new JFileChooser();
        int respuesta = fc.showOpenDialog(fc);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            path=String.valueOf(fc.getSelectedFile()); 
            
            //LECTURA DEL JSON GUARDANDOLO EN UN STRING  
            String contenidoJSON = "";
            try(BufferedReader br = new BufferedReader(new FileReader(path))){
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenidoJSON += linea;
                }                
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
            //CONTANDO EL NUMERO TOTAL DE CLIENTES EN EL JSON CARGADO
            int totalCharacters = 0;
            char temp;
            for (int i = 0; i < contenidoJSON.length(); i++) {
                temp = contenidoJSON.charAt(i);
                if (temp == '}')
                    totalCharacters++;
            }
            int ntotalClientes=totalCharacters-1;
            System.out.println("Total de clientes: "+ntotalClientes);

            
            //EXTRACCION DEL JSON EN EL STRING
            
            System.out.println(contenidoJSON);
            
            
            
            for(int i=1;i<=ntotalClientes;i++){
                
                //Insertar clientes en nuestra Cola Recepcion
                
                String nCliente="Cliente"+i;
                System.out.println("--------------------------------------------");
                JSONObject objetoJson = new JSONObject(contenidoJSON);
                JSONObject contenidoCliente = objetoJson.getJSONObject(nCliente);
                System.out.println(contenidoCliente);
                int id_cliente=contenidoCliente.getInt("id_cliente");
                System.out.println(id_cliente);
                String nombre_cliente=contenidoCliente.getString("nombre_cliente");
                System.out.println(nombre_cliente);
                int img_color=contenidoCliente.getInt("img_color");
                System.out.println(img_color);
                int img_bw=contenidoCliente.getInt("img_bw");
                System.out.println(img_bw);
                
                Clientes cl=new Clientes(nCliente,id_cliente,nombre_cliente,img_color,img_bw);
                cola_recepcion.Insertar(cl);
                
            }
            
        }
    }
   
    public static void pruebCola(){
        
        if (!cola_recepcion.ColaVacia()){
            cola_recepcion.MostrarContenido();
            cola_recepcion.Extraer();
        } else{
            System.out.println("-- Cola Recepcion Vacia --");
        }     
        
    }
    
    //Cargar el numero ded ventanillas a su lista simple enlazada
    public static void cargarVentanillas(int nVentanas){
        for(int i=1;i<=nVentanas;i++){
            Ventanillas v1=new Ventanillas(i);
            lista_ventanillas.InsertarVentanilla(v1);
        }
    }
    
    public static void pruebaPila(){
        
        if (!pila_imagenes.PilaVacia()){
            pila_imagenes.MostrarPilaImg();
            pila_imagenes.ExtraerPilaImg();
        } else{
            System.out.println("-- Pila Img Vacia --");
        }     
        
    }
    
}
