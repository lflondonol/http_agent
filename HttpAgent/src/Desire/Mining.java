/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import desire.AnswerD;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class Mining {
    
    private static boolean pathExists;
    //private AnswerD answerD;

    public Mining() {
    }
    
    public static boolean validateUriRequest(String pathRequested){
       
        String[] separateString = pathRequested.split(" ");
        String verb = separateString[0];
        String uri = separateString[1];

        
    if(255 < uri.length()){
        System.out.println("414 - Request-URI Too Long");
    }
    
    if( !verb.equals("GET")){
        System.out.println("501- Not Implemented");
    }
       
    //Validar URL   System.out.println("400 - Bad Request");
   
    return true;
    }
   
    
    public static boolean pathExistsInBlockChainContent(Socket connect, 
            String pathRequested){
    try {
        if(validateUriRequest(pathRequested)){
            String[] separateString = pathRequested.split(" ");
            String uri = separateString[1];
            File myDir = new File(".");
            File folder = new File(myDir.getAbsolutePath()+"/src/repository/Certifiers/");
            File[] listOfFiles = folder.listFiles();

            int pathFilesFound = 0;
            for(File file: listOfFiles){
                    Scanner scanner;
                
                    scanner = new Scanner(file);

                    
                    int lineNumber = 0;
                    while (scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        lineNumber++;
                        if(uri.equals(line)){
                            pathFilesFound++;
                        }
                    }
            }
            System.out.println("Uri encontrado esta cantidad de veces: "
                    +pathFilesFound);

            pathExists = pathFilesFound >= 1;

        }else{
            pathExists = false;
             }
        } catch (FileNotFoundException ex) {
                    Logger.getLogger(Mining.class.getName()).log(Level.SEVERE, null, ex);
                }
         return pathExists;
    }
    
}