/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import edd.proyecto_fase3_202010316.*;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author josep
 */
public class ListaRutas {

    private Nodo inicioCola, finalCola;

    public class Nodo {

        public Rutas dataRutas;
        public Nodo siguiente = null;

        public Nodo(Rutas dataRutas) {
            this.dataRutas = dataRutas;
        }

    }

    public ListaRutas() {
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
    public void insertar(Rutas dataRutas) {
        Nodo nuevo_nodo = new Nodo(dataRutas);
        nuevo_nodo.dataRutas = dataRutas;
        nuevo_nodo.siguiente = null;

        if (ColaVacia()) {
            inicioCola = nuevo_nodo;
            finalCola = nuevo_nodo;
        } else {
            finalCola.siguiente = nuevo_nodo;
            finalCola = nuevo_nodo;
        }

    }

    //Mostar contenido de la lista
    public void Mostrar_Rutas() {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            System.out.println("-------------------------");
            System.out.println(recorrido.dataRutas.inicio);
            System.out.println(recorrido.dataRutas.finall);
            System.out.println(recorrido.dataRutas.peso);
            recorrido = recorrido.siguiente;
        }
    }

    //Agregar conexiones a la lista de adyacencia
    public void conexiones_listAD() {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            admin.miLista.conexion(recorrido.dataRutas.inicio, recorrido.dataRutas.finall);
            recorrido = recorrido.siguiente;
        }
    }

    //Graficar grafo de rutas
    public void generarGrafoRutas() {
        Nodo recorrido = inicioCola;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("Estructuras\\Grafo\\grafo.dot");
            pw = new PrintWriter(fw);
            pw.println("digraph G{ rankdir=LR;");
            //Contenido grafo de rutas
            while (recorrido != null) {
                pw.println(recorrido.dataRutas.inicio+"->"+recorrido.dataRutas.finall+"[label=\""+recorrido.dataRutas.peso+"\"]");
                recorrido = recorrido.siguiente;
            }

            pw.println("}");

            try {
                String doPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
                String fileInputPath = "Estructuras\\Grafo\\grafo.dot";
                String fileOutPath = "Estructuras\\Grafo\\grafo.png";
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
