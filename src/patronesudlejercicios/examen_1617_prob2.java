//EXAMEN segunda parte SOLUCIONADO RECUPERACIO
//UDL 2016/2017: AMPLIACIÓ DE BASES DE DADES I ENGINYERIA DEL PROGRAMARI
//Esta hecho sobre papel, no esperar que compile ya que faltan clases

package patronesudlejercicios;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Alberto Susin
 */
public class examen_1617_prob2 {}

//GENERAL MACHINECOMPONENT ::: 
public abstract class MachineComponent{
    private final long id;
    
    public MachineComponent(long id){   //Constructor
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
}

//NODO MACHINE ::: 
public class Machine extends MachineComponent{

    public Machine(long id){
        super(id);
    }
}

//COMPOSITE
public class MachineComposite extends MachineComponent implements Observer{

    private List<MachineComponents> lmc;
    private MachineCompositeObservable obs;
    
    //Constructor completo
    public MachineComposite(long id){
        super(id);
        lmc = new ArrayList<>();
        obs =  new MachineCompositeObservable();
    }
    
    
    public void addComponent(MachineComponent mc){
        lmc.add(mc);
       
        if (mc instanceof MachineComposite){
            MachineComposite moc = (MachineComposite) mc;
            moc.addObserver(this);
        }
        notifyChanges();
    }
    
    //UPDATE (observer) envia las notificacione de los subcomponentes a los observadores
    public void update(Observable o, Object arg){
        setChanged();
        notifyObservers(arg);
    }
    
    //Agrupa las 2 funciones que posteriormente puenteamos con el adapter
    public void notifyChanges(){
        setChanged();
        notifyObservers(this);
    }
    //////////////////////////////////
    /////  Metodos adaptador del Observable   ///////////
    public void addObserver(Observer o){
        obs.addObserver(o);
    }
    
    public void notifyObservers(Observer o){
        obs.notifyObservers(o);
    }
    
    public void deleteObserver(Observer o){
        obs.deleteObserver(o);
    }
    
    public void notifyObservers(){
        obs.notifyObservers();
    }
    
    public void setChanged(){
        obs.setChanged();
    } 
    
    public void clearChanged(){
        obs.clearChanged();
    }
    
    public boolean hasChanged(){
        return obs.hasChanged();
    }
    
    public int countObservers(){
        return obs.countObservers();
    }

}

public static class MachineCompositeObservable extends Observable{

   @Override
   public void setChanged(){
       super.setChanged();
   }
   
   @Override
   public void clearChanged(){
       super.clearChanged();
   }

}
