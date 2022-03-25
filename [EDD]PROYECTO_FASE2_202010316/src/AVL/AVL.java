/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ABB.*;
import Matriz.*;

/**
 *
 * @author josep
 */
class NodeAVL {

    private static int correlativo = 1;
    public final int id;

    imagen _imagen;
    NodeAVL left;
    NodeAVL right;
    int alt;

    NodeAVL(imagen _imagen) {
        this._imagen = _imagen;
        left = null;
        right = null;
        alt = 0;
        this.id = correlativo++;
    }
}

public class AVL {

    public NodeAVL root = null;

    public void add(imagen _imagen) {
        root = add(_imagen, root);
    }

    NodeAVL add(imagen _imagen, NodeAVL tmp) {
        if (tmp == null) {
            tmp = new NodeAVL(_imagen);
        } else if (_imagen.id_imagen < tmp._imagen.id_imagen) {
            tmp.left = add(_imagen, tmp.left);
            if ((altura(tmp.left) - altura(tmp.right)) == 2) {
                if (_imagen.id_imagen < tmp.left._imagen.id_imagen) {
                    tmp = srl(tmp);
                } else {
                    tmp = drl(tmp);
                }
            }
        } else {
            tmp.right = add(_imagen, tmp.right);
            if ((altura(tmp.right) - altura(tmp.left)) == 2) {
                if (_imagen.id_imagen > tmp.right._imagen.id_imagen) {
                    tmp = srr(tmp);
                } else {
                    tmp = drr(tmp);
                }
            }

        }
        int d, i, m;
        d = altura(tmp.right);
        i = altura(tmp.left);
        m = maxi(d, i);
        tmp.alt = m + 1;
        return tmp;
    }

    int altura(NodeAVL tmp) {
        if (tmp == null) {
            return -1;
        } else {
            return tmp.alt;
        }
    }

    int maxi(int val1, int val2) {
        return ((val1 > val2) ? val1 : val2);
    }

    NodeAVL srl(NodeAVL t1) {
        NodeAVL t2;
        t2 = t1.left;
        t1.left = t2.right;
        t2.right = t1;
        t1.alt = maxi(altura(t1.left), altura(t1.right)) + 1;
        t2.alt = maxi(altura(t2.left), t1.alt) + 1;
        return t2;
    }

    NodeAVL srr(NodeAVL t1) {
        NodeAVL t2;
        t2 = t1.right;
        t1.right = t2.left;
        t2.left = t1;
        t1.alt = maxi(altura(t1.left), altura(t1.right)) + 1;
        t2.alt = maxi(altura(t2.right), t1.alt) + 1;
        return t2;
    }

    NodeAVL drl(NodeAVL tmp) {
        tmp.left = srr(tmp.left);
        return srl(tmp);
    }

    NodeAVL drr(NodeAVL tmp) {
        tmp.right = srl(tmp.right);
        return srr(tmp);
    }

    void preorder(NodeAVL tmp) {
        if (tmp != null) {
            System.out.print(tmp._imagen.id_imagen + " ");
            preorder(tmp.left);
            preorder(tmp.right);
        }
    }

    void enorder(NodeAVL tmp) {
        if (tmp != null) {
            enorder(tmp.left);
            System.out.print(tmp._imagen.id_imagen + " ");
            enorder(tmp.right);
        }
    }

    void postorder(NodeAVL tmp) {
        if (tmp != null) {
            postorder(tmp.left);
            postorder(tmp.right);
            System.out.print(tmp._imagen.id_imagen + " ");
        }
    }

    //Graficar
    public void graficar(NodeAVL tmp) {
        FileWriter fichero = null;
        PrintWriter escritor;
        String dot = "Estructuras\\AVL\\avl_" + tmp._imagen.id_cliente + ".dot";
        String jpg = "Estructuras\\AVL\\avl_" + tmp._imagen.id_cliente + ".jpg";
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

    private String getCodigoGraphviz(NodeAVL tmp) {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=seashell2];\n"
                + getCodigoInterno(tmp)
                + "}\n";
    }

    private String getCodigoInterno(NodeAVL tmp) {
        String etiqueta;
        if (tmp.left == null && tmp.right == null) {
            etiqueta = "nodo" + tmp.id + " [ label =\"" + tmp._imagen.id_imagen + "\"];\n";
        } else {
            etiqueta = "nodo" + tmp.id + " [ label =\"<C0>|" + tmp._imagen.id_imagen + "|<C1>\"];\n";
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

    //GRAFICAR POR ID DE LA IMAGEN
    public void porIDimagen(NodeAVL tmp, long id_cliente, int id_imagen, MatrizDispersa matriz,bst abb) {
        if (tmp != null) {
            if (id_cliente == tmp._imagen.id_cliente && id_imagen == tmp._imagen.id_imagen) {
                String patron = "\\d+";
                Pattern pattern = Pattern.compile(patron);
                Matcher matcher = pattern.matcher(tmp._imagen.capa);

                while (matcher.find()) {
                    System.out.println(matcher.group());
                    String capa = matcher.group();
                    abb.bstIngresoCapa(abb.root, matriz, id_cliente, Integer.valueOf(capa));
                }
                //System.out.print(tmp._imagen.id_imagen + " ");
            }
            porIDimagen(tmp.left, id_cliente, id_imagen,matriz,abb);
            porIDimagen(tmp.right, id_cliente, id_imagen,matriz,abb);
        }
    }

}
