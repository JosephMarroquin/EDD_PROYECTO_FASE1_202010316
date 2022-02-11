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
public class ClientesEspera {
    
    String encabezado;
    int id_cliente;
    String nombre_cliente;
    int ventanilla_atentida;
    ListaImgPendiente ListaImgPendiente;
    
    public ClientesEspera(String _encabezado,int _id_cliente, String _nombre_cliente, int _ventanilla_atendida){
        this.encabezado=_encabezado;
        this.id_cliente=_id_cliente;
        this.nombre_cliente=_nombre_cliente;
        this.ventanilla_atentida=_ventanilla_atendida;
        this.ListaImgPendiente=new ListaImgPendiente();
    }
    
}
