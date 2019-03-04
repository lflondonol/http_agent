/*
 * Sevidor Http básico bajo el concepto de agentes BDI y blockchain.
 */
package httpagent;

import beliefe.*;
import desire.*;
//import intention.*;
import java.net.ServerSocket;
import java.net.Socket;

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
            
//            HttpAgent httpA = new HttpAgent();
//            
//            Thread thread = new Thread(httpA);
//            thread.start();
            ListenerB.callServer(serverSocket);

    }

    @Override
    public void run() {
        //clientConnect = ListenerB.callServer(serverSocket);
        
    }
    
}
