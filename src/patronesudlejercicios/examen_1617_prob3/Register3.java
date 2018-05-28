package patronesudlejercicios.examen_1617_prob3;

import patronesudlejercicios.examen_1617_prob3.MachineComponent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alberto Susin
 */

// MODO 3: SYNCHRONIZED
public class Register3{

    private static Register3 register;
    private static final Object obsinc = 5;
    
    private final Map<String,MachineComponent> machines;
    
    public static Register3 getInstance(){
        synchronized(obsinc){            
            if(register == null){
                register = new Register3();
            }
            return register;
        }
    }
    
    private Register3() {
        machines = new HashMap<>();
    }
    
    public void addComponent(String name, MachineComponent mc){
        machines.put(name, mc);
    }
    
    public void getComponent(String name){
        machines.get(name);
    }
}