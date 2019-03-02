/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import beliefe.ListenerB;
import desire.Mining;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class ReceiverD{
    
    private static String pathRequest;
    

    public ReceiverD() {
    }

    

    public static String callReceiver(Socket connect) {
        
        BufferedReader in = null;
        System.out.println("ddddd");
        
        try {
            in = new BufferedReader(new InputStreamReader(
            connect.getInputStream()));
            pathRequest = in.readLine();
            System.out.println("Lo que llego fue :"+pathRequest);
            /*
            En esta parte iría el componente de mining, se captura lo que llego
            recuerden que la entrada lo que recibe es un STRING separado asi
            METODO[ESPACIO]SOLICITUD , Ejemplo1 -> GET / 
            Ejemplo2 -> GET /index.html las cuales son las caracteristicas
            suficientes para validar en el concepto de BlockChain si es una 
            ruta valida, me imagino que esto nos debe retornar un booleano
            entonces instanciaríamos el método validator de la clase Mining
            */
            //Mining.pathExistsInBlockChainContent(connect, input);
            
            
            
        
        } catch (IOException ex) {
            Logger.getLogger(ReceiverD.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
        
        return pathRequest;
        
    }    
}
