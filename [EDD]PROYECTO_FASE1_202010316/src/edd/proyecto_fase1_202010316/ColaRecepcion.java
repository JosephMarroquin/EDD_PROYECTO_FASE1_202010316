/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase1_202010316;

/**
 *
 * @author josep
 */
public class ColaRecepcion {
    
    //
    public static String encabezadoCliente;
    public static int idDelCliente;
    //
    
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
    
    //Saber que cliente estoy atendiendo
    public void MostrarEncabezadoCliente(){
        NodoRecepcion recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.informacion.atendiendo=="no"){
                encabezadoCliente=recorrido.informacion.encabezado;
                idDelCliente=recorrido.informacion.id_cliente;
                recorrido.informacion.atendiendo="si";
                break;
            } 
            
            recorrido=recorrido.siguiente;
        }
    }
    
}
