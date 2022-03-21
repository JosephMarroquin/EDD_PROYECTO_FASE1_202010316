/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010316;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ABB.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import AVL.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author josep
 */
public class moduloUsuario extends javax.swing.JFrame {

    /**
     * Creates new form moduloUsuario
     */
    public moduloUsuario() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Ver Arbol de Imagenes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver Arbol de Capas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        jButton6.setText("Graficar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Graficar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Cargar Capas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cargar Imagenes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Cargar Albumes");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Cerrar Sesion");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(598, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(jButton7)
                            .addGap(96, 96, 96)
                            .addComponent(jButton6))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jButton1)
                            .addGap(32, 32, 32)
                            .addComponent(jButton2)
                            .addGap(47, 47, 47)
                            .addComponent(jButton3)
                            .addGap(18, 18, 18)
                            .addComponent(jButton4)
                            .addGap(18, 18, 18)
                            .addComponent(jButton5)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //ARBOL BINARIO DE BUSQUEDA
    public static bst abb = new bst();
    public static bst abbGraphviz = new bst();

    //ARBOL AVL
    public static AVL avl = new AVL();

    //
    private long id_cliente;

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Gson json = new Gson();
            JFileChooser selector = new JFileChooser();
            File file;
            selector.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(null, "json");
            selector.setFileFilter(filtro);

            if (selector.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
                file = selector.getSelectedFile();

                Scanner sc = new Scanner(file);
                String data = "";
                while (sc.hasNextLine()) {
                    data += sc.nextLine() + "\n";
                }
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(data);
                JSONArray array = (JSONArray) obj;
                JSONObject jobj;
                for (int i = 0; i < array.size(); i++) {
                    jobj = (JSONObject) array.get(i);
                    System.out.println("-----------------------------------");
                    JSONArray array2 = (JSONArray) jobj.get("pixeles");
                    JSONObject pixeles;

                    String id_capa = String.valueOf(jobj.get("id_capa"));

                    capas capG = new capas(id_cliente, Integer.valueOf(id_capa), 0, 0, "");
                    abbGraphviz.add(capG);

                    for (int j = 0; j < array2.size(); j++) {
                        pixeles = (JSONObject) array2.get(j);

                        //System.out.println("id: "+jobj.get("id_capa"));
                        /*System.out.println("fila: "+pixeles.get("fila"));
                        System.out.println("columna: "+pixeles.get("columna"));
                        System.out.println("color: "+pixeles.get("color"));
                        System.out.println();*/
                        String fila = String.valueOf(pixeles.get("fila"));
                        String columna = String.valueOf(pixeles.get("columna"));

                        capas _capas = new capas(id_cliente, Integer.valueOf(id_capa), Integer.valueOf(fila), Integer.valueOf(columna), String.valueOf(pixeles.get("color")));
                        abb.add(_capas);

                    }

                    System.out.println("-----------------------------------");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String ubicacion = "Estructuras\\ABB\\abb_" + id_cliente + ".jpg";
        Image imagen = new ImageIcon(ubicacion).getImage();
        ImageIcon imgIcon = new ImageIcon(imagen.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH));
        jLabel2.setIcon(imgIcon);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        abbGraphviz.graficar(abbGraphviz.root);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            Gson json = new Gson();
            JFileChooser selector = new JFileChooser();
            File file;
            selector.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(null, "json");
            selector.setFileFilter(filtro);

            if (selector.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
                file = selector.getSelectedFile();

                Scanner sc = new Scanner(file);
                String data = "";
                while (sc.hasNextLine()) {
                    data += sc.nextLine() + "\n";
                }
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(data);
                JSONArray array = (JSONArray) obj;
                JSONObject jobj;
                for (int i = 0; i < array.size(); i++) {
                    jobj = (JSONObject) array.get(i);
                    System.out.println("-----------------------------------");
                    //System.out.println(jobj.get("id"));

                    String id_imagen = String.valueOf(jobj.get("id"));
                    String capas = String.valueOf(jobj.get("capas"));
                    System.out.println(capas);
                    imagen img=new imagen(id_cliente,Integer.valueOf(id_imagen),capas);
                    avl.add(img);

                    System.out.println("-----------------------------------");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        avl.graficar(avl.root);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String ubicacion = "Estructuras\\AVL\\avl_" + id_cliente + ".jpg";
        Image imagen = new ImageIcon(ubicacion).getImage();
        ImageIcon imgIcon = new ImageIcon(imagen.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH));
        jLabel2.setIcon(imgIcon);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(moduloUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(moduloUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(moduloUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(moduloUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new moduloUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    // End of variables declaration//GEN-END:variables

}
