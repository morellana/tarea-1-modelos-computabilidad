
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
public class ProcesadorER {
 
    static final char SIMBOLO_CONCATENACION = '.';
    static final char SIMBOLO_UNION = '|';
    static final char SIMBOLO_KLEENE = '*';
    static final char SIMBOLO_INICIO_PARENTESIS = '(';
    static final char SIMBOLO_CIERRE_PARENTESIS = ')';
    
    public ProcesadorER(){}
    
    public AFND procesar(String cadena){
        //System.out.println("procesar: " + cadena);
        if (contiene(cadena, SIMBOLO_UNION) != -1){               // si la cadena contiene '.' que no sea dentro de parentesis
            int separador = contiene(cadena, SIMBOLO_UNION);
            //System.out.println(cadena.substring(0, separador) + " OR " + cadena.substring(separador+1, cadena.length()));
            return OR(procesar(cadena.substring(0, separador)),
                       procesar(cadena.substring(separador+1, cadena.length())));
        }
        if (contiene(cadena, SIMBOLO_CONCATENACION) != -1){               // si la cadena contiene '.' que no sea dentro de parentesis
            int separador = contiene(cadena, SIMBOLO_CONCATENACION);
            //System.out.println(cadena.substring(0, separador) + " AND " + cadena.substring(separador+1, cadena.length()));
            return AND(procesar(cadena.substring(0, separador)),
                       procesar(cadena.substring(separador+1, cadena.length())));
        }
        if (contiene(cadena, SIMBOLO_KLEENE) != -1){               // si la cadena contiene '*' que no sea dentro de parentesis
            //System.out.println("kleene a: " + cadena.substring(0, cadena.length()-1));
            return kleene(procesar(cadena.substring(0, cadena.length()-1)));
        }
        if (cadena.contains("" + SIMBOLO_INICIO_PARENTESIS)){
            return procesar(cadena.substring(1, cadena.length()-1));
        }
        return new AFND(cadena.charAt(0));  // si no contiene parentesis, *, . o | es un caracter. caso base.
    }
    
    /*
     * Retorna verdadero en caso de encontrar el caracter C
     * en CADENA y que éste no esté contenido entre parentesis
     * Retorna -1 en caso de no encontrarlo
     */
    public int contiene(String cadena, char c) {
        boolean dentroDeParentesis = false;
        for (int i = 0; i < cadena.length(); i++) {
            char ch = cadena.charAt(i);
            if (ch == SIMBOLO_INICIO_PARENTESIS)
                dentroDeParentesis = true;
            else if (ch == SIMBOLO_CIERRE_PARENTESIS)
                dentroDeParentesis = false;
            if (!dentroDeParentesis && ch == c)
                return i;
        }
        return -1;
    }

    private AFND AND(AFND a, AFND b) {
        return a.and(b);
    }
    
    private AFND OR(AFND a, AFND b) {
        return a.or(b);
    }

    private AFND kleene(AFND a) {
        return a.kleene();
    }

    
    
}