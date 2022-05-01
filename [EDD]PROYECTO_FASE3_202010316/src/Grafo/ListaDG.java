/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author josep
 */
public class ListaDG {
    //Clase para realizar las conexiones entre los nodos

    private class ENodo {

        int ivex;//posicion del nodo conectado
        ENodo siguiente;//nodo siguiete en la sublista

        //constructor de la clase
        public ENodo(int ivex) {
            this.ivex = ivex;
            this.siguiente = null;
        }
    }

    //Vector de la lista de adyacencia
    private class VNodo {

        String data;//valor del nodo
        ENodo inicio = null;//inicio de la sublista enlazada

        //metodo de insertar en la sublista
        public void insert(int ivex) {
            ENodo nuevo = new ENodo(ivex);
            if (inicio == null) {
                inicio = nuevo;
            } else {
                ENodo aux = inicio;
                while (true) {
                    if (aux.siguiente == null) {
                        aux.siguiente = nuevo;
                        break;
                    }
                    aux = aux.siguiente;
                }
            }
        }

        //imprimir la sublista
        public void imprimir() {
            ENodo aux = inicio;
            while (aux != null) {
                System.out.print("->" + "[" + aux.ivex + "]");
                aux = aux.siguiente;
            }
        }

        //Graficar lista adyacencia
        public String codigoGrapvhiz(String nodoInicial) {
            String contenido = "";
            contenido += "nodo" + nodoInicial + "[label=\"" + nodoInicial + "\"]"+"\n";
            ENodo aux = inicio;
            while (aux != null) {
                contenido += "nodo" + nodoInicial + "_" + aux.ivex + "[label=\"" + aux.ivex + "\"]"+"\n";
                aux = aux.siguiente;
            }
            //conexiones
            ENodo aux2 = inicio;
            contenido += "nodo" + nodoInicial + "->nodo" + nodoInicial + "_" + aux2.ivex+"\n";
            while (aux2 != null) {
                if (aux2.siguiente != null) {
                    contenido += "nodo" + nodoInicial + "_" + aux2.ivex + "->nodo" + nodoInicial + "_" + aux2.siguiente.ivex+"\n";
                }
                aux2 = aux2.siguiente;
            }
            return contenido;
        }

    }

    //estructura principal de los nodos que conforman el grafo
    VNodo v[];

    public ListaDG(int vlen) {
        v = new VNodo[vlen];
        //inicializar el vector de los nodos del grafo
        for (int i = 0; i < v.length; i++) {
            v[i] = new VNodo();
        }
    }

    //insertar nodos en el grafo
    public void insert(String valor, int pos) {
        if (pos >= 0 && pos < v.length) {
            v[pos].data = valor;
            v[pos].inicio = null;
        }
    }

    //insertar conexiones en el grafo
    public void conexion(int inicio, int fin) {
        if (inicio >= 0 && inicio < v.length) {
            v[inicio].insert(fin);
        }
    }

    //imprimir la lsita de adyacencia del grafo
    public void imprimir() {
        for (int i = 0; i < v.length; i++) {
            if (v[i].data == null) {
                continue;
            }
            System.out.print("[" + v[i].data + "]");
            v[i].imprimir();
            System.out.println("");
        }
    }

    //Graficar lista de adyacencia
    public void generarGrafoRutas() {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("Estructuras\\ListaAdyacencia\\listaAD.dot");
            pw = new PrintWriter(fw);
            pw.println("digraph G{ rankdir=LR;");
            pw.println("node [shape= record];");

            //Contenido
            for (int i = 0; i < v.length; i++) {
                if (v[i].data == null) {
                    continue;
                }
                pw.println(v[i].codigoGrapvhiz(v[i].data));
            }

            pw.println("}");

            try {
                String doPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
                String fileInputPath = "Estructuras\\ListaAdyacencia\\listaAD.dot";
                String fileOutPath = "Estructuras\\ListaAdyacencia\\listaAD.png";
                String tParam = "-Tpng";
                String toParam = "-o";
                String[] cmd = new String[5];
                cmd[0] = doPath;
                cmd[1] = tParam;
                cmd[2] = fileInputPath;
                cmd[3] = toParam;
                cmd[4] = fileOutPath;
                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
                //rt.exec("HashTable.png");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
