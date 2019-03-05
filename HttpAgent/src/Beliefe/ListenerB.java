package beliefe;

import Intention.QueueOfPetitions;
import Intentions.QueueOfPetitiosBQ.QMessage;
import Intentions.QueueOfPetitiosBQ.QProducer;
import Intentions.QueueOfPetitiosBQ.StarQServiceProdCons;
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
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */

public class ListenerB implements Runnable {
    
    static final int PORT = 8099;
    
    
    private static Socket clientConnect;
    private static String pathRequest;
    private static boolean pathExists;
    private static HttpRequestedPath httpRequestedPath;
    private static byte[] fileData;
    private static BufferedReader in = null;
    private static PrintWriter out = null; 
    private static BufferedOutputStream dataOut = null;




    public ListenerB(Socket connect) {
        this.clientConnect = connect;
    }
    
    
    public static Socket callServer(ServerSocket serverConnect) throws InterruptedException {
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
                //Thread.sleep(30);
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
                        clientConnect.getInetAddress().toString());
        
        System.err.println("Conexion "+clientConnect.getLocalAddress().toString());
        
        try {
            in = new BufferedReader(new InputStreamReader(
                    clientConnect.getInputStream()));
            out = new PrintWriter(clientConnect.getOutputStream());
            dataOut = new BufferedOutputStream(clientConnect.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ListenerB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pathRequest = ReceiverD.callReceiver(in,clientConnect);
               
        try {
            pathExists = Mining.pathExistsInBlockChainContent(clientConnect, pathRequest);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ListenerB.class.getName()).log(Level.SEVERE, null, ex);
        }

        
       httpRequestedPath = AnswerD.sendMessage(clientConnect,pathExists,
               pathRequest);
       
       //if (pathExists) {
           QueueOfPetitions.pushQueuOfPetitions(in,out,dataOut,clientConnect,
                   httpRequestedPath);
              
        //}else{
         //RejectorB.rejectorMessage(in,out,dataOut,clientConnect, 
           //      httpRequestedPath);
        //}
       
       
         
       //AnswerB.responseClient(in,clientConnect, httpRequestedPath, fileData);
       
        
    }

    
}
