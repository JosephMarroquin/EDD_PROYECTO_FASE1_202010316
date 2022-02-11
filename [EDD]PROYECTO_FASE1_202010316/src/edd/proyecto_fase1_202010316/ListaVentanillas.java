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
    
    //
    public static int numVentana;
    //
    
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
        if(cabeza == null){
            cabeza = nuevo_nodo;
        }
        else{
            Nodo aux = cabeza;
            while(aux.next != null){
                aux=aux.next;
            }
            aux.next = nuevo_nodo;
        }
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
    
    //INGRESO DEL CLIENTE A LAS VENTANILLAS
    public void ingresarClienteVentana(String encabezadoCliente, int idCliente){
        Nodo aux=cabeza;
        while(aux!=null){
            
            if(aux.ventanilla.estado=="Disponible"){
                aux.ventanilla.estado="Ocupado"; //Se cambia el estado de la ventanilla a ocupado
                numVentana=aux.ventanilla.nVentanilla; //Se guarda el numero de ventanilla que lo atendio  
                aux.ventanilla.id_cliente=idCliente; //La ventanilla guarda que cliente esta atendiendo
                System.out.println("El "+encabezadoCliente+" ingresa a la ventanilla "+numVentana); //Se imprime en consola N cliente entro a N ventana
                break;
            }
            
            aux=aux.next;
        }     
    }
   
    
}
