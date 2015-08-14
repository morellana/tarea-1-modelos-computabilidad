/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
class Transicion {
    private Estado estadoOrigen;
    private Estado estadoLlegada;
    private char caracter;

    public Transicion(Estado estadoOrigen, Estado estadoLlegada, char caracter) {
        this.estadoOrigen = estadoOrigen;
        this.estadoLlegada = estadoLlegada;
        this.caracter = caracter;
    }

    void imprimir() {
        System.out.println(estadoOrigen.getId() + " --- " +  this.caracter + " ---> " + estadoLlegada.getId());
    }

    public Estado getEstadoOrigen() {
        return estadoOrigen;
    }

    public Estado getEstadoLlegada() {
        return estadoLlegada;
    }

    public char getCaracter() {
        return caracter;
    }
    
    
}
