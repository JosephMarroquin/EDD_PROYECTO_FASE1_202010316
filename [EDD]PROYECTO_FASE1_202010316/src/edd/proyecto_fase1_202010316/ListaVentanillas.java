/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase1_202010316;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    
    //Saber si hay ventanillas disponibles
    
    public String HayVentanasDisp(){
        Nodo aux=cabeza;
        while(aux!=null){
            if(aux.ventanilla.estado=="Disponible"){
                String ss="si";
                return ss;
            }
            aux=aux.next;
        }     
        return null;
    }
    
    //INGRESO DEL CLIENTE A LAS VENTANILLAS
    public void ingresarClienteVentana(String encabezadoCliente, int idCliente, ColaRecepcion colaRecepcion, ColaRecepcionVerdad colaRecepcionVerdad){
        Nodo aux=cabeza;
        while(aux!=null){
            
            if(aux.ventanilla.estado=="Disponible" && ColaRecepcionVerdad.encabezadoCliente!=""){
                aux.ventanilla.estado="Ocupado"; //Se cambia el estado de la ventanilla a ocupado
                aux.ventanilla.id_cliente=idCliente; //La ventanilla guarda que cliente esta atendiendo
                System.out.println("El "+encabezadoCliente+" ingresa a la ventanilla "+aux.ventanilla.nVentanilla); //Se imprime en consola N cliente entro a N ventana
                
                ClientesEnCola clientCola=new ClientesEnCola("",0,"",0,0);
                clientCola=colaRecepcionVerdad.mandarAlClinete(idCliente);
                
                Clientes client=new Clientes(clientCola.encabezado,clientCola.id_cliente,clientCola.nombre_cliente,clientCola.img_color,clientCola.img_bw);
                colaRecepcion.Insertar(client);
                
                colaRecepcionVerdad.Extraer();
                
                break;
            }
            
            aux=aux.next;
        }     
    }
    
    //RECIBIR IMAGENES Y MANDARLOS A UNA PILA
    
    public void ingresarImagenApila(ColaRecepcion cola_recepcion, ListaImgPila lista_img_pila, ColaImpresion cola_impresion, ColaImpresionBw cola_impresionBw, ListaDeEspera lista_espera){
        Nodo aux=cabeza;
        while(aux!=null){
            
            if(aux.ventanilla.estado=="Ocupado"){
                
                //Mando a traer datos del cliente en recepcion
                int imagenesAcolor=cola_recepcion.CantidadImgColorRecepcion(aux.ventanilla.id_cliente);
                int imagenesAbw=cola_recepcion.CantidadImgBwRecepcion(aux.ventanilla.id_cliente);
                String encabezado=cola_recepcion.EncabezadoParaImg(aux.ventanilla.id_cliente);
                String nombreCliente=cola_recepcion.NombreParaImg(aux.ventanilla.id_cliente);
                //
                
                if(imagenesAcolor!=0){
                    System.out.println("La ventanilla "+aux.ventanilla.nVentanilla+" recibe una imagen a color del "+encabezado);
                    cola_recepcion.QuitarImagenColor(aux.ventanilla.id_cliente);  
                    
                    //agregar imagen a la pila
                    ImagenPorVentana imv=new ImagenPorVentana(0);
                    imv=lista_img_pila.BuscarVentana(aux.ventanilla.nVentanilla);
                    Imagenes cc=new Imagenes(aux.ventanilla.id_cliente,"color");
                    imv.pilaImagen.InsertarPilaImg(cc);
                }
                else if(imagenesAcolor==0){
                    if(imagenesAbw!=0){
                        System.out.println("La ventanilla "+aux.ventanilla.nVentanilla+" recibe una imagen en blanco y negro del "+encabezado);
                        cola_recepcion.QuitarImagenBw(aux.ventanilla.id_cliente);
                        
                        //agregar imagen a la pila
                        ImagenPorVentana imv2=new ImagenPorVentana(0);
                        imv2=lista_img_pila.BuscarVentana(aux.ventanilla.nVentanilla);
                        Imagenes cc2=new Imagenes(aux.ventanilla.id_cliente,"blanco y negro");
                        imv2.pilaImagen.InsertarPilaImg(cc2);
                    }
                    else if(imagenesAbw==0){
                        System.out.println("El "+encabezado+" es atendido e ingresa a la lista de espera");
                        System.out.println("La ventanilla "+aux.ventanilla.nVentanilla+" envía las imágenes del "+encabezado+" a sus respectivas colas de impresión");
                        
                        //INGRESAR CLIENTE A LISTA DE ESPERA
                        ClientesEspera cle=new ClientesEspera(encabezado,aux.ventanilla.id_cliente,nombreCliente,aux.ventanilla.nVentanilla);
                        lista_espera.IngresarListaEspera(cle);
                        
                        //SACO AL CLIENTE DE LA COLA
                        Clientes sacarCliente=new Clientes("",0,"",0,0);
                        sacarCliente=cola_recepcion.BuscarNodoxId(aux.ventanilla.id_cliente);
                        cola_recepcion.EliminarClienteCola(sacarCliente);
                        
                        //Vaciar la pila de imagenes y mandarlos a la cola de impresion
                        ImagenPorVentana imv3=new ImagenPorVentana(0);
                        imv3=lista_img_pila.BuscarVentana(aux.ventanilla.nVentanilla);
                        imv3.pilaImagen.ContarPilaImg();
                        int totalImgEnPila=PilaImg.contarImgPila;
                        
                        for(int i=1;i<=totalImgEnPila;i++){
                            //Mandar imagen a la cola de impresion
                            String tipoImg=imv3.pilaImagen.MostrarTipoImg();
                            
                            if(tipoImg=="color"){
                                Impresoras impre=new Impresoras(aux.ventanilla.id_cliente,tipoImg,aux.ventanilla.nVentanilla);
                                cola_impresion.Insertar_ColaImpresion(impre);
                                imv3.pilaImagen.ExtraerPilaImg();//Saco una imagen de la pila   
                            }
                            else if(tipoImg=="blanco y negro"){
                                ImpresoraBw impreBw=new ImpresoraBw(aux.ventanilla.id_cliente,tipoImg,aux.ventanilla.nVentanilla);
                                cola_impresionBw.Insertar_ColaImpresionBw(impreBw);
                                imv3.pilaImagen.ExtraerPilaImg();//Saco una imagen de la pila   
                            }
                            
                            
                            
                            
                        }
                        
                        //La ventana se encuentra disponible nuevamente 
                        aux.ventanilla.id_cliente=0;
                        aux.ventanilla.estado="Disponible";
                        
                        
                    }                  
                }                            
            }
            
            aux=aux.next;
        }   
    }
   
    //Metodo para graficar en graphviz
    public void generarDot() throws IOException{
        String resultado="digraph G{\nlabel=\""+"Lista de ventanillas"+"\";\nnode [shape=box];\n";
        Nodo aux = cabeza;
        String conexiones="";
        String nodos="";
        while(aux != null){
            nodos+="N"+aux.hashCode()+"[label=\"nodo "+" Ventanilla "+aux.ventanilla.nVentanilla+"\"];\n";
            if(aux.next != null){
                conexiones+="N"+aux.hashCode()+ " -> "+"N"+aux.next.hashCode()+";\n";
            }
            aux = aux.next;
        }
        resultado+= "//Agregando nodods\n";
        resultado+=nodos+"\n";
        resultado+= "//Agregando conexiones\n";
        resultado+="{rank= same;\n"+conexiones+"\n";
        
        resultado+="}\n}";       
        
        String path = "Estructuras\\ListaVentanillas.txt";
        Files.write(Paths.get(path), resultado.getBytes());
        
    }
    
    public void generarJPG(){
        try{
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            
            String fileInputPath ="Estructuras\\ListaVentanillas.txt";
            String fileOutputPath = "Estructuras\\ListaVentanillas.jpg";
            
            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];

            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
            
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {}
    }
    
}
