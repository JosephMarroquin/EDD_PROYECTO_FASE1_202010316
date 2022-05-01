/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import javax.swing.JTextField;

/**
 *
 * @author josep
 */
public class ListaLugares {
    private Nodo inicioCola, finalCola;

    public class Nodo {

        public Lugares dataLugares;
        public Nodo siguiente = null;

        public Nodo(Lugares dataLugares) {
            this.dataLugares = dataLugares;
        }

    }

    public ListaLugares() {
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
    public void insertar(Lugares dataLugares) {
        Nodo nuevo_nodo = new Nodo(dataLugares);
        nuevo_nodo.dataLugares = dataLugares;
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
    public void Mostrar_lugares() {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            System.out.println("-------------------------");
            System.out.println(recorrido.dataLugares.id);
            System.out.println(recorrido.dataLugares.departamento);
            System.out.println(recorrido.dataLugares.nombre);
            System.out.println(recorrido.dataLugares.sn_sucursal);
            recorrido = recorrido.siguiente;
        }
    }


}
