package Intentions.QueueOfPetitiosBQ;

/**
 * @Descripción Servicio produce los mensajes de la cola 
 * @author      John Faber Florez
 *              Luis Restrepo
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */

import java.util.concurrent.BlockingQueue;
import java.util.Scanner;
        
public class QProducer implements Runnable {
    private String msgQ;
    private final BlockingQueue<QMessage> queueMsg;
       
    public QProducer(BlockingQueue<QMessage> qMsg){
        this.queueMsg = qMsg;
    }
    
    @Override
    public void run() {
        //Para producir mensajes
        while(!"exit".equals(msgQ)) {    
            QMessage qMsg;
           
            System.out.println ("Mensaje:");
            msgQ = "";
            Scanner entradaEscaner = new Scanner (System.in); 
            msgQ = entradaEscaner.nextLine (); 
            System.out.println ("Entrada recibida por teclado es: \"" + msgQ +"\"");            
            qMsg = new QMessage(msgQ);            
            
            try {
                queueMsg.put(qMsg);
                System.out.println("Mensaje traido de la cola "+qMsg.QGetMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}    

