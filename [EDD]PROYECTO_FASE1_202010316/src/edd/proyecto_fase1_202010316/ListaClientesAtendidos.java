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
            System.out.println(aux.cliente_atendido.id_cliente);
            System.out.println(aux.cliente_atendido.nombre_cliente);
            System.out.println(aux.cliente_atendido.ventanilla_atendida);
            System.out.println(aux.cliente_atendido.imagenes_impresasColor);
            System.out.println(aux.cliente_atendido.imagenes_impresasBw);
            System.out.println(aux.cliente_atendido.total_pasos);
            aux=aux.next;
        }     
    }
    
    //Buscar cliente
    public void BuscarClienteAtendido(int idCliente){
        Nodo aux=cabeza;
        boolean encontrado=false;
        while(aux!=null){
            if(aux.cliente_atendido.id_cliente==idCliente){
                encontrado=true;
                break;
            }
            aux=aux.next;
        }
        if(encontrado==true){
            System.out.println("--------------------DATOS CLIENTE--------------------");
            System.out.println("Id: "+aux.cliente_atendido.id_cliente);
            System.out.println("Nombre: "+aux.cliente_atendido.nombre_cliente);
            System.out.println("Ventanilla que lo atendio: "+aux.cliente_atendido.ventanilla_atendida);
            System.out.println("Total de imagenes impresas a color: "+aux.cliente_atendido.imagenes_impresasColor);
            System.out.println("Total de imagenes impresas en blanco y negro: "+aux.cliente_atendido.imagenes_impresasBw);
            System.out.println("Total de pasos en el sistema: "+aux.cliente_atendido.total_pasos);
            System.out.println("-----------------------------------------------------");
        }else{
            System.out.println("El cliente solicitado no esta registrado como cliente atendido");
        }
    }
    
    //Saber que cliente tiene mas pasos en el sistema
    public void ClienteMayorNumPasos(ListaClientesAtendidos lista_clienteAtendido){
        Nodo aux=cabeza;
        int mayor=0;
        int id=0;
        while(aux!=null){
            if(aux.cliente_atendido.total_pasos > mayor){
                mayor=aux.cliente_atendido.total_pasos;
                id=aux.cliente_atendido.id_cliente;
            }
            aux=aux.next;
        }
        lista_clienteAtendido.BuscarClienteAtendido(id);
    }
    
    //Ordenar de mayor a menor segun imagenes a color
    public void Top5ClientesImgColor(){
        int t=1,c=1;
            Nodo act = cabeza;/*definimos que el apuntador act esta en el primer nodo*/
            while(act.next !=null)//Este while cuenta el total de nodos.
            {
                act = act.next;
                c++;
            }
            /*guardaran el valor temporalmente*/
            ClientesAtendidos clienAten =new ClientesAtendidos(0,"",0,0,0,0);
            //aqui se hace el ordenamiento
            do{
                act = cabeza;//aux esta en el primer nodo
                Nodo sig = act.next;//esta en el siguiente nodo 
                while(act.next != null)
                {
                    if(act.cliente_atendido.imagenes_impresasColor < sig.cliente_atendido.imagenes_impresasColor)
                    {
                        clienAten = act.cliente_atendido;
                        act.cliente_atendido = sig.cliente_atendido;
                        sig.cliente_atendido = clienAten;
                        act = act.next;
                        sig = sig.next;
                    }
                    else
                    {
                        act = act.next;
                        sig = sig.next;
                    }
                }
                t++;
            }while(t<=c);
    }
    
    //Ordenar de mayor a menor segun imagenes en blanco y negro
    public void Top5ClientesImgBw(){
        int t=1,c=1;
            Nodo act = cabeza;/*definimos que el apuntador act esta en el primer nodo*/
            while(act.next !=null)//Este while cuenta el total de nodos.
            {
                act = act.next;
                c++;
            }
            /*guardaran el valor temporalmente*/
            ClientesAtendidos clienAten =new ClientesAtendidos(0,"",0,0,0,0);
            //aqui se hace el ordenamiento
            do{
                act = cabeza;//aux esta en el primer nodo
                Nodo sig = act.next;//esta en el siguiente nodo 
                while(act.next != null)
                {
                    if(act.cliente_atendido.imagenes_impresasBw < sig.cliente_atendido.imagenes_impresasBw)
                    {
                        clienAten = act.cliente_atendido;
                        act.cliente_atendido = sig.cliente_atendido;
                        sig.cliente_atendido = clienAten;
                        act = act.next;
                        sig = sig.next;
                    }
                    else
                    {
                        act = act.next;
                        sig = sig.next;
                    }
                }
                t++;
            }while(t<=c);
    }
    
    //Mostrar en consola el top 5 clientes ImgColor
    public void MostrarTop5ImgColor(){
        Nodo aux=cabeza;
        int cont=0;
        while(aux!=null){
            cont=cont+1;
            System.out.println("-----------------------TOP "+cont+"-----------------------");
            System.out.println("Id: "+aux.cliente_atendido.id_cliente);
            System.out.println("Nombre: "+aux.cliente_atendido.nombre_cliente);
            System.out.println("Total de imagenes a color: "+aux.cliente_atendido.imagenes_impresasColor);
            System.out.println("----------------------------------------------------------");
            if(cont==5){
                break;
            }           
            aux=aux.next;
        }     
    }
    //Mostrar en consola el top 5 clientes imagenes en blanco y negro
    public void MostrarTop5ImgBw(){
        Nodo aux=cabeza;
        int cont=0;
        while(aux!=null){
            cont=cont+1;
            System.out.println("-----------------------TOP "+cont+"-----------------------");
            System.out.println("Id: "+aux.cliente_atendido.id_cliente);
            System.out.println("Nombre: "+aux.cliente_atendido.nombre_cliente);
            System.out.println("Total de imagenes en blanco y negro: "+aux.cliente_atendido.imagenes_impresasBw);
            System.out.println("----------------------------------------------------------");
            if(cont==5){
                break;
            }           
            aux=aux.next;
        }     
    }
    
    //Metodo para graficar en graphviz
    public void generarDot() throws IOException{
        String resultado="digraph G{\nlabel=\""+"Clientes Atendidos"+"\";\nnode [shape=box];\n";
        Nodo aux = cabeza;
        String conexiones="";
        String nodos="";
        while(aux != null){
            nodos+="N"+aux.hashCode()+"[label=\"nodo "+aux.cliente_atendido.id_cliente+"\"];\n";
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
        
        String path = "Estructuras\\ListaClientesAtendidos.txt";
        Files.write(Paths.get(path), resultado.getBytes());
        
    }
    
    public void generarJPG(){
        try{
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            
            String fileInputPath ="Estructuras\\ListaClientesAtendidos.txt";
            String fileOutputPath = "Estructuras\\ListaClientesAtendidos.jpg";
            
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
