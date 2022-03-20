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
class Node {

    capas _capas;
    Node left;
    Node right;

    Node(capas _capas) {
        this._capas = _capas;
        left = null;
        right = null;
    }
}

public class bst {

    Node root = null;

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
            System.out.print(tmp._capas.id_capa + " ");
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

}
