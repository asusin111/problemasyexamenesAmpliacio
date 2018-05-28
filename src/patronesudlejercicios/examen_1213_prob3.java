
//EXAMEN segunda parte SOLUCIONADO
//UDL 2012/2013: AMPLIACIÓ DE BASES DE DADES I ENGINYERIA DEL PROGRAMARI
//Esta hecho sobre papel, no esperar que compile ya que faltan clases
package patronesudlejercicios;

/**
 * @author Alberto Susin
 */
import java.awt.Color; 
//Explicacion: Como dice el enunciado son inmutables por lo que necesitamos
//buscar un patron sin tener que cambiar condiciones y construirlo de 1.
//Por esta razón y la facilidad de uso lo haremos con Builder

public abstract class Figure {

    public static class Builder {
        private double x,y,w,h,x2,y2,r;
        private java.awt.Color  col;
        private boolean isRect = false;
        private boolean isCircle = false;
        private boolean isLine = false;
        private boolean isRectCol = false;
        private boolean isPos = false;
        
    
        //RECTANGULO Y CIRCLE
        public Builder at(double x, double y){
            if (!isLine){ //El at es usado por Circle, Rect menos por line.
                this.x = x; 
                this.y = y;
                isPos = true;
            }
            return null; //Devuelve cuando falta info o es contradictoria
        }

        //CIRCLE
        public Builder withRadius(double r){
            if(!isLine && !isRect){ //Check: no hay "to", "from" ni "withDims"
                this.r = r;
                isCircle = true;
                return this;
            }
            return null;
        }

        //RECTANGULO
        public Builder withDims (double w, double h){ 
            if (!isCircle && !isLine){ //Tene
                this.w = w;
                this.h = h;
                isRect = true;
                return this;
            }
            return null;
        }

        //LINE
        public Builder to(double x2, double y2){
            if(!isRect && !isCircle){ //El to es usado solo para line
                this.x2 = x2;
                this.y2 = y2;
                isLine = true;
                return this;
            }
            return null;
        }

        public Builder in (java.awt.Color col){
            if (isRect){ //Para ser ColorRectangle tiene que ser Rect primero
                this.col = col;
                return this;
            }
            return null;
        }

        public static Builder create(){
            return new Builder();
        }

        public Figure do(){
            if (isPos){
                if (isCircle){
                    return new Circle(x,y,r);
                }
                else if (isRect && isRectCol){
                    return RectColor(x,y,w,h,col);
                }
            }
        }
    }
    
}

















public class examen_1213_prob3 {
    
}
