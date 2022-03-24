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
public class lista {

    private Nodo cabeza;

    public class Nodo {

        public info cliente_atendido;
        public Nodo next = null;

        public Nodo(info _cliente_atendido) {
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
    public void InsertarClienteAtendido(info _clientes_atendidos) {
        Nodo nuevo_nodo = new Nodo(_clientes_atendidos);
        nuevo_nodo.next = cabeza;
        cabeza = nuevo_nodo;
    }

    //Mostar contenido de la lista
    public void mostrar() {
        Nodo aux = cabeza;
        while (aux != null) {
            System.out.println("-------------------------");
            System.out.println("capa "+ aux.cliente_atendido.id_capa);
            System.out.println("nivel "+aux.cliente_atendido.nivel);
            aux = aux.next;
        }
    }

    public int obtenerMayor() {
        int mayor=0;
        Nodo aux = cabeza;
        if (aux != null) {
            mayor=aux.cliente_atendido.nivel;
            aux = aux.next;
        }
        while(aux!=null){
            if(aux.cliente_atendido.nivel>mayor){
                mayor=aux.cliente_atendido.nivel;
            }
            aux=aux.next;
        }

        return mayor;
    }

}
