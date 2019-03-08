package beliefe;

import httpagent.HttpRequestedPath;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class RejectorB {
    
    
    private static byte[] fileData = null;
    
    public RejectorB() {
    }

    
    public static void rejectorMessage(BufferedReader in ,
                PrintWriter out, OutputStream dataOut,Socket clientConnect,
            HttpRequestedPath httpRequestedPath){
        
        try {
            AnswerB.
                    fileNotFound(in,out,dataOut,
                            httpRequestedPath.getFileRequested(),
                            clientConnect, httpRequestedPath.getMethod());
            
            
        } catch (IOException ex) {
            Logger.getLogger(RejectorB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
