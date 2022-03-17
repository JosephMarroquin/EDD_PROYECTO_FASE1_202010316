/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010316;

/**
 *
 * @author josep
 */
public class Clientes {

    //datos del cliente
    long dpi;
    String nombre;
    String contraseña;

    //Apuntadores
    Clientes siguiente;
    Clientes anterior;
    RamaB derecha;
    RamaB izquierda;

    public Clientes(long dpi, String nombre, String contraseña) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.contraseña = contraseña;

        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
    }

}
