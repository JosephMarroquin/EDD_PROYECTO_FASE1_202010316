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
public class ColaRecepcionVerdad {

    //
    public static String encabezadoCliente;
    public static int idDelCliente;
    //

    private Nodo inicioCola, finalCola;
    String Cola = "";

    public class Nodo {

        public ClientesEnCola clientesEnCola;
        public Nodo siguiente = null;

        public Nodo(ClientesEnCola _ClientesEnCola) {
            this.clientesEnCola = _ClientesEnCola;
        }

    }

    public ColaRecepcionVerdad() {
        inicioCola = null;
        finalCola = null;

    }

    //Metodo para saber si la cola esta vacia
    public boolean ColaVacia() {
        if (inicioCola == null) {
            return true;
        } else {
            return false;
        }
    }

    //Metodo para insertar a la cola
    public void Insertar(ClientesEnCola _clientesEnCola) {
        Nodo nuevo_nodo = new Nodo(_clientesEnCola);
        nuevo_nodo.clientesEnCola = _clientesEnCola;
        nuevo_nodo.siguiente = null;

        if (ColaVacia()) {
            inicioCola = nuevo_nodo;
            finalCola = nuevo_nodo;
        } else {
            finalCola.siguiente = nuevo_nodo;
            finalCola = nuevo_nodo;
        }

    }

    //Metodo para extraer de la cola
    public ClientesEnCola Extraer() {
        if (!ColaVacia()) {
            ClientesEnCola informacion = inicioCola.clientesEnCola;
            if (inicioCola == finalCola) {
                inicioCola = null;
                finalCola = null;
            } else {
                inicioCola = inicioCola.siguiente;
            }
            return informacion;
        } else {
            return null;
        }
    }

    

    //Buscar un nodo por el id
    public ClientesEnCola BuscarNodoxId(int idCliente) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idCliente) {
                return recorrido.clientesEnCola;
            }
            recorrido = recorrido.siguiente;
        }
        return null;
    }

    //Metodo para mostrar el contenido de la cola
    public void MostrarContenido() {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            System.out.println("-------------------------");
            System.out.println(recorrido.clientesEnCola.encabezado);
            System.out.println(recorrido.clientesEnCola.id_cliente);
            System.out.println(recorrido.clientesEnCola.nombre_cliente);
            System.out.println(recorrido.clientesEnCola.img_color);
            System.out.println(recorrido.clientesEnCola.img_bw);
            recorrido = recorrido.siguiente;
        }
    }

    //Saber que cliente estoy atendiendo
    public void MostrarEncabezadoCliente(ListaVentanillas lista_ventanillas) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.atendiendo == "no") {
                if (lista_ventanillas.HayVentanasDisp() == "si") {
                    encabezadoCliente = recorrido.clientesEnCola.encabezado;
                    idDelCliente = recorrido.clientesEnCola.id_cliente;
                    recorrido.clientesEnCola.atendiendo = "si";
                    break;
                }
            } else {
                encabezadoCliente = "";
                idDelCliente = 0;
            }

            recorrido = recorrido.siguiente;
        }
    }

    //Buscar id e informar cuantas imagenes a color tiene el cliente
    public int CantidadImgColorRecepcion(int idClienteRecepcion) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idClienteRecepcion) {
                return recorrido.clientesEnCola.img_color;
            }

            recorrido = recorrido.siguiente;
        }
        return Integer.MAX_VALUE;
    }

    //Buscar id e informar cuantas imagenes a blanco y negro tiene el cliente
    public int CantidadImgBwRecepcion(int idClienteRecepcion) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idClienteRecepcion) {
                return recorrido.clientesEnCola.img_bw;
            }

            recorrido = recorrido.siguiente;
        }
        return Integer.MAX_VALUE;
    }

    //Buscar id e informar encabezado
    public String EncabezadoParaImg(int idClienteRecepcion) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idClienteRecepcion) {
                return recorrido.clientesEnCola.encabezado;
            }

            recorrido = recorrido.siguiente;
        }
        return null;
    }

    //Buscar id e informar nombre del cliente
    public String NombreParaImg(int idClienteRecepcion) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idClienteRecepcion) {
                return recorrido.clientesEnCola.nombre_cliente;
            }

            recorrido = recorrido.siguiente;
        }
        return null;
    }

    //Quitarle una imagen a color al cliente
    public void QuitarImagenColor(int idClienteRecepcion) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idClienteRecepcion) {
                if (recorrido.clientesEnCola.img_color != 0) {
                    recorrido.clientesEnCola.img_color = recorrido.clientesEnCola.img_color - 1;
                    break;
                }
            }
            recorrido = recorrido.siguiente;
        }
    }

    //Quitarle una imagen blanco y negro al cliente
    public void QuitarImagenBw(int idClienteRecepcion) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {

            if (recorrido.clientesEnCola.id_cliente == idClienteRecepcion) {
                if (recorrido.clientesEnCola.img_bw != 0) {
                    recorrido.clientesEnCola.img_bw = recorrido.clientesEnCola.img_bw - 1;
                    break;
                }
            }
            recorrido = recorrido.siguiente;
        }
    }

    //Metodo para graficar en graphviz
    public void generarDot() throws IOException {
        String resultado = "digraph G{\nlabel=\"" + "Cola Recepcion" + "\";\nnode [shape=box];\n";
        Nodo aux = inicioCola;
        String conexiones = "";
        String nodos = "";
        while (aux != null) {
            nodos += "N" + aux.hashCode() + "[label=\"nodo " + aux.clientesEnCola.nombre_cliente + "\"];\n";
            if (aux.siguiente != null) {
                conexiones += "N" + aux.hashCode() + " -> " + "N" + aux.siguiente.hashCode() + ";\n";
            }
            aux = aux.siguiente;
        }
        resultado += "//Agregando nodods\n";
        resultado += nodos + "\n";
        resultado += "//Agregando conexiones\n";
        resultado += "{rank= same;\n" + conexiones + "\n";

        resultado += "}\n}";

        String path = "Estructuras\\ColaRecepcion.txt";
        Files.write(Paths.get(path), resultado.getBytes());

    }

    public void generarJPG() {
        try {
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

            String fileInputPath = "Estructuras\\ColaRecepcion.txt";
            String fileOutputPath = "Estructuras\\ColaRecepcion.jpg";

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

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }
}
