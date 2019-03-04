package Intentions.QueueOfPetitiosBQ;
/**
 * @Descripción Este servicio consume los servicios de la cola 
 * @author      John Faber Florez
 *              Luis Restrepor
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */

import java.util.concurrent.BlockingQueue;

public class QConsumer implements Runnable{
    private final BlockingQueue<QMessage> queueMsg;
    
    public QConsumer(BlockingQueue<QMessage> qMsg){
        this.queueMsg = qMsg;
    }

    @Override
    public void run() {
        try{
            QMessage qMsg;
            //Consumiendo mensajes hasta un exit
            while(!"exit".equals((qMsg = queueMsg.take()).QGetMsg())){
            Thread.sleep(10);
            System.out.println("Consumed "+qMsg.QGetMsg());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}