/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ABB;

import java.io.FileWriter;
import java.io.PrintWriter;

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

    void preorder(Node tmp) {
        if (tmp != null) {
            System.out.print(tmp._capas.id_capa + " ");
            preorder(tmp.left);
            preorder(tmp.right);
        }
    }

    void enorder(Node tmp) {
        if (tmp != null) {
            enorder(tmp.left);
            //System.out.print(tmp._capas.id_capa + " ");
            enorder(tmp.right);
        }
    }

    void postorder(Node tmp) {
        if (tmp != null) {
            postorder(tmp.left);
            postorder(tmp.right);
            System.out.print(tmp._capas.id_capa + " ");
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
                + "node [shape = record, style=filled, fillcolor=seashell2];\n"
                + getCodigoInterno(tmp)
                + "}\n";
    }

    public String getCodigoInterno(Node tmp) {
        String etiqueta;
        if (tmp.left == null && tmp.right == null) {
            etiqueta = "nodo" + tmp.id + " [ label =\"" + tmp._capas.id_capa + "\"];\n";
        } else {
            etiqueta = "nodo" + tmp.id + " [ label =\"<C0>|" + tmp._capas.id_capa + "|<C1>\"];\n";
        }
        if (tmp.left != null) {
            etiqueta = etiqueta + getCodigoInterno(tmp.left)
                    + "nodo" + tmp.id + ":C0->nodo" + tmp.left.id + "\n";
        }
        if (tmp.right != null) {
            etiqueta = etiqueta + getCodigoInterno(tmp.right)
                    + "nodo" + tmp.id + ":C1->nodo" + tmp.right.id + "\n";
        }
        return etiqueta;
    }
    
    public static String existe="";

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
    }

}
