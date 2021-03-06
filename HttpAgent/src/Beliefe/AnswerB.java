/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beliefe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
   
    private String fileRequested;
    private String method;
    private static final File MY_DIR = new File(".");
    private static final File WEB_ROOT = new File(MY_DIR.getAbsolutePath()+
            "/src/repository/");
    private static final String DEFAULT_FILE = "index.html";
    private static final String FILE_HELLO = "hello.html";
    private static final String FILE_NOT_FOUND = "404.html";
    private static final String METHOD_NOT_SUPPORTED = "not-supported.html";
    private static final String [] STATUS_CODE_DESCRIPTION 
            = {"501 Method Not Implemented","404 File Not Found",
                "200 OK"};
    
    public AnswerB() {
    }
    
    
    public static PrintWriter HeadersWrited(String content,
            int fileLength, PrintWriter out, int code){
                out.println("HTTP/1.1 "+STATUS_CODE_DESCRIPTION[code]);
		out.println("Server: Java HTTP Server Agent : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + content);
		out.println("Content-length: " + fileLength);
		out.println(); 
		
                //Guardar nuestro archivos de log
                saveLog("HTTP/1.1 "+STATUS_CODE_DESCRIPTION[code]);
                saveLog("Server: Java HTTP Server Agent : 1.0");
                saveLog("Date: " + new Date());
                saveLog("Content-type: " + content);
                saveLog("Content-length: " + fileLength);
                saveLog("\n");
                
        return out;
    }
    
  
    public static void fileNotFound(BufferedReader in ,
                PrintWriter out, OutputStream dataOut, 
                String fileRequested,
                Socket clientConnect,
                String method) 
                throws IOException {
                
        int code = 1;

        File file = new File(WEB_ROOT, FILE_NOT_FOUND);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = readFileData(file, fileLength);
        if (method.equals("HEAD")) {
            out = AnswerB.HeadersWrited(content, fileLength, out, code);
            out.flush();
        }else{
            out = AnswerB.HeadersWrited(content, fileLength, out, code);
            out.flush(); 
            dataOut.write(fileData, 0, fileLength);
            dataOut.flush(); 
        }

        System.out.println("CONEXION CERRADA PETICION ENVIADA....");
		
	}
    
     public static void methoNotSupported(BufferedReader in ,
                PrintWriter out, OutputStream dataOut, 
                String fileRequested,
                Socket clientConnect) 
                throws IOException {
            
        File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = readFileData(file, fileLength);
        
        int code = 0;

        out = AnswerB.HeadersWrited(content, fileLength, out, code);
        out.flush(); // flush character output stream buffer

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();

        System.out.println("CONEXION CERRADA PETICION ENVIADA....");
		
	}
 
        private static byte[] readFileData(File file, int fileLength) 
                throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) 
				fileIn.close();
		}
		
		return fileData;
	}
	
	// return supported MIME Types
	public static String getContentType(String fileRequested) {
		if (fileRequested.endsWith(".htm")  ||  
                        fileRequested.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}
	

        public static void fileFound(BufferedReader in,PrintWriter out, 
                OutputStream dataOut, 
                String fileRequested,
                Socket clientConnect,
                String method
                ) throws IOException {
            
            int code = 2;
            
            if (fileRequested.endsWith("/")) {
                        fileRequested += DEFAULT_FILE;
                }
                
            File file = new File(WEB_ROOT, fileRequested);
            int fileLength = (int) file.length();
            String content = getContentType(fileRequested);


            if (method.equals("GET")||method.equals("PUT")
                    ||method.equals("POST")||method.equals("DELETE")) {
                
                byte[] fileData = readFileData(file, fileLength);
                
                out = AnswerB.HeadersWrited(content, fileLength, out, code);
                out.flush(); 

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
            } else{
                
                out = AnswerB.HeadersWrited(content, fileLength, out, code);
                out.flush(); 
            }               
	}
        
        public static void saveLog(String text){
 
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(MY_DIR.getAbsolutePath()
                                +"/src/repository/Log/Log.txt", true));
                writer.newLine();
                writer.append(text);
               

                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AnswerB.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
	}   
                   
   }
    

