
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
public class AFND {
    private ArrayList<Estado> estados;          // K
    private Estado estadoInicial;               // s
    private Estado estadoFinal;                 // todos los finales conectados a este estado. auxiliar
    private ArrayList<Transicion> transiciones; // relacion de transicion

    public AFND(ArrayList<Estado> estados, String alfabeto, Estado estadoInicial, ArrayList<Estado> estadosFinales, ArrayList<Transicion> transiciones) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.transiciones = transiciones;
    }
    
    // Crea un AFND a partir de un caracter    
    public AFND(char c){
        this.estados = new ArrayList<>();
        this.transiciones = new ArrayList<>();
        
        this.estadoInicial = new Estado(0, false);
        this.estadoFinal = new Estado(1, true);
        
        this.estados.add(estadoInicial);
        this.estados.add(estadoFinal);
        this.transiciones.add(new Transicion(estadoInicial, estadoFinal, c));
    }
    
    // Agrupa 2 AFND's en base a Thompson(a.b)
    public AFND and(AFND a){
        a.incrementarIDs(this.estados.size());
        Transicion conexion = new Transicion(this.estadoFinal, a.estadoInicial, '_'); // transicion epsilon para unir los dos AFNDs
        this.transiciones.add(conexion);
       
        // todas las transiciones del AFND "a" se agregan a la instancia actual
        for (Transicion t : a.transiciones) {
            this.transiciones.add(t);
        }
        
        // todos los estado del AFND "a" se agregan a la instancia actual
        this.estadoFinal.setEsFinal(false);
        this.estadoFinal = a.estadoFinal;
        for (Estado e : a.estados)
        {
            e.setEsFinal(false);
            this.estados.add(e);
        }
        this.estadoFinal.setEsFinal(true);
        
        
        return this;
    }
    
    // Agrupa 2 AFND's en base a Thomson(a|b)
    public AFND or (AFND a){
        Estado nuevoEstadoInicial = new Estado(this.estados.size(), false);
        estados.add(nuevoEstadoInicial);
        Transicion conexion = new Transicion(nuevoEstadoInicial,this.estadoInicial, '_'); // transicion epsilon para unir los dos AFNDs
        this.transiciones.add(conexion);
        conexion = new Transicion(nuevoEstadoInicial,a.estadoInicial, '_');
        this.transiciones.add(conexion);
        this.estadoInicial = nuevoEstadoInicial;
        Estado nuevoEstadoFinal = new Estado(this.estados.size(), true);
        estados.add(nuevoEstadoFinal);
        
        conexion = new Transicion(this.estadoFinal,nuevoEstadoFinal, '_'); // transicion epsilon para unir los dos AFNDs
        this.transiciones.add(conexion);
        conexion = new Transicion(a.estadoFinal,nuevoEstadoFinal, '_');
        this.transiciones.add(conexion);
        
        a.incrementarIDs(this.estados.size());
         for (Transicion t : a.transiciones) {
            this.transiciones.add(t);
        }
        
        // todos los estado del AFND "a" se agregan a la instancia actual
        for (Estado e : a.estados) {
            this.estados.add(e);
        }
        
        this.estadoFinal = nuevoEstadoFinal;
        return this;
    }
    
    // Aplica clausa de Kleene al AFND
    public AFND kleene(){
        this.incrementarIDs(1);       
        Estado s = new Estado(0, false);                    // nuevo estado inicial
        Estado q = new Estado(this.estados.size()+1, true); // nuevo estado final
        
        Transicion t = new Transicion(this.estadoFinal, this.estadoInicial, '_');
        Transicion t1 = new Transicion(s, this.estadoInicial, '_');
        Transicion t2 = new Transicion(s, q, '_');
        Transicion t3 = new Transicion(this.estadoFinal, q, '_');
                
        this.transiciones.add(t);
        this.transiciones.add(t1);
        this.transiciones.add(t2);
        this.transiciones.add(t3);
        
        this.estados.add(s);
        this.estados.add(q);
        
        this.estadoInicial = s;
        this.estadoFinal = q;
        return this;
    }
    
    // imprime un AFND, para hacer seguimiento
    public void imprimir(){
        System.out.println("estados: " + this.estados.size());
        System.out.println("transiciones: ");
        for (Transicion t : this.transiciones) {
            t.imprimir();
        }
        System.out.println("estado inicial: " + this.estadoInicial.getId());
        System.out.println("estado final: " + this.estadoFinal.getId());
    }
    
    // incrementa los identificadores de los nodos en x unidades
    private void incrementarIDs(int x){
        for (Estado e : this.estados) {
            e.setId(e.getId() + x);
        }
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }
  
    
    
}
