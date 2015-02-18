/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author javiyesares
 */
public class Polinomio {
    private int[] coef;  // coefficients
    private int deg;     // degree of polynomial (0 for the zero polynomial)

    // a * x^b
    public Polinomio(int a, int b) {
        coef = new int[b+1];
        coef[b] = a;
        deg = degree();
    }

    // return the degree of this polynomial (0 for the zero polynomial)
    public int degree() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0) d = i;
        return d;
    }

    // return c = a + b
    public Polinomio plus(Polinomio b) {
        Polinomio a = this;
        Polinomio c = new Polinomio(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] += b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // return (a - b)
    public Polinomio minus(Polinomio b) {
        Polinomio a = this;
        Polinomio c = new Polinomio(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] -= b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // return (a * b)
    public Polinomio times(Polinomio b) {
        Polinomio a = this;
        Polinomio c = new Polinomio(0, a.deg + b.deg);
        for (int i = 0; i <= a.deg; i++)
            for (int j = 0; j <= b.deg; j++)
                c.coef[i+j] += (a.coef[i] * b.coef[j]);
        c.deg = c.degree();
        return c;
    }

    // return a(b(x))  - compute using Horner's method
    public Polinomio compose(Polinomio b) {
        Polinomio a = this;
        Polinomio c = new Polinomio(0, 0);
        for (int i = a.deg; i >= 0; i--) {
            Polinomio term = new Polinomio(a.coef[i], 0);
            c = term.plus(b.times(c));
        }
        return c;
    }


    // do a and b represent the same polynomial?
    public boolean eq(Polinomio b) {
        Polinomio a = this;
        if (a.deg != b.deg) return false;
        for (int i = a.deg; i >= 0; i--)
            if (a.coef[i] != b.coef[i]) return false;
        return true;
    }


    // use Horner's method to compute and return the polynomial evaluated at x
    public int evaluate(int x) {
        int p = 0;
        for (int i = deg; i >= 0; i--)
            p = coef[i] + (x * p);
        return p;
    }

    // differentiate this polynomial and return it
    public Polinomio differentiate() {
        if (deg == 0) return new Polinomio(0, 0);
        Polinomio deriv = new Polinomio(0, deg - 1);
        deriv.deg = deg - 1;
        for (int i = 0; i < deg; i++)
            deriv.coef[i] = (i + 1) * coef[i + 1];
        return deriv;
    }

    // convert to string representation
    public String toString() {
        if (deg ==  0) return "" + coef[0];
        if (deg ==  1) return coef[1] + "x + " + coef[0];
        String s = coef[deg] + "x^" + deg;
        for (int i = deg-1; i >= 0; i--) {
            if      (coef[i] == 0) continue;
            else if (coef[i]  > 0) s = s + " + " + ( coef[i]);
            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }
    
    // convert to polynomial a string
    public static Polinomio toPolinomio(String cad) {
        
        Polinomio pol = new Polinomio(0, cad.length());
        
        for(int i=0; i<cad.length(); i++){
            if(cad.charAt(i)=='1'){
                pol = pol.plus(new Polinomio(1,cad.length()-i-1));
            }
        }

        
        return pol;
      
    }

    public boolean existCoef(int i){
        if(coef[i]!=0) return true;
        else return false;
    }
}

