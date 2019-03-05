package Intentions.QueueOfPetitiosBQ;
/**
 * @Descripción Arranque de Servicios de manejo de las cola 
 * @author      John Faber Florez
 *              Luis Restrepo
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class StarQServiceProdCons {
    public static void StarQServiceProdCons() {
        //Creación de Cola de Mensajes de 25 posicciones
        BlockingQueue<QMessage> queueMsg = new ArrayBlockingQueue<>(25);
        QProducer QProd = new QProducer(queueMsg);
        QConsumer QCons = new QConsumer(queueMsg);
        //Inicializa el productor de mensajes de la Cola
        new Thread(QProd).start();
        
        //Inicializa el consumidor de mensajes de la cola
        new Thread(QCons).start();
    }
}