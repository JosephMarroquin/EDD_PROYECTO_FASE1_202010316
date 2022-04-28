/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase3_202010316;

/**
 *
 * @author josep
 */
public class Clientes {
    long dpi;
    String nombre;
    String usuario;
    String correo;
    String contraseña;
    String telefono;
    String direccion;
    int id_municipio;

    public Clientes(long dpi, String nombre, String usuario, String correo, String contraseña, String telefono, String direccion, int id_municipio) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_municipio = id_municipio;
    }
}
