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
public class ListaImgPila {
    
    private Nodo inicio,fin;
    
    public class Nodo{
        public ImagenPorVentana imagenPorVentana;
        public Nodo siguiente=null;
        public Nodo(ImagenPorVentana _imagenPorVentana){
            this.imagenPorVentana=_imagenPorVentana;
        }
    }
    
    public ListaImgPila(){
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
    public void Insertar_ImagenPorVentana(ImagenPorVentana _imagenPorVentana){
        Nodo nuevo_nodo=new Nodo(_imagenPorVentana);
        nuevo_nodo.imagenPorVentana=_imagenPorVentana;
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
    public void Mostrar_ImagenPorVentana(){
        Nodo recorrido=inicio;
        while(recorrido!=null){
            System.out.println("-------------------------");
            System.out.println(recorrido.imagenPorVentana.numVentana);
            recorrido=recorrido.siguiente;
        }
    }
    
    //Buscar el numero de ventana a quien pertenece
    public ImagenPorVentana BuscarVentana(int numVentana){
        Nodo recorrido=inicio;
        while(recorrido!=null){
            
            if(recorrido.imagenPorVentana.numVentana==numVentana){
                return recorrido.imagenPorVentana;
            }
            recorrido=recorrido.siguiente;
        }
        return null;
    }
    
}
