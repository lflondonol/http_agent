/*
 * Sevidor Http básico bajo el concepto de agentes BDI y blockchain.
 */
package httpagent;

import beliefe.*;
//import intention.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Faber Flórez Vasco
 * @author Luisa Fernanda Restrepo Gutierrez
 * @author Luis Fernando Londoño Londoño
 * 
 */
public class HttpAgent implements Runnable{

    /**
     * @param args the command line arguments
     */
    private static ServerSocket serverSocket; 
    private static Socket clientConnect;
    private static String pathRequest;
    private static boolean pathExists;

    public HttpAgent() {
       
    }
   
    
    
    public static void main(String[] args) {
            
            HttpAgent httpA = new HttpAgent();
            
            Thread thread = new Thread(httpA);
            thread.start();
            //ListenerB.callServer(serverSocket);
            //Inicializar la cola de mensajes
            //StarQServiceProdCons.StarQServiceProdCons();
            //Queue q = new Queue();

    }

    @Override
    public void run() {
        try {
            clientConnect = ListenerB.callServer(serverSocket);
        } catch (InterruptedException ex) {
            Logger.getLogger(HttpAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
