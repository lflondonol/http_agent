/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desire;

import beliefe.ListenerB;
import interfaces.httpRequest;
import java.io.IOException;
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

    public ReceiverD() {
    }

    
    @Override
    public void callServer(ServerSocket s) {
       
    }

    @Override
    public void callReceiver(Socket connect) {
        
       
    }

    
}
