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
public class ListaImgPendiente {
    
    private Nodo inicio,fin;
    
    public class Nodo{
        public ImagenPendiente imagenPendiente;
        public Nodo siguiente=null;
        public Nodo(ImagenPendiente _imagenPendiente){
            this.imagenPendiente=_imagenPendiente;
        }
    }
    
    public ListaImgPendiente(){
        inicio=null;
        fin=null;
    }
    
    //Metodo para saber si la lista esta vacia
    public boolean ListaVacia(){
        if (inicio==null){
            return true;
        }else{
            return false;
        }
    }
    
    //Metodo para insertar en la lista
    public void Insertar_ImagenPendiente(ImagenPendiente _imagenPendiente){
        Nodo nuevo_nodo=new Nodo(_imagenPendiente);
        nuevo_nodo.imagenPendiente=_imagenPendiente;
        nuevo_nodo.siguiente=null;
        
        if (ListaVacia()){
            inicio=nuevo_nodo;
            fin=nuevo_nodo;
        }else{
            fin.siguiente=nuevo_nodo;
            fin=nuevo_nodo;
        }
    }
    
    //Metodo para mostrar el contenido de la lista
    public void MostrarContenido_ImagenPendiente(){
        Nodo recorrido=inicio;
        while(recorrido!=null){
            System.out.println("-------------------------");
            System.out.println(recorrido.imagenPendiente.tipoIMG);
            recorrido=recorrido.siguiente;
        }
    }
    
}
