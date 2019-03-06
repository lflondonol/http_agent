/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intention;

import httpagent.HttpRequestedPath;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author JohnFlorez
 */
public class QueueOfPetitions {
    
    public static void pushQueuOfPetitions(BufferedReader in ,
                PrintWriter out, OutputStream dataOut,Socket clientConnect,
            HttpRequestedPath httpRequestedPath){
        
        Queue<HttpRequestedPath> queuePetitions = 
                new LinkedList<>();
        
        queuePetitions.add(httpRequestedPath);
        
        ActionI.validateAction(in,out,dataOut,clientConnect,
                   queuePetitions);
        
    }
    
}
