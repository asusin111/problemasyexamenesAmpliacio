
//EXAMEN segunda parte SOLUCIONADO RECUPERACIO
//UDL 2015/2016: AMPLIACIÓ DE BASES DE DADES I ENGINYERIA DEL PROGRAMARI

package patronesudlejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Fran,Oriol,Alberto
 */
public class examen_1617_rec {
} 


public class LinesIterator implements Iterator<String>{
    private final BufferedReader br;
    private String nextElement;
    
    public LinesIterator(BufferedReader reader){
        this.br = reader;
        nextElement();
    }
    
    public void nextElement(){          //Leemos la siguiente linea
    
        try {
            nextElement = br.readLine(); //Almacenamos la linea
            
        }catch(IOException IO){
            nextElement = null;          //No hay nada mas en el fichero
        }
    }
    
    @Override
    public boolean hasNext(){
        
        return nextElement != null;      //Retorna SI/NO hay mas elementos
    }
    
    @Override
    public String next(){
        if(nextElement == null){
            throw new NoSuchElementException();
        }
        String next = nextElement;      //Guardamos el actual
        nextElement();                  //Dejamos apuntados al siguiente elemento
        return next;                    
    }
    
    @Override
    public void remove(){               //No se pueden eliminar elementos
        throw new UnsupportedOperationException("Invalid operation");
    }
    
    
    //Itera todo una secuencia con un iterador
    //E : es un object general (todo vale)
    public abstract class SequenceProcessor<E>{
    
        public void processsSequence(Iterator<E> iterator) {
            
            while(iterator.hasNext()){
                E element = iterator.next();
                processElement(element);    //tratamos el elemento
            }
        }
        //El processElement sera diferente para tratar el counter o la suma
        protected abstract void processElement(E element); 
    }
    
    //TRATAMIENTO PARA UN CONTADOR
    public class Counter extends SequenceProcessor<String>{
    
        private int counter = 0;
        
        @Override
        protected void processElement(String element){
            counter += 1;
        }
        
        public int getCounter(){
            return counter;
        }
    }
    

    //TRATAMIENTO PARA ACUMULAR SUMAS
    public class MeanLength extends SequenceProcessor<String>{
    
        private int sumAcumulator = 0;
        private int counter = 0;
        
        @Override
        protected void processElement(String element){
            counter += 1;
            sumAcumulator += element.length();      
        }
        
        public int getMeanLength(){
            return sumAcumulator/counter;
        }
    }
    
    //Para hacerlo todo en el mismo SequenceProcesor, es decir, a la vez que
    //suma longitudes que haga un contador
    //Para ello usaremos un composite que agrupe MeanLenght y Counter
    
    public class CompositeProcessor<E> extends SequenceProcessor<E>{
        
        private final List<SequenceProcessor<E>> processors;
        
        public CompositeProcessor(){
            processors = new ArrayList<>(); //Creamos lista vacia
        }
        
        public void addProcessor(SequenceProcessor<E> processor){
        
            processors.add(processor); //Añade MeanLenght y counter cada vez
        }
        
        protected void processElement(E element){
            
            for(SequenceProcessor<E> each: processors){
                each.processElement(element);
            }
        }
    }
    /* Curso aprender Java desde cero aprenderaprogramar.com */

    public static void main (String [ ] args) {

        BufferedReader reader; //.... aqui iria donde va a leer
        Iterator<String> lineIterator = new LinesIterator(reader);
        
        CompositeProcessor<String> composite = new CompositeProcessor<>();
 
        Counter counter = new Counter();
        MeanLength meanlength = new MeanLength();
        
        //Añadimos al compuesto los 2 recorridos y el iterator
        composite.addProcessor(counter);
        composite.addProcessor(meanlength);
        composite.processsSequence(lineIterator); //Se pone a iterar
        
        System.out.printf("Number of lines: %d",counter.getCounter());
        System.out.printf("MeanLength %f ",meanlength.getMeanLength());
    }

}
