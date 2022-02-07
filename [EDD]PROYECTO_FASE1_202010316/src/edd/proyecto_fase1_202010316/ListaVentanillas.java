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
public class ListaVentanillas {
    
    private Nodo cabecera;
    
    public class Nodo{
        public Ventanillas ventanilla;
        public Nodo next=null;
        public Nodo(Ventanillas _ventanilla){
            this.ventanilla=_ventanilla;
        }
    }
    
}
