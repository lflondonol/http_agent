/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beliefe;

import httpagent.HttpRequestedPath;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class AnswerB {
    
    private static PrintWriter out = null; 
    private static BufferedOutputStream dataOut = null;

    public AnswerB() {
    }
    
    public static void responseClient(BufferedReader in,Socket clientConnect, 
            HttpRequestedPath httpRequestedPath, byte[] fileData){
        
        try {      
            String content = "text/html";
            out = new PrintWriter(clientConnect.getOutputStream());
            dataOut = new BufferedOutputStream(clientConnect.getOutputStream());
            out.println("HTTP/1.1 404 File Not Found");
            out.println("Server: Java HTTP Agent Server: 1.0");
            out.println("Date: " + new Date());
            out.println("Content-type: " + content);
            out.println("Content-length: " + httpRequestedPath.getFileRequested());
            out.println(); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
            int fileLength = fileData.length;
            dataOut.write(fileData, 0, fileLength);
            dataOut.flush();

            System.out.println("File " + httpRequestedPath.getFileRequested() + 
                    " not found");
            in.close();			
            out.close();
            dataOut.close();
            clientConnect.close(); // we close socket connection
            System.err.println("Conexion CERRADA!");
        } catch (IOException ex) {
            Logger.getLogger(AnswerB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
