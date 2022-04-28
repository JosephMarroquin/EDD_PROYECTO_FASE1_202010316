/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase3_202010316;

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

    public boolean verificarLogin(String usuario, String contraseña) {

        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.usuario.equals(usuario)&& recorrido.clientesEnCola.contraseña.equals(contraseña)) {
                return true;
            }
            recorrido = recorrido.siguiente;
        }

        return false;
    }

    public boolean existeUsuario(String usuario) {

        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.usuario.equals(usuario)) {
                return true;
            }
            recorrido = recorrido.siguiente;
        }

        return false;
    }

    public void editarCliente(long dpi, String nombre, String usuario, String correo, String contraseña, String telefono, String direccion, int id_municipio) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.usuario.equals(usuario)) {
                recorrido.clientesEnCola.dpi = dpi;
                recorrido.clientesEnCola.nombre = nombre;
                recorrido.clientesEnCola.correo = correo;
                recorrido.clientesEnCola.contraseña = contraseña;
                recorrido.clientesEnCola.telefono = telefono;
                recorrido.clientesEnCola.direccion = direccion;
                recorrido.clientesEnCola.id_municipio = id_municipio;
            }
            recorrido = recorrido.siguiente;
        }
    }

    public void buscarUsuario(JTextField dpi, JTextField nombre, String usuario, JTextField correo, JTextField contraseña, JTextField telefono, JTextField direccion, JTextField id_municipio) {
        Nodo recorrido = inicioCola;
        while (recorrido != null) {
            if (recorrido.clientesEnCola.usuario.equals(usuario)) {
                dpi.setText(Long.toString(recorrido.clientesEnCola.dpi));
                nombre.setText(recorrido.clientesEnCola.nombre);
                correo.setText(recorrido.clientesEnCola.correo);
                contraseña.setText(recorrido.clientesEnCola.contraseña);
                telefono.setText(recorrido.clientesEnCola.telefono);
                direccion.setText(recorrido.clientesEnCola.direccion);
                id_municipio.setText(String.valueOf(recorrido.clientesEnCola.id_municipio));
                //System.out.println();
            }
            recorrido = recorrido.siguiente;
        }
    }
}
