package desire;

import httpagent.HttpRequestedPath;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class AnswerD {
    
     private static String fileRequested = null; 
     private static HttpRequestedPath httpRequestedPath = null;
     private static StringTokenizer parse;
     private static String myPathRequest = null;
     
    
    public AnswerD() {
        
    }
    
    public static HttpRequestedPath sendMessage(Socket clientConnect,
            boolean pathExists, String pathRequest){
        
        myPathRequest = pathRequest;
        System.err.println("Capturando el Recurso"
                + " listo para enviar a Rejector o a Cola"
                + " "+clientConnect.getLocalAddress().toString());
        try{
            
            httpRequestedPath = new HttpRequestedPath();
        
            parse = new StringTokenizer(pathRequest);

            httpRequestedPath.setMethod(parse.nextToken().toUpperCase()); 

            httpRequestedPath.setFileRequested(parse.nextToken().toLowerCase());
           getPathRequest();
        
            
        }catch(NullPointerException npe){
            System.out.println(" ");
        }


        return httpRequestedPath;
            
    }
    
    public static String getPathRequest(){
        return myPathRequest;
    }
 
    
}
