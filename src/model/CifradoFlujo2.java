/*
 * Dado un polinomio p(x) 2 Z2[x] de grado n, y tal que p(0) = 1,
 * y una sucesión de n bits, construya la sucesión pseudo-aleatoria 
 * que generar ́ıa un LFSR, con polinomio de conexión p(x), y semilla
 * la sucesión de n bits dada. La salida debe ser guardada en un 
 * fichero de texto, y debe contener, al menos, dos veces el periodo.
 */
package model;

/**
 * Dado un polinomio p(x) 2 Z2[x] de grado n, y tal que p(0) = 1,
 * y una sucesión de n bits, construya la sucesión pseudo-aleatoria 
 * que generar ́ıa un LFSR, con polinomio de conexión p(x), y semilla
 * la sucesión de n bits dada. La salida debe ser guardada en un 
 * fichero de texto, y debe contener, al menos, dos veces el periodo.
 * @author javiyesares
 */
public class CifradoFlujo2 {
    
    private String semilla;
    private Polinomio polinomio;
    private String sucesion;
    private String funcion;
    
    public CifradoFlujo2(){
        semilla = null;
        polinomio = null;
        sucesion = null;
        funcion = null;
    }
    
    public CifradoFlujo2(String semilla, Polinomio polinomio){
        boolean salir = false;
        int tam = semilla.length()*2;
        
        this.semilla = semilla;
        this.polinomio = polinomio;
        this.funcion = obtenerFuncion();
        do{
            this.sucesion = calcularSucesion(tam);
            if(calculaPeriodo(this.sucesion))
                salir = true;
            else tam++;
        }while(!salir);
    }
    
    public String getSemilla() {
        return semilla;
    }

    public void setSemilla(String semilla) {
        this.semilla = semilla;
    }

    public Polinomio getPolinomio() {
        return polinomio;
    }

    public void setPolinomio(Polinomio polinomio) {
        this.polinomio = polinomio;
    }
    
    public String getSucesion() {
        return sucesion;
    }

    public void setSucesion(String sucesion) {
        this.sucesion = sucesion;
    }
    
    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
    
    private String calcularSucesion(int tam) {
        String s="", aux="";
        int suma=0, temp=0;
        int L;
        
        L = polinomio.degree(); // o semilla.length()
        
        for(int i=0; i<semilla.length(); i++)
            s += semilla.charAt(i);

        for(int i=semilla.length(); i<tam; i++){
            for(int j=0; j<funcion.length(); j++){
                aux = "";
                aux += funcion.charAt(j); // lo convierto a entero
                temp = Integer.parseInt(aux);
                aux = "";
                aux += s.charAt(temp+i-semilla.length());
                suma += Integer.parseInt(aux);
            }
            suma = suma%2;
            s += Character.forDigit(suma,10);
            suma = 0;
        }
        return s;
    }
    
    private String obtenerFuncion(){
        String cadena = "";
        
        for(int i=1; i<=semilla.length(); i++)
            if(polinomio.existCoef(i))
                cadena += semilla.length()-i;
        
        return cadena;
    }
    
    private boolean calculaPeriodo(String s) {
        
        String aux = null;
        boolean encontrado = false;
        
        for(int i=1; i<=s.length()/2 && !encontrado; i++){
            aux = s.substring(0,i);
            encontrado = true;
            for(int j=i; j<s.length() && encontrado; j++){
                if(s.charAt(j)!=aux.charAt((j)%aux.length())){
                    encontrado = false;
                }
            }
        }
        
        if(encontrado){
            //secPeriodo = aux;
            //longPeriodo = secPeriodo.length();
            return true;
        }else return false;
    }
}
