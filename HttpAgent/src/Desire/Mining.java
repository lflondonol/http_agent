/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import java.io.File;
import java.net.Socket;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class Mining {
    
    private static boolean pathExists;

    private static String verb;
    private static String uri;
    private static String[] separateString;

    private static boolean validationResult;

    //private AnswerD answerD;

    public Mining() {
    }
    
    public static boolean pathExistsInBlockContent(Socket connect, 
            String pathRequested){
        if (pathRequested!=null) {
            separateString = pathRequested.split(" ");
        uri = separateString[1];
        File myDir = new File(".");
        File folder = new File(myDir.
                getAbsolutePath()+"/src/repository/Certifiers/");
        File[] listOfFiles = folder.listFiles();

        int pathFilesFound = 0;
        for(File file: listOfFiles){
            try {
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
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Mining.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Uri encontrado esta cantidad de veces: "
                +pathFilesFound);
        
        pathExists = pathFilesFound >= 1;
 
        }
        
        return pathExists;
    }
    
}