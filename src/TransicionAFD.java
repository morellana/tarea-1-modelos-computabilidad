/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
class TransicionAFD {
    private EstadoAFD estadoOrigen;
    private EstadoAFD estadoLlegada;
    private char caracter;

    public TransicionAFD(EstadoAFD estadoOrigen, EstadoAFD estadoLlegada, char caracter) {
        this.estadoOrigen = estadoOrigen;
        this.estadoLlegada = estadoLlegada;
        this.caracter = caracter;
        
    }

    void imprimir() {
        System.out.println(estadoOrigen.getId() + " --- " +  this.caracter + " ---> " + estadoLlegada.getId());
    }

    public EstadoAFD getEstadoOrigen() {
        return estadoOrigen;
    }

    public EstadoAFD getEstadoLlegada() {
        return estadoLlegada;
    }

    public char getCaracter() {
        return caracter;
    }
    
    
}
