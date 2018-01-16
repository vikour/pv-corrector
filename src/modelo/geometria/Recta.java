
package modelo.geometria;

import java.util.List;

public class Recta extends ObjetoGeometrico {
    
    private double a;
    private double b;
    private double c;
    
    public Recta(Punto p0, Punto p1) {
        double v1 = p1.getX() - p0.getX();
        double v2 = p1.getY() - p0.getY();
        
        if (v1 == 0) {
            a = 1 / v1;
            b = 0;            
            c = p0.getY() / v2;
        }
        else if (v2 == 0) {
            a = 0;
            b = (-1) / v2;
            c = (-p0.getX()) / v1;
        }
        else {
            a = 1 / v1;
            b = (-1) / v2;
            c = (-p0.getX()) / v1 + p0.getY() / v2;
        }
        
        if (a == 0 || b == 0)
            throw new RuntimeException();
        
    }
    
    public Recta(Punto [] puntos) {
      double m1x = 0;
      double m2x = 0;
      double m11 = 0;
      double m1y = 0;

      for (int i = 0; i < puntos.length; i++) {
         m1x += puntos[i].getX();
         m2x += puntos[i].getX() * puntos[i].getX();
         m11 += puntos[i].getY() * puntos[i].getX();
         m1y += puntos[i].getY();
      }

      m11 /= puntos.length;
      m1x /= puntos.length;
      m2x /= puntos.length;
      m1y /= puntos.length;

      double cov = m11 - m1x * m1y;
      double varx = m2x - m1x * m1x;
      
      a = -cov / varx;
      b = 1;
      c = -m1y - b * m1x;
    }
    
    public Recta(List<Punto> puntos) {
        this(puntos.toArray(new Punto[puntos.size()]));
    }
    
    public double pendiente() {
        double m = 0;
        
        if (b != 0)
            m = -a / b;
        
        return m;
    }
    
    public double ordenadaCorte() {
        double n = 0;
        
        if (b != 0)
            n = -c/b;
        
        return n;
    }
    
    public boolean secante(Recta r) {
        boolean secante = false;
        
        if (r.a != 0 && r.b != 0)
            secante = a/r.a != b/r.b;
            
        return secante;
    }
    
    public boolean paralela(Recta r) {
        boolean paralela = false;
        
        if (r.a != 0 && r.b != 0 && r.c != 0)
            paralela = (a/r.a == b/r.b) && (b/r.b != c/r.c);
        
        return paralela;
    }
    
    public boolean coincidentes(Recta r) {
        boolean coincidentes = false;
        
        if (r.a != 0 && r.b != 0 && r.c != 0)
            coincidentes = (a/r.a == b/r.b) && (b/r.b == c/r.c);
        
        return coincidentes;
    }
    
    public double valorY(double x) {
        return pendiente()*x + ordenadaCorte();
    }
    
    public double valorX(double y) {
        return (y - ordenadaCorte()) / pendiente();
    }
    
    public ObjetoGeometrico interseccion(Recta r) {
        ObjetoGeometrico result = null;
        double m = pendiente();
        double n = ordenadaCorte();
        double x ;
        
        if (secante(r)) {
            x = (r.ordenadaCorte() - n) / (m - r.pendiente());
            result = new Punto(x, valorY(x));
        }
        else if (coincidentes(r))
            result = this;

        
        return result;
    }
    

    public String formatImplicita() {
        String result = null;
        double m = pendiente();
        double n = ordenadaCorte();
        
        if (a == 0 && b != 0)
            result = " x = " + (-c/b);
        else if (b == 0 && a != 0)
            result = " y = " + (-c/a);
        else
            result = " y = " + String.format("%.4f",m) +"x " + String.format("%+.4f", n);

        return result;
    }

    @Override
    public String toString() {
        return String.format("%+.4f", a) + " x " +
               String.format("%+.4f", b) + " y " + 
               String.format("%+.4f", c) + " = 0";
    }
    
}
