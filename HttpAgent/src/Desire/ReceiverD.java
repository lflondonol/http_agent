/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import beliefe.ListenerB;
import interfaces.httpRequest;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnFlorez
 */
public class ReceiverD implements httpRequest{
    
    private Socket connect;
    private ServerSocket myServer;
    //private Mining mining;
    

    public ReceiverD() {
    }

    

    @Override
    public void callReceiver(Socket connect) {
        
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String pathRequest = null;
        
        try {
            in = new BufferedReader(new InputStreamReader(
            connect.getInputStream()));
            dataOut = new BufferedOutputStream(connect.getOutputStream());
            String input = in.readLine();
            System.out.println("lo que llego fue :"+input);
            /*
            En esta parte iría el componente de mining, se captura lo que llego
            recuerden que la entrada lo que recibe es un STRING separado asi
            METODO[ESPACIO]SOLICITUD , Ejemplo1 -> GET / 
            Ejemplo2 -> GET /index.html las cuales son las caracteristicas
            suficientes para validar en el concepto de BlockChain si es una 
            ruta valida, me imagino que esto nos debe retornar un booleano
            entonces instanciaríamos el método validator de la clase Mining
            */
            Mining mining = new Mining();
            mining.pathExistsInBlockChainContent(connect, input);
            
        
        } catch (IOException ex) {
            Logger.getLogger(ReceiverD.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }

        
    }

    @Override
    public void callServer(ServerSocket s) {

    }

    @Override
    public void callRejector() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}
