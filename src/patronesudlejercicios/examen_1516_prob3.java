/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesudlejercicios;

/**
 *
 * @author Fran,Oriol,Alberto
 */

public abstract class Figure {

    private final double x;
    private final double y;
    
    public Figure (double x, double y){ //Constructor basico
        this.x = x;
        this.y = y;
    }
    
    
    public static class Builder {   //Constructor Builder

        private double x, y, w, h, r;

        private boolean hasAt = false;
        private boolean isRect = false;
        private boolean isCircle = false;
        
        //Lo primero es crear el Builder que es la base para encadenar metodos
        public Builder create(){
            return new Builder();
        }
        
        // El AT la tiene tanto Rect y Circle
        public Builder at(double x, double y){
            
            if(!hasAt){
                this.x = x;
                this.y = y;
                hasAt = true;                   
            }
            return this;
        }
        
        //RECTANGLE
        public Builder withDimensions(double w, double h){
            
            if(!isCircle){
                this.w = w;
                this.h = h;   
                isRect = true;
                return this;
            }else{
                throw new IllegalStateException();
            }           
        }       
        
        //CIRCLE
        public Builder withRadius(double radius){
            
            if(!isCircle){
                this.r = radius;
                isCircle = true;
                return this;
            }else{
                throw new IllegalStateException();
            }           
        }
        
        //Creamos la figura
        public Figure execute(){
        
            if(hasAt && isCircle){
                return new Circle(this.x,this.y, this.r);
            }
            else if (hasAt && isRect){
                return new Rectangle(this.x, this.y, this.w, this.h);
            }else{
                throw new IllegalStateException();
            }
        
        }
        
        
        
        

    }

}
