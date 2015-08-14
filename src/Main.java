
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length != 2){
            System.out.println("Uso: java Main archivo ER");
            System.exit(0);
        }
        
        String nombreArchivo = args[0];
        String expresion = args[1];
        try{
        nombreArchivo = args[0];
        expresion = args[1];
        }
        catch(Exception e) {
            System.out.println("Excepcion en los argumentos: " + e);
          }
        
        //System.out.println("archivo: " + nombreArchivo);
        //System.out.println("expresion: " + expresion);
        
        
        ProcesadorER p = new ProcesadorER();

        AFND afnd = p.procesar(expresion);
        
        //System.out.println("----AFND--------");
        //afnd.imprimir();
                
        char[] alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        AFD afd = new AFD(afnd, alfabeto);  
        //System.out.println("----AFD----------");
        //afd.imprimir();       
        
        
        SeguimientoAFD seguimientoAFD = new SeguimientoAFD(afd);
        
        
        
        //System.out.println("Palabras aceptadas por la ER: " + expresion);
        
        try {
            FileInputStream fis = new FileInputStream(nombreArchivo);
            InputStreamReader isr = new InputStreamReader(fis,"utf8");
            BufferedReader br = new BufferedReader(isr);

            String palabra;
            while((palabra = br.readLine()) != null){
                 if (seguimientoAFD.revisarPalabra(palabra))
                      System.out.println(palabra);
            }
            fis.close();
          }
          catch(Exception e) {
            System.out.println("Excepcion procesando fichero "+ nombreArchivo + ": " + e);
          }
//         
    }
    

}
