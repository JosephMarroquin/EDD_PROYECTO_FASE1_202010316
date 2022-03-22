/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matriz;

/**
 *
 * @author josep
 */
class nodo {

    int x;
    int y;
    Object valor;

    nodo derecho, izquierdo;
    nodo arriba, abajo;
    nodo siguiente, anterior;

    nodo(Object valor, int x, int y) {
        this.valor = valor;
        this.x = x;
        this.y = y;
        derecho = izquierdo = arriba = abajo = null;
        siguiente = anterior = null;
    }

    nodo(Object valor) {
        this.valor = valor;
        x = 0;
        y = 0;
        derecho = izquierdo = arriba = abajo = null;
        siguiente = anterior = null;
    }
}

class lista {

    nodo raiz, ultimo;

    public lista() {
        raiz = ultimo = null;
    }

    public void insertar(int valor) {
        nodo nuevo = new nodo(valor);
        if (raiz == null) {
            raiz = ultimo = nuevo;
        } else {
            ordenar(nuevo);
        }
    }

    public void ordenar(nodo nodo) {
        nodo aux = raiz;
        while (aux != null) {
            if ((int) aux.valor < (int) nodo.valor) {
                aux = aux.siguiente;
            } else {
                if (aux == raiz) {
                    nodo.siguiente = aux;
                    aux.anterior = nodo;
                    raiz = nodo;
                    return;
                } else {
                    nodo.anterior = aux.anterior;
                    aux.anterior.siguiente = nodo;
                    nodo.siguiente = aux;
                    aux.anterior = nodo;
                    return;
                }
            }
            ultimo.siguiente = nodo;
            nodo.anterior = ultimo;
            ultimo = nodo;
        }
    }

    public nodo search(int valor) {
        nodo temp = raiz;
        while (temp != null) {
            if ((int) temp.valor == valor) {
                return temp;
            }
            temp = temp.siguiente;

        }
        return null;
    }

    public void print() {
        nodo temp = raiz;
        while (temp != null) {
            System.out.println("Cabecera: " + temp.valor);
            temp = temp.siguiente;
        }
    }

}

public class Matriz {

    public lista lista_horizontal = new lista();
    public lista lista_vertical = new lista();

    public Matriz() {
        lista_horizontal = new lista();
        lista_vertical = new lista();
    }

    public void insertar(String valor, int x, int y) {
        nodo horizontal = lista_horizontal.search(x);
        nodo vertical = lista_vertical.search(y);

        if (horizontal == null && vertical == null) {
            caso1(valor, x, y);
        } else if (horizontal == null && vertical != null) {
            caso2(valor, x, y);
        } else if (horizontal != null && vertical == null) {
            caso3(valor, x, y);
        } else {
            caso4(valor, x, y);
        }

    }

    public void caso1(String valor, int x, int y) {
        lista_horizontal.insertar(x);
        lista_vertical.insertar(y);

        nodo horizontal = lista_horizontal.search(x);
        nodo vertical = lista_vertical.search(y);

        nodo nuevo = new nodo(valor, x, y);
        horizontal.abajo = nuevo;
        nuevo.arriba = horizontal;

        vertical.derecho = nuevo;
        nuevo.izquierdo = vertical;

    }

    public void caso2(String valor, int x, int y) {
        lista_horizontal.insertar(x);

        nodo horizontal = lista_horizontal.search(x);
        nodo vertical = lista_vertical.search(y);

        boolean agregado = false;

        nodo nuevo = new nodo(valor, x, y);

        nodo aux = vertical.derecho;
        int cabecera = 0;

        while (aux != null) {
            cabecera = aux.x;
            if (cabecera < x) {
                aux = aux.derecho;
            } else {
                nuevo.derecho = aux;
                nuevo.izquierdo = aux.izquierdo;
                aux.izquierdo.derecho = nuevo;
                aux.izquierdo = nuevo;
                agregado = true;
                break;
            }
        }
        if (agregado == false) {
            aux = vertical.derecho;
            while (aux.derecho != null) {
                aux = aux.derecho;
            }
            nuevo.izquierdo = aux;
            aux.derecho = nuevo;
        }
        horizontal.abajo = nuevo;
        nuevo.arriba = horizontal;

    }

    public void caso3(String valor, int x, int y) {
        lista_vertical.insertar(y);

        nodo horizontal = lista_horizontal.search(x);
        nodo vertical = lista_vertical.search(y);

        boolean agregado = false;

        nodo nuevo = new nodo(valor, x, y);
        nodo aux = horizontal.abajo;
        int cabecera = 0;

        while (aux != null) {
            cabecera = aux.y;
            if (cabecera < y) {
                aux = aux.abajo;
            } else {
                nuevo.abajo = aux;
                nuevo.arriba = aux.arriba;
                aux.arriba.abajo = nuevo;
                aux.arriba = nuevo;
                agregado = true;
                break;
            }
        }
        if (agregado == false) {
            aux = horizontal.abajo;
            while (aux.abajo != null) {
                aux = aux.abajo;
            }
            nuevo.arriba = aux;
            aux.abajo = nuevo;
        }
        vertical.derecho = nuevo;
        nuevo.izquierdo = vertical;

    }

    public void caso4(String valor, int x, int y) {
        nodo vertical = lista_vertical.search(y);
        nodo horizontal = lista_horizontal.search(x);

        nodo nuevo = new nodo(valor, x, y);
        boolean agregado = false;

        nodo aux = horizontal.abajo;

        int cabecera = 0;

        while (aux != null) {
            cabecera = aux.y;
            if (cabecera < y) {
                aux = aux.abajo;
            } else {
                nuevo.abajo = aux;
                nuevo.arriba = aux.arriba;
                aux.arriba.abajo = nuevo;
                aux.arriba = nuevo;
                agregado = true;
                break;
            }
        }

        if (agregado == false) {
            aux = horizontal.abajo;
            while (aux.abajo != null) {
                aux = aux.abajo;
            }

            nuevo.arriba = aux;
            aux.abajo = nuevo;
        }

        agregado = false;
        aux = vertical.derecho;
        cabecera = 0;

        while (aux != null) {
            cabecera = aux.x;
            if (cabecera < x) {
                aux = aux.derecho;
            } else {
                nuevo.derecho = aux;
                nuevo.izquierdo = aux.izquierdo;
                aux.izquierdo.derecho = nuevo;
                aux.izquierdo = nuevo;
                agregado = true;
                break;
            }
        }

        if (agregado == false) {
            aux = vertical.derecho;
            while (aux.derecho != null) {
                aux = aux.derecho;
            }
            nuevo.izquierdo = aux;
            aux.derecho = nuevo;
        }

    }

    public void imprimir_horizontal() {
        nodo cabecera = lista_horizontal.raiz;
        while (cabecera != null) {
            nodo aux = cabecera.abajo;
            while (aux != null) {
                System.out.println(aux.valor + " " + aux.x + " " + aux.y);
                aux = aux.abajo;
            }
            cabecera = cabecera.siguiente;
        }
    }

    public void imp() {
        lista_horizontal.print();
        lista_vertical.print();
    }

}
