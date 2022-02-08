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
public class ColaImpresion {
    
    private Nodo inicioCola, finalCola;
    
    public class Nodo{
        public Impresoras impresora;
        public Nodo siguiente=null;
        public Nodo(Impresoras _impresora){
            this.impresora=_impresora;
        }
    }
    
    public ColaImpresion(){
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
    public void Insertar_ColaImpresion(Impresoras _impresora){
        Nodo nuevo_nodo=new Nodo(_impresora);
        nuevo_nodo.impresora=_impresora;
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
    public Impresoras Extraer_ColaImpresion(){
        if (!ColaVacia()) {
            Impresoras informacion=inicioCola.impresora;
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
    public void MostrarContenido_ColaImpresion(){
        Nodo recorrido=inicioCola;
        while(recorrido!=null){
            System.out.println("-------------------------");
            System.out.println(recorrido.impresora.id_cliente);
            System.out.println(recorrido.impresora.img_color);
            System.out.println(recorrido.impresora.img_bw);
            recorrido=recorrido.siguiente;
        }
    }
    
}
