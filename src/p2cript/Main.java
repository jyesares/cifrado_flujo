/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2cript;

import controller.*;
import javax.swing.UIManager;
import view.VistaPrincipal;
/**
 *
 * @author javiyesares
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //VistaPrincipal.main(args);
        
        try {
        UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        
        /*UIManager.LookAndFeelInfo plaf[] = UIManager.getInstalledLookAndFeels();
 
        for (int i=0; i < plaf.length; i++){
 
            System.out.println("Nombre: " + plaf[i].getName());
 
            System.out.println("Nombre de la clase: "+ plaf[i].getClassName());
 
        }*/
 
        VistaPrincipal vp = new VistaPrincipal();
        Controlador c = new Controlador(vp);
        vp.setVisible(true);
        
        /*
         * 1
         *//*
        String txt = "01100110";
        CifradoFlujo1 ci = new CifradoFlujo1(txt);
        System.out.println(ci.getSecuencia());
        System.out.println(ci.getSecPeriodo());
        System.out.println(ci.getLongPeriodo());
        */
        
        /*
         * 2
         *//*
        String txt = "1001";
        Polinomio p1 = new Polinomio(1, 4);
        Polinomio p2 = new Polinomio(1, 2);
        Polinomio p3 = new Polinomio(1, 0);
        Polinomio p = p1.plus(p2).plus(p3);
        CifradoFlujo2 cf = new CifradoFlujo2(txt, p);
        System.out.println(cf.getSucesion());
        */
        
        /*
         * 3
         */
        /*String txt = "1010011";
        CifradoFlujo3 cf = new CifradoFlujo3(txt);
        cf.pintaPolinomio();
        */
    }
}
