/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Javier Aros
 */
public class SeguimientoAFD {
    AFD afd;
    public SeguimientoAFD(AFD afd)
    {
        this.afd = afd;
    }
    
    public EstadoAFD desplazar(EstadoAFD estadoInicial, char caracter)
    {
        for (TransicionAFD trancicion: afd.getTransiciones())
        {
            if (trancicion.getEstadoOrigen() == estadoInicial &&  caracter == trancicion.getCaracter())
            {
                return trancicion.getEstadoLlegada();
            }
        }
        
        return estadoInicial;
    }
    
    public boolean revisarPalabra(String palabra)
    {   
        EstadoAFD estadoActual = afd.estados.get(0); //revisar
        char caracter;
        while(palabra.length() != 0)
        {
            caracter = palabra.charAt(0);
            if (palabra.length() != 1)
            {
                palabra = palabra.substring(1);
            }
            else
            {
                palabra = "";
            }
            estadoActual = desplazar(estadoActual,caracter);
        }
        if (estadoActual.isEsFinal())
        {
            return true;
        }
        return false;
    }
    
   
}
