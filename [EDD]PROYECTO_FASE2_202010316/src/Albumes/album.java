/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Albumes;

/**
 *
 * @author josep
 */
public class album {
    long id_cliente;
    String nombre;
    String imgs;
    
    public album(long id_cliente, String nombre, String imgs){
        this.id_cliente=id_cliente;
        this.nombre=nombre;
        this.imgs=imgs; 
    }
    
}
