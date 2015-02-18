/*
 * Dada una sucesión de bits periódica, determine la complejidad lineal 
 * de dicha sucesión, y el polinomio de conexión que la genera. 
 * Para esto, se usará el algoritmo de Berlekamp-Massey.
 */
package model;

/**
 * Dada una sucesión de bits periódica, determine la complejidad lineal 
 * de dicha sucesión, y el polinomio de conexión que la genera. 
 * Para esto, se usará el algoritmo de Berlekamp-Massey.
 * @author javiyesares
 */
public class CifradoFlujo3 {
    
    private String sucesion;
    private int L;
    private Polinomio polinomio;
    private String polString;
    
    public CifradoFlujo3(){
        sucesion = null;
        L = -1;
        polinomio = null;
        polString = null;
    }
    
    public CifradoFlujo3(String sucesion){
        this.sucesion = sucesion;
        polString = berlekampMassey();
        polinomio = new Polinomio(0,polString.length());
        polinomio = Polinomio.toPolinomio(polString);
    }
    
    public void pintaPolinomio(){
        System.out.println(polinomio.toString());
    }
    
    public String getSucesion() {
        return sucesion;
    }

    public void setSucesion(String sucesion) {
        this.sucesion = sucesion;
    }
    
    public int getL() {
        return L;
    }

    public void setL(int L) {
        this.L = L;
    }

    public Polinomio getPolinomio() {
        return polinomio;
    }

    public void setPolinomio(Polinomio polinomio) {
        this.polinomio = polinomio;
    }
    
    public String getPolString() {
        return polString;
    }

    public void setPolString(String polString) {
        this.polString = polString;
    }
    
    private String berlekampMassey(){
        
        int c[], b[], s[], aux2[], n, m=-1, r=0, d=0;
        String pol="";
        
        n = sucesion.length();
        L = 0;
        s = new int[n];
        c = new int[n]; 
        b = new int[n];
        aux2 = new int[n];
        
        for(int i=0; i<n; i++){
            c[i]=0;
            b[i]=0;
        }
        c[0]=1;
        b[0]=1;
        
        for(int i=0; i<sucesion.length(); i++){
            String aux = "";
            aux += sucesion.charAt(i);
            s[i] = Integer.parseInt(aux);
        }
        
        while(r<=n-1){
            for(int i=0; i<=r; i++){
                d += c[i]*s[r-i];
            }
            d = d%2;
            if(d==1){
                for(int i=0; i<c.length; i++){
                    aux2[i] = c[i];
                    //aux2[i] = aux2[i]%2;
                }
                for(int j=0; j<=n-r+m-1; j++){
                    c[r-m+j] += b[j];
                    c[r-m+j] = c[r-m+j]%2;
                }
                if(L<=r/2){
                    L = r-L+1;
                    m = r;
                    for(int i=0; i<aux2.length; i++)
                        b[i] = aux2[i];
                }
            }
            d=0;
            r++;
        }
        
        for(int i=0; i<c.length; i++){
            pol += Character.forDigit(c[i],10);
        }
        
        return pol;
        
    }
}
