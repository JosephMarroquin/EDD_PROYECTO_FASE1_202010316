/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablaHash;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JComboBox;

/**
 *
 * @author josep
 */
public class TablaHash {
    private int tamano;//tamano del arreglo de la tabla hash
    private static int[] tamanos;
    private int indiceTam;
    private int ocupados;//datps almacenados en la tabla
    private float porcentajeUtil;//para ver la capacidad de la tabla hash ha sido ocupada mas que todo para hacer rehashing
    private float factorUtil;
    public NodoHash[] vectorHash;//tiene todos los nodos hash

    public TablaHash() {
        this.tamanos = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157};//tamaño inicial
        this.indiceTam = 0;
        this.ocupados = 0;
        this.factorUtil = 75.0f;//para hacer rehashing
        this.tamano = tamanos[indiceTam];//toma el valor del vector este comienza en 7
        this.vectorHash = new NodoHash[tamano];//creo el vector con 7 de tamano
        this.porcentajeUtil = calcularPorcentajeUtil();
    }
    
    //para el desbordamiento//
    private float calcularPorcentajeUtil() {
        return (ocupados * 100) / tamano;
    }
    
    private long hash2(long dpi) {//funcion hash funcion por division k mod t
        return dpi % tamano;
    }
    
    public void insertar(long dpi, String nombres, String apellidos, String tipo_licencia, String genero, String telefono, String direcion) {
        boolean insertado = false;

        if (porcentajeUtil <= 75.00f) {///por el desbordamiento del 75% para que haga que crezca la tabla hash
            long posicion = hash2(dpi);
            if (posicion > tamano) {//si el tamaño es mayor a la posision se le resta al tamaño osea da una ciclo 
                posicion -= tamano; // y lo inserta                
            }   
            if (vectorHash[(int)posicion] == null || vectorHash[(int)posicion].estado == 'b') {//datos nulos y borrados y ocupados se ingresa el dato 
                //a un nuevo nodo hash
                vectorHash[(int)posicion] = new NodoHash(dpi,nombres,apellidos,tipo_licencia,genero,telefono,direcion);
                ocupados += 1;
                porcentajeUtil = calcularPorcentajeUtil();
                insertado = true;
                //System.out.println("Dato " + nombre + " " + "Posicion " + posicion);

            } else {
                if (vectorHash[(int)posicion].dpi==dpi) {
                    //System.out.println("La variable a insertar ya existe en la tabla: " + nombre);
                } else {
                    for (int i = 1; i < 10; i++) {
                        int posicionActual = (((int)posicion +i)^2)%tamano;//cuadratica 
                        if (posicionActual > tamano) {
                            posicionActual = i;
                        }
                        if (posicionActual < tamano && validarDisponibilidadColision(posicionActual)) {
                            vectorHash[posicionActual] = new NodoHash(dpi,nombres,apellidos,tipo_licencia,genero,telefono,direcion);
                            ocupados += 1;//suma uno a ocupados 
                            porcentajeUtil = calcularPorcentajeUtil();//no sobrepase el 50%
                            insertado = true;
                            break;
                        }
                        //cuando hay colision automaticamente el proceso sigue hasta que se pueda insertar el dato
                        //System.out.println("Colision en la pos: " + posicion);                        
                    }
                }
            }
            if (insertado == true) {
                //System.out.println("Se inserto correctamente el dato " + nombre);
            } else {
                //System.out.println("No se pudo insertar el dato " + nombre);
            }
        } else {
            //System.out.println("Hacer Rehashing -> Porcentaje util: " + porcentajeUtil);            
            rehashing();
            insertar(dpi,nombres,apellidos,tipo_licencia,genero,telefono,direcion);
        }
    }
    public boolean validarDisponibilidadColision(int posicion) {
        if (vectorHash[posicion] == null || vectorHash[posicion].estado == 'b') {
            return true;
        }
        return false;
    }
    public void rehashing() {///cuando sobrepase el 75 aumente el tamano de la tabla hash
        NodoHash[] tmp = vectorHash;
        int tamanoTmp = tamano;
        if (indiceTam < tamanos.length) {
            indiceTam += 1;
            if (indiceTam == tamanos.length - 1) { //esto pasa cuando se llega al tamano maximo del arreglo o vector en este casi es 81
                System.out.println("Se alcanzo el tamano maximo del arreglo");
            }
        }
        tamano = tamanos[indiceTam];
        vectorHash = new NodoHash[tamano];
        ocupados = 0;
        porcentajeUtil = calcularPorcentajeUtil();
        for (int i = 0; i < tamanoTmp; i++) {// se va insertando el nodo hash con el arreglo aumentado de tamano
            if (tmp[i] != null) {
                insertar(tmp[i].dpi,tmp[i].nombres,tmp[i].apellidos,tmp[i].tipo_licencia,tmp[i].genero,tmp[i].telefono,tmp[i].direccion);
            }
        }
        System.out.println("Rehashing realizado correctamente");
    }
   

    public void generarDotTablaHash() {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("Estructuras\\TablaHash\\HashTable.dot");            
            pw = new PrintWriter(fw);
            pw.println("digraph G{ rankdir=LR;");
            pw.println("node [shape= record, width=.1,height=.1];");
            pw.println("nodeTable [label = \" ");
            for (int i = 0; i < vectorHash.length; i++) {//COMIENZO DEL FOR PARA LLENAR LOS DATOS DE LA HASH
                pw.print("<f" + i + "> " + i);
                if (i != vectorHash.length - 1) {
                    pw.println(" | ");
                } else {
                    pw.println("\"];");
                }
            }    ///fin for datos hash   
            for (int i = 0; i < vectorHash.length; i++) {
                if (vectorHash[i] != null) {
                    pw.println("nodo" + vectorHash[i].dpi + " [label= \"" +  "Nombres: "+vectorHash[i].nombres + " " + "Apellidos: "+vectorHash[i].apellidos+" Tipo_licencia: "+vectorHash[i].tipo_licencia+" Genero: "+vectorHash[i].genero+" Telefono: "+vectorHash[i].telefono+" Direccion: "+vectorHash[i].direccion +"\"];");
                    pw.println("\"nodeTable\":f" + i + " -> nodo" + vectorHash[i].dpi);
                }
            }


            pw.println("}");

            try{
                String doPath="C:\\Program Files\\Graphviz\\bin\\dot.exe";
                String fileInputPath="Estructuras\\TablaHash\\HashTable.dot";
                String fileOutPath="Estructuras\\TablaHash\\HashTable.png";            
                String tParam="-Tpng";
                String toParam="-o";
                String[] cmd=new String[5];
                cmd[0]=doPath;
                cmd[1]=tParam;
                cmd[2]=fileInputPath;
                cmd[3]=toParam;
                cmd[4]=fileOutPath;
                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
                //rt.exec("HashTable.png");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != fw) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    //DPI MENSAJERO
    public void agregarDPI(JComboBox combox) {
       for (int i = 0; i < vectorHash.length; i++) {
                if (vectorHash[i] != null) {
                    combox.addItem(vectorHash[i].dpi);
                }
            }
    }
}
