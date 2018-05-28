//EXAMEN segunda parte SOLUCIONADO RECUPERACIO
//UDL 2015/2016: AMPLIACIÓ DE BASES DE DADES I ENGINYERIA DEL PROGRAMARI
//Esta hecho sobre papel, no esperar que compile ya que faltan clases

package patronesudlejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Alberto Susin
 */

public class examen_1516_recu {}

//El patron Composite permite estructuras en arbol
//El patron Observer permitira detectar cambios de estado
/////Observer ira sobre MachineComposite para mirar a sus componentes
///// Observable serán todas las instancias de machine

public abstract class MachineComponent extends Observable{

    protected boolean broken = false;
    
    //Rompe y notifica solo cuando broken era falso.
    public void setBroken(){
        if (!broken){
            broken = true;
            notifyChanges();
        }
    }
    
    //Repara y notifica solo cuando estaba roto
    public void repair(){
        if(broken){
            broken = false;
            notifyChanges();
        }
    }
    
    //Sera diferente para Machine y Composite, cada uno lo definira  en su clase
    public abstract boolean isBroken();
    
    //Metodo simple para agrupar que notifique al observer
    public void notifyChanges(){
        setChanged();
        notifyObservers();
    }
}

//CLASE Machine que representa un solo nodo y siempre sera un leaf de una estructura de arbol
public class Machine extends MachineComponent{

    //Devuelve el estado de la maquina, no hay mas.
    public boolean isBroken(){return broken;}

}

//CLASE Composite que es un array de otras Composites o Machines.
public class MachineComposite extends MachineComponent{

    private boolean broken = false;
    private List<MachineComponent> components = new ArrayList<>();
    private int BrokenSubComponents = 0; //Nº de componentes rotos

    public void addComponent(MachineComponent mc){
        mc.addObserver((Observer) this);
        components.add(mc);
        
        if(mc.isBroken()){
            mc.notifyChanges();
            addBrokenSubComponent();
        }
    }
    
    //Update es llamada cuando se produce un cambio y haremos lo correspondiente
    //1. Cuando se rompe  2. Cuando se repara
    public void update (Observer o, Object arg){
    
        MachineComponent mc = (MachineComponent) o;
        
        if (mc.isBroken()){
            addBrokenSubComponent();
            
        }else{
            addRepariedSubComponent();
        }
    
    }
    //Para ser Broken = Con que su propio estado o una maquina lo sea -> Broken
    //Para que !Broken = Su propio estado y el de todos sus componentes debe ser falso.
    @Override
    public boolean isBroken(){
        
        return broken && BrokenSubComponents>0;   
    }
    
    public void addBrokenSubComponent(){
           
        BrokenSubComponents++;
        System.out.println("Se añadio un Componente roto");    
    }
    
    public void addRepariedSubComponent(){
           
        BrokenSubComponents--;
        System.out.println("Se reparo un componente que estaba roto");
    }
}

// CLASE interfaz grafica para ver cuando hay un cambio de estado
// Esta clase ponerla en un fichero de test ya que es muy util para comprobar facilmente

public class GraphicInterface implements Observer{
    protected boolean notified = false;

    @Override
    public void update(Observable o, Object arg) {
        notified = true;
    }
    
}

