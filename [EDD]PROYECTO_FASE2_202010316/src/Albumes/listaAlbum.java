/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Albumes;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author josep
 */
class Nodo {

    album _album;
    Nodo siguiente, anterior;

    Nodo(album _album) {
        this._album = _album;
    }
}

public class listaAlbum {

    public Nodo primero = null;
    public Nodo ultimo = null;

    //Metodo para ingresar datos a la lista circular doble
    public void IngresarAlbum(album _album) {
        Nodo nuevo_nodo = new Nodo(_album);
        nuevo_nodo._album = _album;
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

    //Metodo para mostrar la lista
    public void MostrarAlbum(long id_cliente) {
        Nodo actual = primero;
        do {
            if (id_cliente == actual._album.id_cliente) {
                System.out.println(actual._album.id_cliente);
                System.out.println(actual._album.nombre);
                System.out.println(actual._album.imgs);
            }

            actual = actual.siguiente;
        } while (actual != primero);
    }

    //Generar el dot
    public String generarDot(long id_cliente) {
        String texto = "digraph G\n"
                + "{\n";

        Nodo temporal = primero;

        if (temporal != null) {
            do {
                if (id_cliente == temporal._album.id_cliente) {
                    texto += "\"" + temporal._album.nombre + "\"" + "->";
                }
                temporal = temporal.siguiente;
            } while (temporal != primero);
        }

        ultimo = primero;
        do {
            if (id_cliente == temporal._album.id_cliente) {
                texto += "\"" + temporal._album.nombre + "\"" + "->";
            }
            temporal = temporal.anterior;
        } while (temporal != ultimo);

        if (id_cliente == temporal._album.id_cliente) {
            texto += "\"" + temporal._album.nombre + "\"";
        }

        texto += "    rankdir=LR;\n"
                + "}";

        return texto;
    }

    //Graficar lista dolbemente enlazada
    public void graficar(long id_cliente) {
        FileWriter fichero = null;
        PrintWriter escritor;
        String dot = "Estructuras\\LC\\lc_" + id_cliente + ".dot";
        String jpg = "Estructuras\\LC\\lc_" + id_cliente + ".jpg";
        try {
            fichero = new FileWriter(dot);
            escritor = new PrintWriter(fichero);
            escritor.print(generarDot(id_cliente));
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo .dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo .dot");
            }
        }
        try {
            String dotPath = "dot";
            String tParam = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = dot;
            cmd[3] = tOParam;
            cmd[4] = jpg;

            Runtime.getRuntime().exec(cmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
