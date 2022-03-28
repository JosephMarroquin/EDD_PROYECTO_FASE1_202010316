/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josep
 */
public class listaTop {

    private Nodo cabeza;

    public class Nodo {

        public imagen cliente_atendido;
        public Nodo next = null;

        public Nodo(imagen _cliente_atendido) {
            this.cliente_atendido = _cliente_atendido;
        }
    }

    //Metodo para saber si la lista esta vacia
    public boolean ListaVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
        }
    }

    //INGRESO DE DATOS
    public void InsertarClienteAtendido(imagen _clientes_atendidos) {
        Nodo nuevo_nodo = new Nodo(_clientes_atendidos);
        nuevo_nodo.next = cabeza;
        cabeza = nuevo_nodo;
    }

    //Ordenar de mayor a menor segun imagenes a color
    public void ordenar() {
        int t = 1, c = 1;
        Nodo act = cabeza;/*definimos que el apuntador act esta en el primer nodo*/
        while (act.next != null)//Este while cuenta el total de nodos.
        {
            act = act.next;
            c++;
        }
        /*guardaran el valor temporalmente*/
        imagen clienAten = new imagen(0, 0, "");
        //aqui se hace el ordenamiento
        do {
            act = cabeza;//aux esta en el primer nodo
            Nodo sig = act.next;//esta en el siguiente nodo 
            while (act.next != null) {
                if (act.cliente_atendido.capa.length() < sig.cliente_atendido.capa.length()) {
                    clienAten = act.cliente_atendido;
                    act.cliente_atendido = sig.cliente_atendido;
                    sig.cliente_atendido = clienAten;
                    act = act.next;
                    sig = sig.next;
                } else {
                    act = act.next;
                    sig = sig.next;
                }
            }
            t++;
        } while (t <= c);
    }

    //Mostrar en consola el top 5 clientes ImgColor
    public void MostrarTop5ImgColor(DefaultTableModel tblModel, long id_cliente) {
        Nodo aux = cabeza;
        int cont = 0;
        while (aux != null) {
            if (aux.cliente_atendido.id_cliente == id_cliente) {
                cont = cont + 1;
                String idimg = String.valueOf(aux.cliente_atendido.id_imagen);
                String[] clienteTabla = {idimg};
                tblModel.addRow(clienteTabla);
            }
            if (cont == 5) {
                break;
            }
            aux = aux.next;
        }
    }
}
