
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Javier Aros
 */
public class AFD {
    ArrayList<EstadoAFD> estados;
    char[] alfabeto;
    ArrayList<TransicionAFD> transiciones;
        
    public AFD(AFND afnd, char[] alfabeto)
    {
        this.alfabeto = alfabeto;
        this.estados = new ArrayList<>();
        this.transiciones = new ArrayList<>();
        
        ArrayList<Estado> estados = Epsilon(afnd, afnd.getEstadoInicial());
        EstadoAFD Q0 = new EstadoAFD(this.estados.size(), false, estados); // estado inicial del AFD
        
        this.estados.add(Q0);  
        
        while (existeNoMarcado()){
            EstadoAFD T = obtenerNoMarcado();
            T.setMarcado(true);
            for (char a : this.alfabeto) {
                ArrayList<Estado> destinos = buscarNodosDestino(afnd, T.getEstadosContenidos(), a);
                ArrayList<Estado> res = Epsilon(afnd, destinos);  // res = null
                EstadoAFD nuevo = new EstadoAFD(this.estados.size(), false, res);
                if (!existeEstado(nuevo.getEstadosContenidos())){
                    this.estados.add(nuevo);
                    this.transiciones.add(new TransicionAFD(T, nuevo, a));
                }
                else {   // existe
                    
                    this.transiciones.add(new TransicionAFD(T, getEstado(this.estados, nuevo.getEstadosContenidos()), a));
                }
                    
                    //this.transiciones.add(new TransicionAFD(T, T, a));
            }
        }
        
    }
    
    

    // Retorna la lista de estados a los que se puede llegar en una transicion en vacío
    private ArrayList<Estado> Epsilon(AFND afnd, Estado estadoInicial) {
        ArrayList<Integer> marcados = new ArrayList<>();
        marcados.add(estadoInicial.getId());
        ArrayList<Estado> res = buscarNodosDestino(afnd, estadoInicial, '_');   // 1, 3
        Estado e;
        for(int i = 0 ; i <res.size();i++)
        {
            e = res.get(i);
            if (!marcados.contains(e.getId())){
                marcados.add(e.getId());
                ArrayList<Estado> tmp = buscarNodosDestino(afnd, e, '_');
                for(Estado a:tmp)
                {
                    if(!res.contains(a))
                    {
                        res.add(a);
                    }
                }
            }
        }
        return res;
    }

    // Retorna la lista de estados a los que se puede llegar desde ESTADO consumiendo C
    private ArrayList<Estado> buscarNodosDestino(AFND afnd, Estado estado, char c) {
        ArrayList<Estado> resultado = new ArrayList<>();
        for (Transicion t : afnd.getTransiciones()) {
            if (t.getEstadoOrigen().equals(estado) && t.getCaracter() == c){
                resultado.add(t.getEstadoLlegada());
            }
        }
        if (c == '_')
            resultado.add(estado);
        return resultado;
    }
    
    // Retorna los estados a los que se puede llegar desde alguno de los estados incluidos en ESTADOS
    private ArrayList<Estado> buscarNodosDestino(AFND afnd, ArrayList<Estado> estados, char c){
        ArrayList<Estado> resultado = new ArrayList<>();
        for (Estado e : estados){                          // para cada estado
            for (Transicion t : afnd.getTransiciones()) {  // revisamos si nos lleva a algun destino consumiendo C
                if (t.getEstadoOrigen().equals(e) && t.getCaracter() == c){
                    resultado.add(t.getEstadoLlegada());
                }
            }
        }
        return resultado;
    }

    // Retorna verdadero en caso de que haya un estado no marcado
    private boolean existeNoMarcado() {
        for (EstadoAFD e : this.estados){
            if (e.isMarcado() == false)
                return true;
        }
        return false;
    }

    // Retorna el primer estado no marcado
    private EstadoAFD obtenerNoMarcado() {
        for (EstadoAFD e : this.estados){
            if (e.isMarcado() == false)
                return e;
        }
        return null;
    }

    // Retorna el resultado de aplicar Epsilon a cada uno de los estados de ESTADOS
    private ArrayList<Estado> Epsilon(AFND afnd, ArrayList<Estado> estados) {
        ArrayList<Estado> resultado = new ArrayList<>();
        for (Estado e : estados){
            resultado.addAll(Epsilon(afnd, e));
        }
        

        return resultado;
    }

    // Verdadero en caso que haya un estado del AFD que tenga los mismos estados contenidos
    private boolean existeEstado(ArrayList<Estado> res) {
        for (EstadoAFD e : this.estados) {
            if (e.getEstadosContenidos().equals(res))
                return true;
        }
        return false;
    }
    
    public void imprimir(){
        System.out.println("-------------");
        System.out.println("estados AFD: " + this.estados.size());
        System.out.println("-------Estados--------------");
        for (EstadoAFD e : this.estados){
            System.out.print("" + e.getId() + " contiene a ");
            e.imprimirEstadosContenidos();
        }
        System.out.println("-------Transiciones---------");
        System.out.println("transiciones AFD: " + this.transiciones.size());
        for (TransicionAFD t : this.transiciones){
            t.imprimir();
        }
        System.out.println("-------------");
    }

    private EstadoAFD getEstado(ArrayList<EstadoAFD> estados, ArrayList<Estado> estadosContenidos) {
        for (EstadoAFD e : estados){
            if (e.getEstadosContenidos().equals(estadosContenidos))
                return e;
        }
        return null;
    }

    public ArrayList<TransicionAFD> getTransiciones() {
        return transiciones;
    }
    
    
}
