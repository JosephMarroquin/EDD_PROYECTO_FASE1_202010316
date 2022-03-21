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
public class ArbolB {

    int orden_arbol = 5;
    RamaB raiz;

    public ArbolB() {
        this.raiz = null;
    }

    public void insertar(long dpi, String nombre, String contraseña) {
        Clientes nodo = new Clientes(dpi, nombre, contraseña);
        if (raiz == null) {
            raiz = new RamaB();
            raiz.insertar(nodo);
        } else {
            Clientes obj = insertar_en_rama(nodo, raiz);
            if (obj != null) {
                //si devuelve algo el metodo de insertar en rama quiere decir que creo una nueva rama, y se debe insertar en el arbol
                raiz = new RamaB();
                raiz.insertar(obj);
                raiz.hoja = false;
            }
        }
    }

    private Clientes insertar_en_rama(Clientes nodo, RamaB rama) {
        if (rama.hoja) {
            rama.insertar(nodo);
            if (rama.contador == orden_arbol) {
                //si ya se insertaron todos los elementos posibles se debe dividir la rama
                return dividir(rama);
            } else {
                return null;
            }
        } else {
            Clientes temp = rama.primero;
            do {
                if (nodo.dpi == temp.dpi) {
                    return null;
                } else if (nodo.dpi < temp.dpi) {
                    Clientes obj = insertar_en_rama(nodo, temp.izquierda);
                    if (obj instanceof Clientes) {
                        rama.insertar((Clientes) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.siguiente == null) {
                    Clientes obj = insertar_en_rama(nodo, temp.derecha);
                    if (obj instanceof Clientes) {
                        rama.insertar((Clientes) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (Clientes) temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    private Clientes dividir(RamaB rama) {
        long val = -9223372036854775808L;
        String v2 = "";
        String v3 = "";
        Clientes temp, Nuevito;
        Clientes aux = rama.primero;
        RamaB rderecha = new RamaB();
        RamaB rizquierda = new RamaB();

        int cont = 0;
        while (aux != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) {
                temp = new Clientes(aux.dpi, aux.nombre, aux.contraseña);
                temp.izquierda = aux.izquierda;
                if (cont == 2) {
                    temp.derecha = aux.siguiente.izquierda;
                } else {
                    temp.derecha = aux.derecha;
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rizquierda.hoja = false;
                }

                rizquierda.insertar(temp);

            } else if (cont == 3) {
                val = aux.dpi;
                v2 = aux.nombre;
                v3 = aux.contraseña;
            } else {
                temp = new Clientes(aux.dpi, aux.nombre, aux.contraseña);
                temp.izquierda = aux.izquierda;
                temp.derecha = aux.derecha;
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rderecha.hoja = false;
                }
                rderecha.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new Clientes(val, v2, v3);
        Nuevito.derecha = rderecha;
        Nuevito.izquierda = rizquierda;
        return Nuevito;
    }

    //Mostrar datos
    public void mostrarArbolB(Clientes tmp) {
        try {
            if (tmp != null) {
                System.out.print(tmp.dpi + " ");
                
                mostrarArbolB(tmp.siguiente);
                
                mostrarArbolB(tmp.izquierda.primero);
                
                mostrarArbolB(tmp.derecha.primero);
                
            }
        } catch (Exception e) {
        }
    }

    //Buscar dpi existente
    public boolean existeDPI(long dpi) {
        try {
            Clientes aux = raiz.primero;
            while (aux != null) {
                if (aux.dpi == dpi) {
                    return true;
                }
                aux = aux.siguiente;
            }
        } catch (Exception e) {
        }
        return false;
    }

    //verificacion de usario para login
    public boolean verificarLogin(long dpi, String contraseña) {
        try {
            Clientes aux = raiz.primero;
            while (aux != null) {
                if (aux.dpi == dpi && aux.contraseña.equals(contraseña)) {
                    //System.out.println("**********************************////////");
                    return true;
                }
                aux = aux.siguiente;
            }
        } catch (Exception e) {
        }
        return false;
    }

    //Mostrar visualmente usuarios registrados en un jTable para el modulo de administrador
    public void mostrarDatosjTable() {
        try {
            Clientes aux = raiz.primero;
            while (aux != null) {
                String dpi = Long.toString(aux.dpi);
                String nombre = aux.nombre;
                String contraseña = aux.contraseña;

                String[] clienteTabla = {dpi, nombre, contraseña};
                moduloADMIN.tblModel.addRow(clienteTabla);

                aux = aux.siguiente;
            }
        } catch (Exception e) {
            System.out.println("No se ha registrado ningun Cliente");
        }
    }

    //Editar el valor de un usuario
    public void editarArbolB(long dpi, String nombre, String contraseña) {
        try {
            Clientes aux = raiz.primero;
            while (aux != null) {

                if (aux.dpi == dpi) {
                    aux.nombre = nombre;
                    aux.contraseña = contraseña;
                }

                aux = aux.siguiente;
            }
        } catch (Exception e) {
            System.out.println("No se ha registrado ningun Cliente");
        }
    }

}
