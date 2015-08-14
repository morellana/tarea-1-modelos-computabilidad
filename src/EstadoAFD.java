
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
class EstadoAFD extends Estado {
    private ArrayList<Estado> estadosContenidos;
    private boolean marcado;

    public EstadoAFD(int id, boolean esFinal, ArrayList<Estado> estadosContenidos) {
        super(id, esFinal);
        this.estadosContenidos = estadosContenidos;
        this.marcado = false;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public ArrayList<Estado> getEstadosContenidos() {
        return estadosContenidos;
    }
    
    public void imprimirEstadosContenidos(){
        for (Estado e : this.estadosContenidos){
            System.out.print("" + e.getId() + " ");
        }
        System.out.println("");
    }

    public boolean isEsFinal() {
        for(Estado e : this.estadosContenidos){
            if (e.isEsFinal())
                return true;
        }
        return false;
    }
    
    
}
