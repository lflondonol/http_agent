package beliefe;

import desire.ReceiverD;
import interfaces.httpRequest;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
        
        System.out.println("Conexion IP: "+
                        connect.getLocalAddress().toString());
        ReceiverD rd = new ReceiverD();
        rd.callReceiver(connect);
        
    }

    @Override
    public void callReceiver(Socket connect) {
        
    }

    @Override
    public void callRejector() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


    
}
