package patronesudlejercicios.examen_1617_prob3;

import patronesudlejercicios.examen_1617_prob3.MachineComponent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alberto Susin
 */

// MODO 2: LAZY INITIALIZATION
public class Register2{

    private static Register2 INSTANCE = null;
    private final Map<String,MachineComponent> machines;
    
    public static Register2 getInstance(){
    
        if(INSTANCE == null){
            INSTANCE = new Register2();
        }
        return INSTANCE;
    }
    
    private Register2() {
        machines = new HashMap<>();
    }
    
    public void addComponent(String name, MachineComponent mc){
        machines.put(name, mc);
    }
    
    public void getComponent(String name){
        machines.get(name);
    
    }
}