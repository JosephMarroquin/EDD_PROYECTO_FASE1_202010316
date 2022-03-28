/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010316;

import javax.swing.JTextField;

/**
 *
 * @author josep
 */
public class lista {

    private Nodo inicioCola, finalCola;

    public class Nodo {

        public Clientes clientesEnCola;
        public Nodo siguiente = null;

        public Nodo(Clientes _ClientesEnCola) {
            this.clientesEnCola = _ClientesEnCola;
        }

    }

    public lista() {
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
    public void insertar(Clientes _clientesEnCola) {
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

    //Mostar contenido de la lista
    public void Mostrar_ventanilla() {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            System.out.println("-------------------------");
            System.out.println(recorrido.clientesEnCola.dpi);
            System.out.println(recorrido.clientesEnCola.nombre);
            System.out.println(recorrido.clientesEnCola.contraseña);
            recorrido = recorrido.siguiente;
        }
    }

    public boolean verificarLogin(long dpi, String contraseña) {

        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.dpi == dpi && recorrido.clientesEnCola.contraseña.equals(contraseña)) {
                return true;
            }
            recorrido = recorrido.siguiente;
        }

        return false;
    }

    public boolean existeDPI(long dpi) {

        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.dpi == dpi) {
                return true;
            }
            recorrido = recorrido.siguiente;
        }

        return false;
    }

    public void editarArbolB(long dpi, String nombre, String contraseña) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.dpi == dpi) {
                recorrido.clientesEnCola.nombre = nombre;
                recorrido.clientesEnCola.contraseña = contraseña;
            }
            recorrido = recorrido.siguiente;
        }
    }

    public void buscarDPI(long dpi, JTextField jtextfieldNombre, JTextField jtextfieldContraseña) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.dpi == dpi) {
                jtextfieldNombre.setText(recorrido.clientesEnCola.nombre);
                jtextfieldContraseña.setText(recorrido.clientesEnCola.contraseña);
                //System.out.println();
            }
            recorrido = recorrido.siguiente;
        }
    }


}
