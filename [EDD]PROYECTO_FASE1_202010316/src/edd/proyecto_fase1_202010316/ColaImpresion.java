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
    
    //
    public static int ContadorColor;
    //
    
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
            System.out.println(recorrido.impresora.tipoImg);
            System.out.println(recorrido.impresora.numVentana);
            recorrido=recorrido.siguiente;
        }
    }
    
    //Verificar si en la cola existe la imagen de un cliente
    public String BuscarCliente(int id_cliente){
        Nodo recorrido=inicioCola;
        while(recorrido!=null){
            if(id_cliente==recorrido.impresora.id_cliente){
                String respuesta="si";
                return respuesta;
            }
            recorrido=recorrido.siguiente;
        }
        return null;
    }
    
    //Imprimir imagenes y entregarlas a su respectivo cliente
    public void ImprimirImg(ColaImpresion cola_impresion, ListaDeEspera lista_espera){
        Nodo recorrido=inicioCola;
        while(recorrido!=null){
            
            if(recorrido.impresora.tipoImg=="color"){
                ContadorColor=ContadorColor+1;
                if(ContadorColor==2){
                    ImagenPendiente imgp2=new ImagenPendiente(recorrido.impresora.tipoImg); 
                    
                    ClientesEspera clienteEspera2=new ClientesEspera("",0,"",0);
                    clienteEspera2=lista_espera.BuscarIdClienteEspera(recorrido.impresora.id_cliente);//Encontrar a que cliente pertence la imagen
                    clienteEspera2.ListaImgPendiente.Insertar_ImagenPendiente(imgp2);//Agregar imagen a la lista del cliente
                    
                    System.out.println("Se completa la impresion de una imagen a color y se le entrega al "+clienteEspera2.encabezado);
                    
                    cola_impresion.Extraer_ColaImpresion();//Saco la imagne de la cola de impresion
                    
                    ContadorColor=0;
                    break;
                }
                break;
            }
            
            
            recorrido=recorrido.siguiente;
        }
    }
    
}
