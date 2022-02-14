/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase1_202010316;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author josep
 */
public class ColaRecepcion {
    
    
    private NodoRecepcion inicioCola, finalCola;
    String Cola="";
    
    public ColaRecepcion(){
        inicioCola=null;
        finalCola=null;
        
    }
    
    //Metodo para saber si la cola esta vacia
    public boolean ColaVacia(){
        if (inicioCola==null){
            return true;
        }else{
            return false;
        }
    }
    
    //Metodo para insertar a la cola
    public void Insertar(Clientes _cliente){
        NodoRecepcion nuevo_nodo=new NodoRecepcion(_cliente);
        nuevo_nodo.informacion=_cliente;
        nuevo_nodo.siguiente=null;
        
        if (ColaVacia()){
            inicioCola=nuevo_nodo;
            finalCola=nuevo_nodo;
        }else{
            finalCola.siguiente=nuevo_nodo;
            finalCola=nuevo_nodo;
        }
        
    }
    
    //Metodo para extraer de la cola
    public Clientes Extraer(){
        if (!ColaVacia()) {
            Clientes informacion=inicioCola.informacion;
            if(inicioCola==finalCola){
                inicioCola=null;
                finalCola=null;
            }else{
                inicioCola=inicioCola.siguiente;
            }
            return informacion;
        }else{
            return null;
        }
    }
    
    //Metodo para eliminar un nodo de la cola
    public Clientes EliminarClienteCola(Clientes cliente) {
        if (!ColaVacia()) {
            if (inicioCola == finalCola && cliente == inicioCola.informacion) {
                inicioCola = finalCola = null;
            } else if (cliente == inicioCola.informacion) {
                inicioCola = inicioCola.siguiente;
            } else {
                NodoRecepcion anterior, temporal;
                anterior = inicioCola;
                temporal = inicioCola.siguiente;
                while (temporal != null && temporal.informacion != cliente) {
                    anterior = anterior.siguiente;
                    temporal = temporal.siguiente;
                }
                if (temporal != null) {
                    anterior.siguiente=temporal.siguiente;
                    if (temporal == finalCola) {
                        finalCola = anterior;
                    }
                }
            }
        }
        return null;
    }
    
    //Buscar un nodo por el id
    public Clientes BuscarNodoxId(int idCliente){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idCliente){
                return recorrido.informacion;
            }
            recorrido=recorrido.siguiente;
        }
        return null;
    }
    
    //Metodo para mostrar el contenido de la cola
    public void MostrarContenido(){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            System.out.println("-------------------------");
            System.out.println(recorrido.informacion.encabezado);
            System.out.println(recorrido.informacion.id_cliente);
            System.out.println(recorrido.informacion.nombre_cliente);
            System.out.println(recorrido.informacion.img_color);
            System.out.println(recorrido.informacion.img_bw);
            recorrido=recorrido.siguiente;
        }
    }
    
    //Buscar id e informar cuantas imagenes a color tiene el cliente
    public int CantidadImgColorRecepcion(int idClienteRecepcion){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idClienteRecepcion){
                return recorrido.informacion.img_color;
            } 
            
            recorrido=recorrido.siguiente;
        }
        return Integer.MAX_VALUE;
    }
    
    //Buscar id e informar cuantas imagenes a blanco y negro tiene el cliente
    public int CantidadImgBwRecepcion(int idClienteRecepcion){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idClienteRecepcion){
                return recorrido.informacion.img_bw;
            } 
            
            recorrido=recorrido.siguiente;
        }
        return Integer.MAX_VALUE;
    }
    
    //Buscar id e informar encabezado
    public String EncabezadoParaImg(int idClienteRecepcion){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idClienteRecepcion){
                return recorrido.informacion.encabezado;
            } 
            
            recorrido=recorrido.siguiente;
        }
        return null;
    }
    
    //Buscar id e informar nombre del cliente
    public String NombreParaImg(int idClienteRecepcion){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idClienteRecepcion){
                return recorrido.informacion.nombre_cliente;
            } 
            
            recorrido=recorrido.siguiente;
        }
        return null;
    }
    
    //Quitarle una imagen a color al cliente
    public void QuitarImagenColor(int idClienteRecepcion){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idClienteRecepcion){
                if(recorrido.informacion.img_color!=0){
                    recorrido.informacion.img_color=recorrido.informacion.img_color-1;
                    break;
                }
            }         
            recorrido=recorrido.siguiente;
        }
    }
    
    //Quitarle una imagen blanco y negro al cliente
    public void QuitarImagenBw(int idClienteRecepcion){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.id_cliente==idClienteRecepcion){
                if(recorrido.informacion.img_bw!=0){
                    recorrido.informacion.img_bw=recorrido.informacion.img_bw-1;
                    break;
                }
            }         
            recorrido=recorrido.siguiente;
        }
    }
    
    
    
}
