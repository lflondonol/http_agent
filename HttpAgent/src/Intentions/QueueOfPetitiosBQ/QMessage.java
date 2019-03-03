package Intentions.QueueOfPetitiosBQ;

/**
 * @Descripción Declaración de tipo mensaje 
 * @author      John Faber Florez
 *              Luis Restrepo
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */
public class QMessage {
    private final String qMsg;
    
    public QMessage(String qStr){
        this.qMsg = qStr;
    }

    public String QGetMsg() {
        return qMsg;
    }

}    

