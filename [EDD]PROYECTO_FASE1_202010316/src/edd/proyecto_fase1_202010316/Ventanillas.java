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
public class Ventanillas {
    
    int nVentanilla;
    int id_cliente;
    String estado;
    
    public Ventanillas(int _nVentanilla, int _id_cliente){
        this.nVentanilla=_nVentanilla;
        this.id_cliente=_id_cliente;
        this.estado="Disponible";
    }
    
}
