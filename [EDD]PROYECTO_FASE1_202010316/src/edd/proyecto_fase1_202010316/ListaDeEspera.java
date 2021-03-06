/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase1_202010316;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author josep
 */
public class ListaDeEspera {

    public class Nodo {

        public ClientesEspera clienteEspera;
        public Nodo siguiente, anterior;

        public Nodo(ClientesEspera _clienteEspera) {
            this.clienteEspera = _clienteEspera;
        }
    }

    Nodo primero, ultimo;

    public ListaDeEspera() {
        primero = null;
        ultimo = null;
    }

    //Metodo para ingresar datos a la lista circular doble
    public void IngresarListaEspera(ClientesEspera _clienteEspera) {
        Nodo nuevo_nodo = new Nodo(_clienteEspera);
        nuevo_nodo.clienteEspera = _clienteEspera;
        if (primero == null) {
            primero = nuevo_nodo;
            primero.siguiente = primero;
            nuevo_nodo.anterior = ultimo;
            ultimo = nuevo_nodo;
        } else {
            ultimo.siguiente = nuevo_nodo;
            nuevo_nodo.siguiente = primero;
            nuevo_nodo.anterior = ultimo;
            ultimo = nuevo_nodo;
            primero.anterior = ultimo;
        }
    }

    //Metodo para mostrar la lista de espera
    public void MostrarListaEspera() {
        Nodo actual = primero;
        do {
            System.out.println(actual.clienteEspera.encabezado);
            System.out.println(actual.clienteEspera.id_cliente);
            System.out.println(actual.clienteEspera.nombre_cliente);
            System.out.println(actual.clienteEspera.ventanilla_atentida);
            actual = actual.siguiente;
        } while (actual != primero);
    }

    //Buscar por id del cliente
    public ClientesEspera BuscarIdClienteEspera(int idClienteEspera) {
        Nodo actual = primero;
        do {
            if (actual.clienteEspera.id_cliente == idClienteEspera) {
                return actual.clienteEspera;
            }
            actual = actual.siguiente;
        } while (actual != primero);
        return null;
    }

    //Eliminar nodo de la lista de espera
    public void EliminarListaEspera(ClientesEspera _clienteEspera) {
        Nodo actual = new Nodo(_clienteEspera);
        Nodo anterior = new Nodo(_clienteEspera);
        actual = primero;
        anterior = ultimo;
        do {
            if (actual.clienteEspera == _clienteEspera) {
                if (actual == primero) {
                    primero = primero.siguiente;
                    ultimo.siguiente = primero;
                    primero.anterior = ultimo;
                } else if (actual == ultimo) {
                    ultimo = anterior;
                    primero.anterior = ultimo;
                    ultimo.siguiente = primero;
                } else {
                    anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = anterior;
                }
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != primero);
    }

    //Verificar si el cliente ya tiene todas sus imagenes
    public void VerificacionImagenes(ColaImpresion cola_impresion, ColaImpresionBw cola_impresionBw, ListaClientesAtendidos lista_clienteAtendido, ListaDeEspera lista_espera) {
        if (primero != null) {
            Nodo actual = primero;
            do {
                String impresoraColor = cola_impresion.BuscarCliente(actual.clienteEspera.id_cliente);
                String impresoraBw = cola_impresionBw.BuscarClienteBw(actual.clienteEspera.id_cliente);
                if (impresoraColor != "si" && impresoraBw != "si") {

                    int contadorImagenes = actual.clienteEspera.ListaImgPendiente.ContarImagenes();//Saber el total de imagenes del cliente
                    int contImgColor = actual.clienteEspera.ListaImgPendiente.ContarImagenesColor();//Saber total de imagenes a color
                    int contImgBw = actual.clienteEspera.ListaImgPendiente.ContarImagenesBw();//Saber total de imagenes en blanco y negro

                    int totalPasos = 5 + contadorImagenes + (2 * contImgColor) + contImgBw;//Saber el total de pasos del cliente en el sistema

                    //Agregar cliente a lista de clientes atendidos
                    ClientesAtendidos clienteAtendido = new ClientesAtendidos(actual.clienteEspera.id_cliente, actual.clienteEspera.nombre_cliente, actual.clienteEspera.ventanilla_atentida, contImgColor, contImgBw, totalPasos);
                    lista_clienteAtendido.InsertarClienteAtendido(clienteAtendido);

                    System.out.println("El " + actual.clienteEspera.encabezado + " ya posee todas sus im??genes impresas y sale de la empresa registrando el tiempo total dentro de ella");

                    //Eliminar cliente de lista de espera
                    lista_espera.EliminarListaEspera(actual.clienteEspera);

                }

                actual = actual.siguiente;
            } while (actual != primero);
        }
    }

    //Generar el dot
    public String generarDot() {
        String texto = "digraph G\n"
                + "{\n"
                + "  ListaDeEspera [shape=box]\n"
                + "  ListaDeEspera [style=filled]\n"
                + "  ListaDeEspera [fillcolor=\"#EEEEEEE\"]\n"
                + "  ListaDeEspera [color=\"#4EA06D\"]\n"
                + "  edge [color=\"#00FF00\"]\n";

        Nodo temporal = primero;

        if (temporal != null) {
            do {
                texto += temporal.clienteEspera.encabezado + "->";
                temporal = temporal.siguiente;
            } while (temporal != primero);
        }

        ultimo = primero;
        do {
            texto += temporal.clienteEspera.encabezado + "->";
            temporal = temporal.anterior;
        } while (temporal != ultimo);
        texto += temporal.clienteEspera.encabezado;

        texto += "    rankdir=LR;\n"
                + "}";

        return texto;
    }

    public void escribirArchivo(String contenido) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("Estructuras\\ListaDeEspera.txt");
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

    }

    //Generar jog
    public void dibujarGrapvhiz() {
        if (primero != null) {
            try {
                escribirArchivo(generarDot());
                
                String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

                String fileInputPath = "Estructuras\\ListaDeEspera.txt";
                String fileOutputPath = "Estructuras\\ListaDeEspera.jpg";

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

}
