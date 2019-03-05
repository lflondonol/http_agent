package Intentions.QueueOfPetitiosBQ;

/**
 * @Descripción Servicio produce los mensajes de la cola 
 * @author      John Faber Florez
 *              Luis Restrepo
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */

import desire.AnswerD;
import java.util.concurrent.BlockingQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class QProducer implements Runnable {
    private String msgQ;
    private final BlockingQueue<QMessage> queueMsg;
       
    public QProducer(BlockingQueue<QMessage> qMsg){
        this.queueMsg = qMsg;
    }

    

    public String getMsgQ() {
        return msgQ;
    }

    public void setMsgQ(String msgQ) {
        this.msgQ = msgQ;
    }
    
    
    
    @Override
    public void run() {
        //Para producir mensajes
        while(true) {    
            QMessage qMsg;
            
            //AnswerD ad = new AnswerD();
            
            msgQ = AnswerD.getPathRequest();
           
        try {
            //System.out.println ("Mensaje:");
            //msgQ = "";
            //Scanner entradaEscaner = new Scanner (System.in);
            //msgQ = entradaEscaner.nextLine (); 
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(QProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
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

