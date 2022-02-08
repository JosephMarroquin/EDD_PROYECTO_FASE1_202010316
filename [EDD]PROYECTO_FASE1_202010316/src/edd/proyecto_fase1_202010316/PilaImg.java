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
public class PilaImg {
    
    public class Nodo{
        public Imagenes informacion;
        public Nodo siguiente=null;
        public Nodo(Imagenes imagen){
            this.informacion=imagen;
        }
    }
    
    private Nodo raiz;
    
    
    //Metodo para saber si la pila esta vacia
    public boolean PilaVacia(){
        if (raiz==null){
            return true;
        }else{
            return false;
        }
    }
    
    //Metodo insertar
    
    public void InsertarPilaImg(Imagenes _imagen){
        Nodo nuevo_nodo=new Nodo(_imagen);
        nuevo_nodo.informacion=_imagen;
        if(raiz==null){
            raiz=nuevo_nodo;
        }else{
            nuevo_nodo.siguiente=raiz;
            raiz=nuevo_nodo;
        }
    }
    
 
    //Metodo para extraer datos de la pila de imagenes
    
    public Imagenes ExtraerPilaImg(){
        if(raiz==null){
            return null;
        }else{
            Imagenes dato=raiz.informacion;
            raiz=raiz.siguiente;
            return dato;
        }
    } 
    
    
    //Metodo para mostrar la pila de imagenes
    
    public void MostrarPilaImg(){
        Nodo reco=raiz;
        while(reco!=null){
            System.out.println("-------------------------");
            System.out.println(reco.informacion.id_cliente);
            System.out.println(reco.informacion.img_color);
            System.out.println(reco.informacion.img_bw);
            reco=reco.siguiente;
        }
    }
    
}
