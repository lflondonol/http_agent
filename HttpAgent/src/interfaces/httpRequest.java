/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import beliefe.ListenerB;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author JohnFlorez
 */
public interface httpRequest {
    
    public void callServer(ServerSocket s);
    
    public void callReceiver(Socket connect);
    
    public void callRejector();
         
}
