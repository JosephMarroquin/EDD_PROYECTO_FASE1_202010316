/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase1_202010316;

/**
 *
 * @author josep
 */
public class Clientes {
    
    String encabezado;
    int id_cliente;
    String nombre_cliente;
    int img_color;
    int img_bw;
    
    public Clientes(String _encabezado,int _id_cliente, String _nombre_cliente, int _img_color, int _img_bw){
        this.encabezado=_encabezado;
        this.id_cliente=_id_cliente;
        this.nombre_cliente=_nombre_cliente;
        this.img_color=_img_color;
        this.img_bw=_img_bw;
    }
    
}
