/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noobchain;

/**
 *
 * @author josep
 */
public class lista {

    private Nodo inicio, finall;

    public class Nodo {

        public Block bloque;
        public Nodo siguiente = null;

        public Nodo(Block bloque) {
            this.bloque = bloque;
        }

    }

    public lista() {
        inicio = null;
        finall = null;

    }

    //Metodo para saber si la cola esta vacia
    public boolean ColaVacia() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    //Metodo para insertar a la cola
    public void add(Block bloque) {
        Nodo nuevo_nodo = new Nodo(bloque);
        nuevo_nodo.bloque = bloque;
        nuevo_nodo.siguiente = null;

        if (ColaVacia()) {
            inicio = nuevo_nodo;
            finall = nuevo_nodo;
        } else {
            finall.siguiente = nuevo_nodo;
            finall = nuevo_nodo;
        }

    }
    
    //Buscar hash anteriro
    public Block HashAnterior() {
        Nodo recorrido = inicio;
        while (recorrido != null) {
            if (recorrido.siguiente==null) {
                return recorrido.bloque;
            }
            recorrido = recorrido.siguiente;
        }
        return null;
    }

    //JSON
    public String definicionBloque() {
        Nodo recorrido = inicio;
        String data="";
        while (recorrido != null) {
            data+="{\n \"INDEX\":"+recorrido.bloque.index+",\n";
            data+="\"TIMESTAP\":"+recorrido.bloque.timeStamp+",\n";
            data+="\"NONCE\":"+recorrido.bloque.nonce+",\n";
            data+="\"DATA\":"+recorrido.bloque.data+",\n";
            data+="\"PREVIOUSHASH\":"+recorrido.bloque.previousHash+",\n";
            data+="\"HASH\":"+recorrido.bloque.hash+"\n}\n";
            recorrido = recorrido.siguiente;
        }
        return data;
    }
    

}
