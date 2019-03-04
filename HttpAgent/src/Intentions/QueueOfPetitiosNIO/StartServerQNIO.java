package Intentions.QueueOfPetitiosNIO;

import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * @Descripción Arranca el servidor de Colas basado en NIO
 * @author      John Faber Florez
 *              Luis Restrepor
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */
public class StartServerQNIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerQNIO sqnio = new ServerQNIO(9000);
        try {
            sqnio.start_server();
        } catch (IOException ex) {
            System.out.println("error"+ex);
        }
    }
    
}
