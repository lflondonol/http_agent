/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intention;

import beliefe.AnswerB;
import static beliefe.AnswerB.getContentType;
import httpagent.HttpRequestedPath;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author JohnFlorez
 */
public class ActionI {
    
    public static boolean verbose = true;
    
    
    public static void validateAction(BufferedReader in ,
                PrintWriter out, OutputStream dataOut,Socket clientConnect,
            Queue queueOfPetitions){
        
        List<String> httpVerbs = 
                Arrays.asList("GET","POST","PUT","DELETE","OPTIONS",
                        "HEAD","TRACE","CONNECT");
        HttpRequestedPath httpRequest = new HttpRequestedPath();
        
    try {
        
        //se desencola el request para obtener el metodo y la ruta
        //a verificar
        httpRequest = (HttpRequestedPath) queueOfPetitions.remove();
        System.err.println("ESTE ES MI METODO "+httpRequest.getMethod());
        System.err.println("ESTA ES MI RUTA "+httpRequest.getFileRequested());
        // we support only GET and HEAD methods, we check
        if (!httpVerbs.contains(httpRequest.getMethod())) {
            System.out.println("ENTRE A NO SOPORTADOOOOS");
                if (verbose) {
                    
                    AnswerB.methoNotSupported(in, out, dataOut, 
                            httpRequest.getFileRequested()
                            , clientConnect);
               }

        } else if(httpVerbs.contains(httpRequest.getMethod())&&
                (httpRequest.getFileRequested().equals("/")||
                httpRequest.getFileRequested().equals("/index.html"))){
                
                
                
                System.out.println("ENTRE A METODOS SOPORTADOS");
                 AnswerB.fileFound(in, out, dataOut, 
                         httpRequest.getFileRequested(), 
                         clientConnect, 
                         httpRequest.getMethod());

            }else{
                System.out.println("ENTRE A RUTA NO VALIDA");
                AnswerB.fileNotFound(in,out,dataOut,
                            httpRequest.getFileRequested(),
                            clientConnect,httpRequest.getMethod());
            }

            } catch (FileNotFoundException fnfe) {
                    try {
                            AnswerB.fileNotFound(in,out,dataOut,
                            httpRequest.getFileRequested(),
                            clientConnect,httpRequest.getMethod());
                    } catch (IOException ioe) {
                            System.err.println("Error with file not "
                                    + "found exception : " + ioe.getMessage());
                    }

            } catch (IOException ioe) {
                    System.err.println("Server error : " + ioe);
            } finally {
                    try {
                            in.close();
                            out.close();
                            dataOut.close();
                            //clientConnect.close(); 
                    } catch (Exception e) {
                            System.err.println("Error closing stream : " 
                                    + e.getMessage());
                    } 

                    if (verbose) {
                            System.out.println("Connection closed.\n");
                    }
            }
        
    }
    
}
