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
public class ColaImpresionBw {
    
    private Nodo inicioCola, finalCola;
    
    public class Nodo{
        public ImpresoraBw impresoraBw;
        public Nodo siguiente=null;
        public Nodo(ImpresoraBw _impresoraBw){
            this.impresoraBw=_impresoraBw;
        }
    }
    
    public ColaImpresionBw(){
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
    public void Insertar_ColaImpresionBw(ImpresoraBw _impresoraBw){
        Nodo nuevo_nodo=new Nodo(_impresoraBw);
        nuevo_nodo.impresoraBw=_impresoraBw;
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
    public ImpresoraBw Extraer_ColaImpresionBw(){
        if (!ColaVacia()) {
            ImpresoraBw informacion=inicioCola.impresoraBw;
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
            System.out.println(recorrido.impresoraBw.id_cliente);
            System.out.println(recorrido.impresoraBw.tipoImg);
            System.out.println(recorrido.impresoraBw.numVentana);
            recorrido=recorrido.siguiente;
        }
    }
    
    //Imprimir imagenes y entregarlas a su respectivo cliente
    public void ImprimirImgBw(ColaImpresionBw cola_impresionBw, ListaDeEspera lista_espera){
        Nodo recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.impresoraBw.tipoImg=="blanco y negro"){
                ImagenPendiente imgp=new ImagenPendiente(recorrido.impresoraBw.tipoImg); 
                
                ClientesEspera clienteEspera=new ClientesEspera("",0,"",0);
                clienteEspera=lista_espera.BuscarIdClienteEspera(recorrido.impresoraBw.id_cliente);//Encontrar a que cliente pertence la imagen
                clienteEspera.ListaImgPendiente.Insertar_ImagenPendiente(imgp);//Agregar imagen a la lista del cliente
                
                System.out.println("Se completa la impresion de una imagen en blanco y negro y se le entrega al "+clienteEspera.encabezado);
                
                cola_impresionBw.Extraer_ColaImpresionBw();//Saco la imagne de la cola de impresion
                break;
            }
                 
            recorrido=recorrido.siguiente;
        }
    }
    
}
