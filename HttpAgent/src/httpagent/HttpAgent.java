/*
 * Sevidor Http básico bajo el conceptos de agentes BDI y blockchain.
 */
package httpagent;

import beliefe.ListenerB;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author John Faber Flórez Vasco
 * @author Luisa Fernanda Restrepo Gutierrez
 * @author Luis Fernando Londoño Londoño
 * 
 */
public class HttpAgent {

    /**
     * @param args the command line arguments
     */
    private static ServerSocket serverSocket; 
    
    
    public static void main(String[] args) {
        ListenerB lb = new ListenerB();
        
        lb.callServer(serverSocket);
    }
    
}
