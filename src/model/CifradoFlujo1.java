/*
 * Dada una sucesion de bits, determine si es o no periódica, 
 * y en caso afirmativo, determine la longitud del periodo 
 * (para que sea periódica, el periodo debe aparecer en la sucesión 
 * al menos dos veces).
 */
package model;

/**
 * Dada una sucesion de bits, determine si es o no periódica, 
 * y en caso afirmativo, determine la longitud del periodo 
 * (para que sea periódica, el periodo debe aparecer en la sucesión 
 * al menos dos veces).
 * @author javiyesares
 */
public class CifradoFlujo1 {

    private String secuencia;
    private String secPeriodo;
    private boolean periodico;
    private int longPeriodo;
    private int tamano;
    private int nunos;
    private int nceros;
    
    public CifradoFlujo1(){
        secuencia = null;
        secPeriodo = null;
        periodico = false;
        longPeriodo = -1;
        tamano = -1;
        nunos = -1;
        nceros = -1;
    }
    
    public CifradoFlujo1(String secuencia){
        this.secuencia = secuencia;
        this.tamano = this.secuencia.length();
        this.nunos = cuentaDigito('1');
        this.nceros = cuentaDigito('0');
        this.periodico = calculaPeriodo();
        
    }
    
    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getNunos() {
        return nunos;
    }

    public void setNunos(int nunos) {
        this.nunos = nunos;
    }

    public int getNceros() {
        return nceros;
    }

    public void setNceros(int nceros) {
        this.nceros = nceros;
    }

    public String getSecPeriodo() {
        return secPeriodo;
    }

    public void setSecPeriodo(String secPeriodo) {
        this.secPeriodo = secPeriodo;
    }

    public boolean isPeriodico() {
        return periodico;
    }

    public void setPeriodico(boolean periodico) {
        this.periodico = periodico;
    }

    public int getLongPeriodo() {
        return longPeriodo;
    }

    public void setLongPeriodo(int longPeriodo) {
        this.longPeriodo = longPeriodo;
    }
    
    private int cuentaDigito(char digito) {
        int n=0;
        for(int i=0; i<tamano; i++){
            if(secuencia.charAt(i)==digito){
                n++;
            }    
        }
        return n;
    }

    private boolean calculaPeriodo() {
        
        String aux = null;
        boolean encontrado = false;
        
        for(int i=1; i<=tamano/2 && !encontrado; i++){
            aux = secuencia.substring(0,i);
            encontrado = true;
            for(int j=i; j<tamano && encontrado; j++){
                if(secuencia.charAt(j)!=aux.charAt((j)%aux.length())){
                    encontrado = false;
                }
            }
        }
        
        if(encontrado){
            periodico = encontrado;
            secPeriodo = aux;
            longPeriodo = secPeriodo.length();
        }
        return periodico;
    }
    
}
