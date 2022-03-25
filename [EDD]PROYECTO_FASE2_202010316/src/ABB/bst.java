/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABB;

import java.io.FileWriter;
import java.io.PrintWriter;
import Matriz.*;
import javax.swing.JTextField;

/**
 *
 * @author josep
 */
class Node {

    private static int correlativo = 1;
    public final int id;

    capas _capas;
    Node left;
    Node right;

    Node(capas _capas) {
        this._capas = _capas;
        left = null;
        right = null;
        this.id = correlativo++;
    }

}

public class bst {

    public Node root = null;

    public void add(capas _capas) {
        if (root != null) {
            add(_capas, root);
        } else {
            root = new Node(_capas);
        }
    }

    void add(capas _capas, Node tmp) {
        if (_capas.id_capa < tmp._capas.id_capa) {
            if (tmp.left != null) {
                add(_capas, tmp.left);
            } else {
                tmp.left = new Node(_capas);
            }
        } else {
            if (tmp.right != null) {
                add(_capas, tmp.right);
            } else {
                tmp.right = new Node(_capas);
            }
        }
    }

    public static String contenido = "";

    public void preorder(Node tmp, MatrizDispersa matriz, long id_cliente) {
        if (tmp != null) {
            if (tmp._capas.id_cliente == id_cliente) {
                matriz.insertar(tmp._capas.columna, tmp._capas.fila, tmp._capas.color);
            }
            //System.out.print(tmp._capas.id_capa + " ");
            preorder(tmp.left, matriz, id_cliente);
            preorder(tmp.right, matriz, id_cliente);
        }
    }

    public void preordenJtext(Node tmp, JTextField jtextfield, long id_cliente) {
        if (tmp != null) {
            if (tmp._capas.id_cliente == id_cliente) {
                contenido = contenido + tmp._capas.id_capa + " ";
                jtextfield.setText(contenido);
            }
            //System.out.print(tmp._capas.id_capa + " ");
            preordenJtext(tmp.left, jtextfield, id_cliente);
            preordenJtext(tmp.right, jtextfield, id_cliente);
        }
    }

    public void enorder(Node tmp, MatrizDispersa matriz, long id_cliente) {
        if (tmp != null) {
            enorder(tmp.left, matriz, id_cliente);
            if (tmp._capas.id_cliente == id_cliente) {
                matriz.insertar(tmp._capas.columna, tmp._capas.fila, tmp._capas.color);
            }
            //System.out.print(tmp._capas.id_capa + " ");
            enorder(tmp.right, matriz, id_cliente);
        }
    }

    public void enorderJtext(Node tmp, JTextField jtextfield, long id_cliente) {
        if (tmp != null) {
            enorderJtext(tmp.left, jtextfield, id_cliente);
            if (tmp._capas.id_cliente == id_cliente) {
                contenido = contenido + tmp._capas.id_capa + " ";
                jtextfield.setText(contenido);
            }
            enorderJtext(tmp.right, jtextfield, id_cliente);
        }
    }

    public void postorder(Node tmp, MatrizDispersa matriz, long id_cliente) {
        if (tmp != null) {
            postorder(tmp.left, matriz, id_cliente);
            postorder(tmp.right, matriz, id_cliente);
            if (tmp._capas.id_cliente == id_cliente) {
                matriz.insertar(tmp._capas.columna, tmp._capas.fila, tmp._capas.color);
            }

            //System.out.print(tmp._capas.id_capa + " ");
        }
    }

    public void postorderJtext(Node tmp, JTextField jtextfield, long id_cliente) {
        if (tmp != null) {
            postorderJtext(tmp.left, jtextfield, id_cliente);
            postorderJtext(tmp.right, jtextfield, id_cliente);
            if (tmp._capas.id_cliente == id_cliente) {
                contenido = contenido + tmp._capas.id_capa + " ";
                jtextfield.setText(contenido);
            }
        }
    }

