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

//
//

/**
 *
 * @author josep
 */
public class EDDPROYECTO_FASE1_202010316 {
    
    private static ColaRecepcion cola_recepcion=new ColaRecepcion();
    private static ListaVentanillas lista_ventanillas=new ListaVentanillas();
    private static ListaImgPila lista_img_pila=new ListaImgPila();
    private static ListaClientesAtendidos lista_clienteAtendido=new ListaClientesAtendidos();
    private static ColaImpresion cola_impresion=new ColaImpresion();
    private static ColaImpresionBw cola_impresionBw=new ColaImpresionBw();
    private static ListaDeEspera lista_espera=new ListaDeEspera();
    
    private static int contador_pasos_generales=1;
    private static int total_de_ventanillas;
    
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
                        System.out.println("------------------PASO "+contador_pasos_generales+"------------------");
                        contador_pasos_generales+=1;
                        System.out.println("------------------------------------------");
                        ImprimirImagen();
                        EntregarImagenVentana();
                        EjecutarPaso();                   
                        break;
                    case 3:
                        cola_recepcion.MostrarContenido();
                        System.out.println("------------------------");
                        System.out.println("------------------------");
                        System.out.println("------------------------");
                        System.out.println("------------------------");
                        System.out.println("------------------------");
                        Clientes a=new Clientes("",0,"",0,0);
                        a=cola_recepcion.BuscarNodoxId(2);
                        cola_recepcion.EliminarClienteCola(a);
                        cola_recepcion.MostrarContenido();
                        /*
                        ImagenPendiente imgp1=new ImagenPendiente("Color");
                        ImagenPendiente imgp2=new ImagenPendiente("Blanco y Negro");
                        ImagenPendiente imgp3=new ImagenPendiente("Color");
                        
                        ClientesEspera cp1=new ClientesEspera("Clietnte2",2,"Juan",5);
                        ClientesEspera cp2=new ClientesEspera("Clietnte3",3,"Pedro",6);
                        ClientesEspera cp3=new ClientesEspera("Clietnte4",4,"Alex",8);
                        ClientesEspera cp4=new ClientesEspera("Clietnte5",5,"Pepe",9);
                        lista_espera.IngresarListaEspera(cp1);
                        lista_espera.IngresarListaEspera(cp2);
                        lista_espera.IngresarListaEspera(cp3);
                        lista_espera.IngresarListaEspera(cp4);
                        
                        ClientesEspera gg=new ClientesEspera("",0,"",0);
                        gg=lista_espera.BuscarIdClienteEspera(2);
                        gg.ListaImgPendiente.Insertar_ImagenPendiente(imgp1);
                        gg.ListaImgPendiente.Insertar_ImagenPendiente(imgp2);
                        gg.ListaImgPendiente.MostrarContenido_ImagenPendiente();
                        
                        ClientesEspera gg2=new ClientesEspera("",0,"",0);
                        gg2=lista_espera.BuscarIdClienteEspera(3);
                        gg2.ListaImgPendiente.Insertar_ImagenPendiente(imgp3);
                        gg2.ListaImgPendiente.MostrarContenido_ImagenPendiente();
                        */
                        break;
                    case 4:
                        ImagenPorVentana img1=new ImagenPorVentana(1);
                        ImagenPorVentana img2=new ImagenPorVentana(2);
                        ImagenPorVentana img3=new ImagenPorVentana(3);
                        ImagenPorVentana img4=new ImagenPorVentana(4);
                        lista_img_pila.Insertar_ImagenPorVentana(img1);
                        lista_img_pila.Insertar_ImagenPorVentana(img2);
                        lista_img_pila.Insertar_ImagenPorVentana(img3);
                        lista_img_pila.Insertar_ImagenPorVentana(img4);
                        
                        ImagenPorVentana im=new ImagenPorVentana(0);
                        im=lista_img_pila.BuscarVentana(1);
                        Imagenes cc=new Imagenes(2,"color");
                        Imagenes cc2=new Imagenes(3,"color");
                        im.pilaImagen.InsertarPilaImg(cc);
                        im.pilaImagen.InsertarPilaImg(cc2);
                        im.pilaImagen.MostrarPilaImg();
                        im.pilaImagen.ContarPilaImg();
                        im.pilaImagen.ExtraerPilaImg();
                        im.pilaImagen.MostrarPilaImg();                        
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
                        total_de_ventanillas=totalVentanas;
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
            
            ImagenPorVentana img1=new ImagenPorVentana(i);
            lista_img_pila.Insertar_ImagenPorVentana(img1);
        }
    }
    
    
   
    //ALGORITMO PARA EJECUTAR LOS PASOS
        
    public static void EjecutarPaso(){
        
        //---------------------------------------------------------------------------------------
        cola_recepcion.MostrarEncabezadoCliente(lista_ventanillas); //Saber que cliente estoy atendiendo
        String encabezadoCliente=ColaRecepcion.encabezadoCliente; //Saber que cliente estoy atendiendo
        int idDelCliente=ColaRecepcion.idDelCliente; //Saber que cliente estoy atendiendo
        lista_ventanillas.ingresarClienteVentana(encabezadoCliente,idDelCliente);//INGRESO DEL CLIENTE A LAS VENTANILLAS
        //---------------------------------------------------------------------------------------
        
    }
    
    //Entrega de imagenes por el cliente a la ventanilla
    public static void EntregarImagenVentana(){
        
        lista_ventanillas.ingresarImagenApila(cola_recepcion, lista_img_pila, cola_impresion, cola_impresionBw, lista_espera);
    
    }
    
    //Imprimir Imagen y agregarla a la lista de espera
    public static void ImprimirImagen(){
        cola_impresion.ImprimirImg(cola_impresion, lista_espera);
        cola_impresionBw.ImprimirImgBw(cola_impresionBw, lista_espera);
    }
    
}
