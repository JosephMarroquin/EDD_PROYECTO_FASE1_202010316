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
public class ClientesAtendidos {
    
    int id_cliente;
    String nombre_cliente;
    int ventanilla_atendida;
    int imagenes_impresasColor;
    int imagenes_impresasBw;
    int total_pasos;
    
    public ClientesAtendidos(int _id_cliente, String _nombre_cliente, int _ventanilla_atendida, int _imagenes_impresasColor, int _imagenes_impresasBw, int _total_pasos){
        this.id_cliente=_id_cliente;
        this.nombre_cliente=_nombre_cliente;
        this.ventanilla_atendida=_ventanilla_atendida;
        this.imagenes_impresasColor=_imagenes_impresasColor;
        this.imagenes_impresasBw=_imagenes_impresasBw;
        this.total_pasos=_total_pasos;
    }
    
}
