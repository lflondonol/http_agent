package desire;

import httpagent.HttpRequestedPath;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author JohnFlorez
 */
public class AnswerD {
    
     private static HttpRequestedPath httpRequestedPath = null;
     private static StringTokenizer parse;
     private static String myPathRequest = null;
     
    
    public AnswerD() {
        
    }
    
    public static HttpRequestedPath sendMessage(Socket clientConnect,
            boolean pathExists, String pathRequest){
        
        myPathRequest = pathRequest;
        System.out.println("Se rechaza o se procesa la ruta recibida");
        
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
