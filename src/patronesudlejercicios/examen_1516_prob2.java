
//EXAMEN segunda parte SOLUCIONADO
//UDL 2015/2016: AMPLIACIÓ DE BASES DE DADES I ENGINYERIA DEL PROGRAMARI
//Esta hecho sobre papel, no esperar que compile ya que faltan clases

package patronesudlejercicios;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alberto Susin
 */
public class examen_1516_prob2 {
    
}

public abstract class Component {

    public abstract void accept(ComponentVisitor cv);
}

//Sera un nodo final, en este caso contiene una variable con informacion a tratar
public class Leaf extends Component{
    private int info;
    
    public int getInfo(){
        return info;
    }
    
    @Override
    public void accept(ComponentVisitor cv){
        cv.visit(this);
    }
            
}

//Composite que puede guardar en su lista leafs como otros composite
//Muy util para estructuras en arbol usar este patron
public class Composite extends Component{

    List<Component> lc;
    
    public List<Component> getComponents(){     //Devuelve una lista no modificable
        return Collections.unmodifiableList(lc);
    }
    
    @Override
    public void accept(ComponentVisitor cv){
        cv.visit(this);
    }
}

public interface ComponentVisitor {

    void visit(Leaf l);
    void visit(Composite c);

}

//Funcion para contar cuantas hojas (leaf) tiene esta estructura en arbol
public class CountVisitor implements ComponentVisitor{

    private int count = 0;
    
    //Nos tenemos que proteger añadiendo los que ya hemos contado para no repetir
    private Set<Component> lx = new HashSet<>();
    
    public void visit (Leaf l){
        if (!estavisitado(l)){
            count += 1;
        }
    }
    
    public void visit(Composite c){
  
        for (Component cx: c.getComponents()){
            cx.accept(this);
        }    
    }
    
    //Comprobamos de forma unica si ya esta visitado
    public boolean estavisitado(Component c){
        return lx.contains(c);
    }
    
    public int getCount(){
        return count;
    }

}

//Funcion para sumar todos los elementos que hay en las hojas (leaf)
public class SumVisitor implements ComponentVisitor{

    private int suma = 0;
    private Set<Component> lx = new HashSet<>();

    public void visit(Leaf l){
        suma += l.getInfo();
    }
    
    public void visit(Composite c){
        for (Component cx: c.getComponents()){
            cx.accept(this);
        }    
    
    }
    
    //Comprobamos de forma unica si ya esta visitado
    public boolean estavisitado(Component c){
        return lx.contains(c);
    }
    
    
    public int getSuma(){
        return suma;
    }
}

/////////////////////////////////////////////////////////////////
// Como vemos se podria simplificar mucho la estructura y reutilizar codigo
//Count y Sum tienen los visit casi igual, la comprobacion del visited es igual...

public class UnificarSumyCount implements ComponentVisitor {

    private Set<Component> lx = new HashSet<>();
    public int accum = 0;
    //Comprobamos de forma unica si ya esta visitado
    public boolean estavisitado(Component c){
        return lx.contains(c);
    }
    
    public void visit(Leaf l){
        accum += dovisit(l); //Si es para count dovisit aumenta 1 y suma el info.
    }
    
    public void visit(Composite c){
        for (Component cx: c.getComponents()){
            cx.accept(this);
        }    
    }
    
    public abstract int dovisit(Leaf l);
}

//Mejoramos la clase SumVisitor usando la plantilla que hemos creado
//Solo necesitamos añadir lo que es especifico de la suma
public class SumVisitor2 extends UnificarSumyCount {
    
    @Override
    public int dovisit(Leaf l){
        return l.getInfo();
    }


}