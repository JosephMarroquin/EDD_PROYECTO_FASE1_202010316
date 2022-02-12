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
public class ImagenPorVentana {
    
    int numVentana;
    PilaImg pilaImagen;
    
    public ImagenPorVentana(int _numVentana){
        this.numVentana=_numVentana;
        this.pilaImagen=new PilaImg();
    }
    
}
