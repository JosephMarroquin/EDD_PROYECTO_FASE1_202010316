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
public class ListaDeEspera {
    
    public class Nodo{
        public ClientesEspera clienteEspera;
        public Nodo siguiente, anterior;
        public Nodo(ClientesEspera _clienteEspera){
            this.clienteEspera=_clienteEspera;
        }
    }
    
    Nodo primero, ultimo;
    
    public ListaDeEspera(){
        primero=null;
        ultimo=null;
    }
    
    //Metodo para ingresar datos a la lista circular doble
    
    public void IngresarListaEspera(ClientesEspera _clienteEspera){
        Nodo nuevo_nodo=new Nodo(_clienteEspera);
        nuevo_nodo.clienteEspera=_clienteEspera;
        if(primero==null){
            primero=nuevo_nodo;
            primero.siguiente=primero;
            nuevo_nodo.anterior=ultimo;
            ultimo=nuevo_nodo;
        }else{
            ultimo.siguiente=nuevo_nodo;
            nuevo_nodo.siguiente=primero;
            nuevo_nodo.anterior=ultimo;
            ultimo=nuevo_nodo;
            primero.anterior=ultimo;
        }
    }
    
    //Metodo para mostrar la lista de espera
    
    public void MostrarListaEspera(){
        Nodo actual=primero;
        do{
            System.out.println(actual.clienteEspera.encabezado);
            System.out.println(actual.clienteEspera.id_cliente);
            System.out.println(actual.clienteEspera.nombre_cliente);
            System.out.println(actual.clienteEspera.ventanilla_atentida);
            actual=actual.siguiente;
        }while(actual!=primero);
    }
    
    //Buscar por id del cliente
    public ClientesEspera BuscarIdClienteEspera(int idClienteEspera){
        Nodo actual=primero;
        do{
            if(actual.clienteEspera.id_cliente==idClienteEspera){
                return actual.clienteEspera;
            }
            actual=actual.siguiente;
        }while(actual!=primero);
        return null;
    }
    
}
