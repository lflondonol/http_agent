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

    private static BufferedReader in = null; 
    private static PrintWriter out = null; 
    private static BufferedOutputStream dataOut = null;
    
    public RejectorB() {
    }

    
    public static void rejectorMessage(Socket clientConnect,
            HttpRequestedPath httpRequestedPath){
        
        try {
            out = new PrintWriter(clientConnect.getOutputStream());
            dataOut = new BufferedOutputStream(clientConnect.getOutputStream());
            httpRequestedPath.fileNotFound(out, dataOut,
                    httpRequestedPath.getFileRequested());
        } catch (IOException ex) {
            Logger.getLogger(RejectorB.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
    }
}
