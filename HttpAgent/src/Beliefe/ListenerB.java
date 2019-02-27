/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beliefe;

import desire.ReceiverD;
import interfaces.httpRequest;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JohnFlorez
 */

public class ListenerB implements Runnable, httpRequest{
    
    static final int PORT = 8087;
    
    private Socket connect;


    public ListenerB() {
    
    }

    public ListenerB(Socket connect) {
        this.connect = connect;
    }
    
    
    
    
    @Override
    public void callServer(ServerSocket serverConnect) {
         try {
            serverConnect = new ServerSocket(PORT);
            System.out.println("Servidor activo. \n Se escucha la conexión por"
                    + " el puerto: "+ PORT +".... \n");
            //we listen until user halts server execution
            while (true) {
                ListenerB miServidor = new ListenerB(serverConnect.accept());
                System.out.println("Se ha abierto la conexión. (" +
                            new Date() + ")");
                
                Thread thread = new Thread(miServidor);
                thread.start();
            }
            
        } catch (IOException ex) {
            System.err.println("Error de conexión al servidor : "+
                    ex.getMessage());
        }
         
            
    }

    @Override
    public void run() {
        
        
    }

    @Override
    public void callReceiver(Socket connect) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


    
}
