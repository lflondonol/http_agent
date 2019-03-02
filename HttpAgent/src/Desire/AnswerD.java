package desire;

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
    
    
    static final File WEB_ROOT = 
            new File("/Users/JohnFlorez/Developer/http_agent/HttpAgent/src/repository/");
    static final String DEFAULT_FILE = "index.html";
    static final String FILE_NOT_FOUND = "404.html";
    static final String METHOD_NOT_SUPPORTED = "not-supported.html";
    static boolean verbose = true;

    public AnswerD() {
        
    }
    
    public static void sendMessage(Socket clientConnect,boolean pathExists){
        
        System.err.println("ESTOY LISTO PARA ENVIAR MI MENSAJE DE "
                + "\nNO EXISTE EL RECURSO BUSCADO EN MINING DE "+
                "CLIENTE "+clientConnect.getLocalAddress().toString());
            
    }
 
    
}
