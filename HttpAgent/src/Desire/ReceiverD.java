/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
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

    

    public static String callReceiver(BufferedReader in,Socket connect) {
        
        
        try {
            
            pathRequest = in.readLine();
            System.err.println("Http Request:"+pathRequest);     
        
        } catch (IOException ex) {
            Logger.getLogger(ReceiverD.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
        
        return pathRequest;
        
    }    
}
