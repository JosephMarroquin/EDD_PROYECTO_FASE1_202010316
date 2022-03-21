/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

/**
 *
 * @author josep
 */
class NodeAVL {

    imagen _imagen;
    NodeAVL left;
    NodeAVL right;
    int alt;

    NodeAVL(imagen _imagen) {
        this._imagen = _imagen;
        left = null;
        right = null;
        alt = 0;
    }
}

public class AVL {

    NodeAVL root = null;

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
    

 
}
