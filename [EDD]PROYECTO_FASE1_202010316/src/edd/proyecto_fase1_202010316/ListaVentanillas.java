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
public class ListaVentanillas {
    
    private Nodo cabeza;
    
    public class Nodo{
        public Ventanillas ventanilla;
        public Nodo next=null;
        public Nodo(Ventanillas _ventanilla){
            this.ventanilla=_ventanilla;
        }
    }
    
    //Metodo para saber si la lista esta vacia
    public boolean ListaVacia(){
        if (cabeza==null){
            return true;
        }else{
            return false;
        }
    }
    
    //INGRESO DE DATOS
    public void InsertarVentanilla(Ventanillas _ventanilla){
        Nodo nuevo_nodo=new Nodo(_ventanilla);
        nuevo_nodo.next=cabeza;
        cabeza=nuevo_nodo;
    }
    
    //Mostar contenido de la lista
    
    public void Mostrar_ventanilla(){
        Nodo aux=cabeza;
        while(aux!=null){
            System.out.println("-------------------------");
            System.out.println(aux.ventanilla.nVentanilla);
            System.out.println(aux.ventanilla.id_cliente);
            System.out.println(aux.ventanilla.estado);
            aux=aux.next;
        }     
    }
   
    
}
