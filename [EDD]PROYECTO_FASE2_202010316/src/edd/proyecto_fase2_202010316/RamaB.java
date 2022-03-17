/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010316;

/**
 *
 * @author josep
 */
public class RamaB {

    boolean hoja;//identificar si es una hoja
    int contador;//identificar la cantidad de elementos que tiene la rama
    Clientes primero;

    public RamaB() {
        this.primero = null;
        this.hoja = true;
        this.contador = 0;
    }

    public void insertar(Clientes nuevo) {
        if (primero == null) {
            //primero en la lista
            primero = nuevo;
            contador++;
        } else {
            //recorrer e insertar
            Clientes aux = primero;
            while (aux != null) {
                if (aux.dpi == nuevo.dpi) {//------------->ya existe en el arbol
                    System.out.println("El DPI " + nuevo.dpi + " ya existe");
                    break;
                } else {
                    if (aux.dpi > nuevo.dpi) {
                        if (aux == primero) {//------------->insertar al inicio
                            aux.anterior = nuevo;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            primero = nuevo;
                            contador++;
                            break;
                        } else {//------------->insertar en medio;
                            nuevo.siguiente = aux;
                            //ramas del nodo
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            nuevo.anterior = aux.anterior;
                            aux.anterior.siguiente = nuevo;
                            aux.anterior = nuevo;
                            contador++;
                            break;
                        }
                    } else if (aux.siguiente == null) {//------------->insertar al final
                        aux.siguiente = nuevo;
                        nuevo.anterior = aux;
                        contador++;
                        break;
                    }
                }
                aux = aux.siguiente;
            }

        }
    }

}
