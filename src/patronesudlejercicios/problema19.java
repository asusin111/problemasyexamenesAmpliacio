package patronesudlejercicios;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alberto Susin
 */
public class problema19 {
    miCircle circle1 = new miCircle(10,10,10);
    FigureVisitor scale = new Scaler(2);
    circle1.accept(scale);
}

public class Figure {
    public double x, y; //Las ponemos public para no tener que hacer get y set
    
    public Figure(double x, double y){
        this.x = x;
        this.y = y;
    }
} 

public class miCircle extends Figure{
    public double r; //Radius
    
    public miCircle(double x, double y, double r){ //Constructor
        super(x,y);
        this.r = r;
    }
    public double getRadius(){return this.r};
    public void setRadius (double rad){this.r = rad}
}

public class Drawing extends Figure{
    List<Figure> l;
    
    public Drawing (List<Figure> lista){
        this.l = lista;
    }
}

//Esta clase es la encargada que cuando se llame al accept se ejecute
// el correspondiente visit depende la figure que se le pase.
public interface FigureVisitor{
   void visit (Drawing d);
   void visit (miCircle c);
   //void visit (Rectangle cr);       //sin implementar
   //void visit (ColorRectangle cr);  //sin implementar
   //void visit(Line l);              //sin implementar

}

//PARTE 1
//Esta funcion lo que hace es que al hacer un scaler le pasaremos le factor
// por el que vamos a aumentarla
public class Scaler implements FigureVisitor {

    private double scale;
    
    public Scaler (double scaler){
        this.scale = scale;
    }
    
    public void visit (Circle c){
        c.setRadius(c.getRadius()*scale);
    }    //El mismo caso sera para Rectangle y Line creando las clases
    
    public void visit (Drawing d){
        for (Figure f: d.getComponents()){ //Para cada figura de la lista...
            f.accept(this); //Llamamos al accept de esa figura para escalarlo
        }
    
    }
}
    
    //PARTE 2
    //Ahora imaginamos que las figuras son inmutables, lo que NO permite
    //modificar los 
    
    //Vamos a hacer el Drawing que es el mas complicado

public class ScalerInmutable implements FigureVisitor {
    
    private double scale;
    private Figure ScaledFigure; //Podriamos guardarlo en la clase y crear getter
    
    public ScalerInmutable {
        this.scale = scale;
    }
    
    public void visit(Circle c){
        ScaledFigure = return new miCircle(c.getRadius()*scale);
    
    }
    
    //Este visit de figures necesita una lista a la que por cada figura
    //scale la figura en el interior para guardarlo en otra lista
    //y esta lista ya de escalados podemos meterlo a una Figura drawing
    public void visit(Drawing d){

        List<Figure> components = new ArrayList<>();

        for (Figure f: d.getComponents()){
            Scaler sca = new Scaler(scale);
            f.accept(sca); //Esta funcion llama al visit depende la figura que sea
            ////!!!!!!!!! no seria sca.accept(f) depende el accept

        }
        components.add(sca.addFigure()); //Añades a la lista la figura escalada
        ScaledFigure = new Drawing(components); //la
    }
}