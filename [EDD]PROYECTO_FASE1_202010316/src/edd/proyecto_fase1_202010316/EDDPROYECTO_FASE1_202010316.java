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
    public static void main(String[] args) throws IOException {
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
                        GenerarClientesColaRecepcion();//Generar clientes random
                        System.out.println("------------------PASO "+contador_pasos_generales+"------------------");
                        contador_pasos_generales+=1;
                        System.out.println("------------------------------------------");
                        VerificarColaImpresion();
                        ImprimirImagen();
                        EntregarImagenVentana();
                        EjecutarPaso();                   
                        break;
                    case 3:
                        //Cola Recepcion
                        cola_recepcion.generarDot();
                        cola_recepcion.generarJPG();
                        
                        //Lista ventanillas
                        lista_ventanillas.generarDot();
                        lista_ventanillas.generarJPG();
                        
                        //Cola impresora Color
                        cola_impresion.generarDot();
                        cola_impresion.generarJPG();
                        
                        //Cola impresora Blanco y Negro
                        cola_impresionBw.generarDot();
                        cola_impresionBw.generarJPG();
                        
                        //ListaDeEspera
                        lista_espera.dibujarGrapvhiz();
                        
                        //Lista Clientes Atendidos
                        lista_clienteAtendido.generarDot();    
                        lista_clienteAtendido.generarJPG();
                        //
                        break;
                    case 4:
                        Reportes();
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
    
    public static void Reportes(){
        Scanner sn= new Scanner(System.in);
        boolean salir=false;
        int opcion;
        
        while(!salir){
            
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("| 1. Top 5 de clientes con mayor cantidad de imagenes a color           |");
            System.out.println("| 2. Top 5 de clientes con menor cantidad de imágenes en blanco y negro |");
            System.out.println("| 3. Información del cliente que más pasos estuvo en el sistema         |");
            System.out.println("| 4. Buscar cliente                                                     |");
            System.out.println("| 5. Regresar                                                           |");
            System.out.println("-------------------------------------------------------------------------");
            
            try{
            
                System.out.println("Introduce una opcion");
                opcion=sn.nextInt();

                switch(opcion){
                    case 1:
                        lista_clienteAtendido.Top5ClientesImgColor();
                        lista_clienteAtendido.MostrarTop5ImgColor();
                        break;
                    case 2:
                        lista_clienteAtendido.Top5ClientesImgBw();     
                        lista_clienteAtendido.MostrarTop5ImgBw();
                        break;
                    case 3:
                        lista_clienteAtendido.ClienteMayorNumPasos(lista_clienteAtendido);
                        break;
                    case 4:
                        Scanner n = new Scanner (System.in);
                        System.out.println("Ingrese el id del cliente a buscar: ");
                        int id=n.nextInt();
                        ReportesBuscarCliente(id);
                        break;
                    case 5:
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
        Scanner n = new Scanner (System.in);
        System.out.println("Ingrese la ruta del archivo JSON: ");
        path=n.nextLine();
            
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
                JSONObject objetoJson = new JSONObject(contenidoJSON);
                JSONObject contenidoCliente = objetoJson.getJSONObject(nCliente);
                int id_cliente=contenidoCliente.getInt("id_cliente");
                String nombre_cliente=contenidoCliente.getString("nombre_cliente");
                int img_color=contenidoCliente.getInt("img_color");
                int img_bw=contenidoCliente.getInt("img_bw");
                
                Clientes cl=new Clientes(nCliente,id_cliente,nombre_cliente,img_color,img_bw);
                cola_recepcion.Insertar(cl);
                
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
    
    //verificar que el cliente tenga imagenes en cola de impresion
    public static void VerificarColaImpresion(){
        lista_espera.VerificacionImagenes(cola_impresion, cola_impresionBw, lista_clienteAtendido, lista_espera);
    }
    
    public static void ReportesBuscarCliente(int idCliente){
        lista_clienteAtendido.BuscarClienteAtendido(idCliente);      
    }
    
    //Generar los clientes aleatorios
    public static void GenerarClientesColaRecepcion(){
        //Genero clientes aleatorios (entre 0 y 3)
        int CantidadClientes=0;
        CantidadClientes=(int)(Math.random()*4);
        
        //Genero cantidad de imagenes por cliente (entre 0 y 4) 
        int ImgColor=0;
        ImgColor=(int)(Math.random()*3);
        
        int ImgBw=0;
        ImgBw=(int)(Math.random()*3);
        
        String nombre[]={"Joseph","Luis","Anthony","Maria","Jeferson","Monica","Sofia","Alejandra","Steven","Justin","Marlon","Mark","Jose","Antonio","Manuel","Francisco","David","Javier","Jaime","James"};
        
        int NombreAlz=0;
        NombreAlz=(int)(Math.random()*nombre.length);
        
        for(int i=0;i<CantidadClientes;i++){
            
        }
    }
    
}
