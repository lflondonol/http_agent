package beliefe;

import Intention.QueueOfPetitions;
import desire.*;
import httpagent.HttpRequestedPath;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */

public class ListenerB implements Runnable{
    
    private static ServerSocket serverConnect;
    private static Socket clientConnect;
    private static final int PORT = 12345;
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
    
    public static void main(String[] args) throws InterruptedException{
        
        //Encender mi servidor
        clientConnect=callServer(serverConnect);
    }
    
    
    public static Socket callServer(ServerSocket serverConnect) 
            throws InterruptedException {
         try {
            serverConnect = new ServerSocket(PORT);
            System.out.println("Servidor activo. \n Se escucha la conexión por"
                    + " el puerto: "+ PORT +".... \n");
           
            while (true) {              
                ListenerB myServer = new ListenerB(serverConnect.accept());
                ExecutorService ejecutor = Executors.newCachedThreadPool();
                ejecutor.execute(myServer);
                
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
        //Intentamos abrir una conexion de una petición que se ha hecho
        //a nuestro servidor
        try {
            System.out.println("--------------------------------");
            System.out.println("Se ha abierto la conexión. (" +
                    new Date() + ")");
            System.out.println("Cliente Conectado: "+
                    clientConnect.getInetAddress());
            
            in = new BufferedReader(new InputStreamReader(
                    clientConnect.getInputStream()));
            
            out = new PrintWriter(clientConnect.getOutputStream());
            dataOut = new BufferedOutputStream(clientConnect.getOutputStream());
            
            pathRequest = ReceiverD.callReceiver(in,clientConnect);

            System.out.println("Este es el request "+pathRequest);
            
            //Una vez capturado el request se valida en nuestros repo(Mining)
            //Si es una ruta valida.
            pathExists = Mining.pathExistsInBlockContent(clientConnect, 
                pathRequest);
            System.out.println("El path es "+pathExists);
            //Descomponemos la petición para ser procesada, ya sea porque
            //es una ruta rechazada o porque va ser procesada.
            httpRequestedPath = AnswerD.sendMessage(clientConnect,pathExists,
               pathRequest);
            //si la ruta en nuestros repositorios existe va a answer para
            //procesar en la cola la peticion que llega, sino, la rechaza.
            if (pathExists) {
                QueueOfPetitions.pushQueuOfPetitions(in, out, dataOut, 
                        clientConnect, httpRequestedPath);
            }else{
                RejectorB.rejectorMessage(in,out,dataOut,clientConnect, 
                 httpRequestedPath);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ListenerB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
