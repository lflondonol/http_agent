package beliefe;

import httpagent.HttpRequestedPath;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
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

    
    public static byte[] rejectorMessage(Socket clientConnect,
            HttpRequestedPath httpRequestedPath){
        
        try {
            fileData=httpRequestedPath.
                    fileNotFound(httpRequestedPath.getFileRequested());
        } catch (IOException ex) {
            Logger.getLogger(RejectorB.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return fileData;
        
    }
}
