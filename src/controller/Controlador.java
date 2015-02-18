/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import model.*;
import view.*;

/**
 *
 * @author javiyesares
 */
public class Controlador implements ActionListener{
    
    VistaPrincipal vp;
    
    public Controlador(VistaPrincipal vp){
        this.vp = vp;
        this.vp.examinar.addActionListener(this);
        this.vp.CalPeriodo.addActionListener(this);
        this.vp.CalLFSR.addActionListener(this);
        this.vp.CalBM.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==vp.examinar){
           VistaFileChooser vfc = new VistaFileChooser(vp, true);
           int returnVal = vfc.jFileChooser.showOpenDialog(vp);
           if(returnVal == JFileChooser.APPROVE_OPTION){
               vp.field.setText(vfc.jFileChooser.getSelectedFile().getAbsolutePath());
           }
           
       }
       if(e.getSource()==vp.CalPeriodo){
           Ficheros f = new Ficheros();
           String texto = "";
           texto = f.leeFichero(vp.field.getText());
           CifradoFlujo1 cf1 = new CifradoFlujo1(texto);
           vp.orin.setText(texto);
           if(cf1.isPeriodico()){
                vp.per.setText("Sí");
                String aux = cf1.getLongPeriodo()+"";
                vp.lon.setText(aux);
                vp.sec.setText(cf1.getSecPeriodo());
           }
           else vp.per.setText("No");
           
       }
       
       if(e.getSource()==vp.CalLFSR){
           Ficheros f = new Ficheros();
           Polinomio polinomio;
           polinomio = new Polinomio(0, vp.polString.getText().length());
           polinomio = Polinomio.toPolinomio(vp.polString.getText());
           CifradoFlujo2 cf2 = new CifradoFlujo2(vp.suc.getText(), polinomio);
           vp.pol.setText(polinomio.toString());
           f.escribeFichero(vp.field.getText(), cf2.getSucesion());
       }
       
       if(e.getSource()==vp.CalBM){
           Ficheros f = new Ficheros();
           String texto = "";
           texto = f.leeFichero(vp.field.getText());
           CifradoFlujo3 cf3 = new CifradoFlujo3(texto);
           String aux="";
           for(int i=cf3.getPolString().length()-1; i>=0; i--)
               aux += cf3.getPolString().charAt(i);
           vp.polbm.setText(aux);
           vp.pxbm.setText(Polinomio.toPolinomio(aux).toString());
           vp.secBM.setText(texto);
           aux = cf3.getL()+"";
           vp.lbm.setText(aux);
       }
    }
}
