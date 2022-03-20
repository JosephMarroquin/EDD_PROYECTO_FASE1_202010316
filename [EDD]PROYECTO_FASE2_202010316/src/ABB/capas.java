/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABB;

/**
 *
 * @author josep
 */
public class capas {
    long id_cliente;
    int id_capa;
    int fila;
    int columna;
    String color;
    
    public capas(long id_cliente,int id_capa,int fila, int columna, String color){
        this.id_cliente=id_cliente;
        this.id_capa=id_capa;
        this.fila=fila;
        this.columna=columna;
        this.color=color;
    }
    
}
