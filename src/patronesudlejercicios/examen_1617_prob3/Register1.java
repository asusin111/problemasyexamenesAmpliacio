package patronesudlejercicios.examen_1617_prob3;

import patronesudlejercicios.examen_1617_prob3.MachineComponent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alberto Susin
 */

// MODO 1: EAGER INITIALIZATION
public class Register1 {

    public static final Register1 INSTANCE = new Register1();
    private final Map<String,MachineComponent> list_regs;
    
    private Register1() {  
        list_regs = new HashMap<>();
    }
    
    public void addComponent(String name, MachineComponent mc){
        list_regs.put(name, mc);
    }
    
    public MachineComponent getComponent(String name){  
        return list_regs.get(name);
    }
}
