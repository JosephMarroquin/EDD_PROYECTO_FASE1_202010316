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
    
    String nombre_cliente;
    int ventanilla_atendida;
    int imagenes_impresas;
    int total_pasos;
    
    public ClientesAtendidos(String _nombre_cliente, int _ventanilla_atendida, int _imagenes_impresas, int _total_pasos){
        this.nombre_cliente=_nombre_cliente;
        this.ventanilla_atendida=_ventanilla_atendida;
        this.imagenes_impresas=_imagenes_impresas;
        this.total_pasos=_total_pasos;
    }
    
}
