/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

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

}
