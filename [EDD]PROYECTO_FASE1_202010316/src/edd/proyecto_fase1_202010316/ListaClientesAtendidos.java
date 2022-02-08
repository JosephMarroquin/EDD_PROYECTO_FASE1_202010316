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
public class ListaClientesAtendidos {
    
    private Nodo cabeza;
    
    public class Nodo{
        public ClientesAtendidos cliente_atendido;
        public Nodo next=null;
        public Nodo(ClientesAtendidos _cliente_atendido){
            this.cliente_atendido=_cliente_atendido;
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
    public void InsertarClienteAtendido(ClientesAtendidos _clientes_atendidos){
        Nodo nuevo_nodo=new Nodo(_clientes_atendidos);
        nuevo_nodo.next=cabeza;
        cabeza=nuevo_nodo;
    }
    
    
    //Mostar contenido de la lista
    
    public void Mostrar_clienteAtendido(){
        Nodo aux=cabeza;
        while(aux!=null){
            System.out.println("-------------------------");
            System.out.println(aux.cliente_atendido.nombre_cliente);
            System.out.println(aux.cliente_atendido.ventanilla_atendida);
            System.out.println(aux.cliente_atendido.imagenes_impresas);
            System.out.println(aux.cliente_atendido.total_pasos);
            aux=aux.next;
        }     
    }
    
}
