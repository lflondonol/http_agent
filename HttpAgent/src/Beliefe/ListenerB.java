package beliefe;

import desire.*;
import beliefe.*;
import httpagent.HttpRequestedPath;
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

public class ListenerB implements Runnable {
    
    static final int PORT = 8087;
    
    
    private static Socket clientConnect;
    private static String pathRequest;
    private static boolean pathExists;
    private static HttpRequestedPath httpRequestedPath;




    public ListenerB(Socket connect) {
        this.clientConnect = connect;
    }
    
    
    public static Socket callServer(ServerSocket serverConnect) {
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
        }//try
         
         return clientConnect;
         
            
    }//metodo

    @Override
    public void run() {
        
        System.out.println("Conexion IP: "+
                        clientConnect.getLocalAddress().toString());
        
        System.err.println("Conexion "+clientConnect.getLocalAddress().toString());
        
        pathRequest = ReceiverD.callReceiver(clientConnect);
               
        /*
        //Aca iría includo el llamado al componente Mining
        */
        
       httpRequestedPath = AnswerD.sendMessage(clientConnect,pathExists,
               pathRequest);
       
       RejectorB.rejectorMessage(clientConnect, httpRequestedPath);
       
        
    }

    
}
