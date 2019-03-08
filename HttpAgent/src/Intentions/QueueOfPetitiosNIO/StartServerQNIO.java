package Intentions.QueueOfPetitiosNIO;

import java.io.IOException;

/**
 * @Descripción Arranca el servidor de Colas basado en NIO
 * @author      John Faber Florez
 *              Luis Restrepor
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */
public class StartServerQNIO {

    public static void main(String[] args) {
        ServerQNIO sqnio = new ServerQNIO(9000);
        try {
            sqnio.start_server();
        } catch (IOException ex) {
            System.out.println("Error"+ex);
        }
    }
    
}