    //Graficar el abb
    public void graficar(Node tmp) {
        FileWriter fichero = null;
        PrintWriter escritor;
        String dot = "Estructuras\\ABB\\abb_" + tmp._capas.id_cliente + ".dot";
        String jpg = "Estructuras\\ABB\\abb_" + tmp._capas.id_cliente + ".jpg";
        try {
            fichero = new FileWriter(dot);
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz(tmp));
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo .dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo .dot");
            }
        }
        try {
            String dotPath = "dot";
            String tParam = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = dot;
            cmd[3] = tOParam;
            cmd[4] = jpg;

            Runtime.getRuntime().exec(cmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getCodigoGraphviz(Node tmp) {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = circle, style=filled, fillcolor=seashell2];\n"
                + getCodigoInterno(tmp)
                + "}\n";
    }

    public String getCodigoInterno(Node tmp) {
        String etiqueta;
        if (tmp.left == null && tmp.right == null) {
            etiqueta = "nodo" + tmp.id + " [ label =\"" + tmp._capas.id_capa + "\"];\n";
        } else {
            etiqueta = "nodo" + tmp.id + " [ label =\"" + tmp._capas.id_capa + "\"];\n";
        }
        if (tmp.left != null) {
            etiqueta = etiqueta + getCodigoInterno(tmp.left)
                    + "nodo" + tmp.id + "->nodo" + tmp.left.id + "\n";
        }
        if (tmp.right != null) {
            etiqueta = etiqueta + getCodigoInterno(tmp.right)
                    + "nodo" + tmp.id + "->nodo" + tmp.right.id + "\n";
        }
        return etiqueta;
    }

    int nivel = -1;

    public void imprimirNiveles(Node tmp, lista list) {
        if (tmp.left == null && tmp.right == null) {

            nivel = nivel + 1;
            //System.out.println("nivel " + nivel + " id: " + tmp._capas.id_capa);
            info inf = new info(tmp._capas.id_capa, nivel);
            list.InsertarClienteAtendido(inf);

        } else {
            nivel = nivel + 1;
            //System.out.println("nivel " + nivel + " id: " + tmp._capas.id_capa);
            info inf = new info(tmp._capas.id_capa, nivel);
            list.InsertarClienteAtendido(inf);
        }
        if (tmp.left != null) {
            imprimirNiveles(tmp.left, list);
            nivel = 0;
        }
        if (tmp.right != null) {
            imprimirNiveles(tmp.right, list);
            nivel = 0;
        }
    }

    //GRAIFCAR POR ID DE IMAGEN
    public void bstIngresoCapa(Node tmp, MatrizDispersa matriz, long id_cliente, int id_capa) {
        if (tmp != null) {
            if (id_capa == tmp._capas.id_capa && tmp._capas.id_cliente == id_cliente) {
                matriz.insertar(tmp._capas.columna, tmp._capas.fila, tmp._capas.color);
                //System.out.println(tmp._capas.columna + " " + tmp._capas.fila + " " + tmp._capas.color);
            }
            bstIngresoCapa(tmp.left, matriz, id_cliente, id_capa);
            bstIngresoCapa(tmp.right, matriz, id_cliente, id_capa);
        }
    }

    /*public void recorridoAmplitud(Node tmp, int capa, Matriz matriz) {
        if (tmp != null) {
            if (capa == tmp._capas.id_capa) {
                matriz.insertar(tmp._capas.columna, tmp._capas.fila, tmp._capas.color);
            }
            recorridoAmplitud(tmp.left, capa, matriz);
            recorridoAmplitud(tmp.right, capa, matriz);
        }
    }*/
 /*public static String existe="";

    public void existe(Node tmp, long id) {
        if (tmp != null) {
            //System.out.println("entrada "+id+" ya esta "+tmp._capas.id_capa);
            if(tmp._capas.id_capa==id){
                existe="si";
                //System.out.println(existe);
            }
            existe(tmp.left,id);
            existe(tmp.right,id);
        }
    }*/
}
