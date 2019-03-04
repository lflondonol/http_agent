package Intentions.QueueOfPetitiosNIO;

/**
 * @Descripción Implementa un servidor de colas basado en NIO, colas no bloqueante
 * @author      John Faber Florez
 *              Luis Restrepor
 *              Luis Fernando Londoño
 * @Fecha       Marzo 3 de 2019
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class ServerQNIO extends Thread {
    private final int ePort;
    private Selector cSelector;
    
    // Clave de identificación para mapa de hilos
    private final ConcurrentMap<Integer, SelectionKey> keysID; 
    private final ConcurrentMap<SocketChannel,List<byte[]>> mapaDataSalida;
    
    //Mensajes de entrada ser capturados por hilo de mensajería
    ConcurrentLinkedQueue<String> msgEntrante; 

    public ServerQNIO(int port) {
        this.ePort = port;
        mapaDataSalida = new ConcurrentHashMap<SocketChannel, List<byte[]>>();
        keysID = new ConcurrentHashMap<Integer, SelectionKey>();
    }

    public void start_server() throws IOException {
        // Crear el Selector y los Canales
        this.cSelector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        // Asociación a los puertos
        InetSocketAddress listenAddr = new InetSocketAddress((InetAddress)null, this.ePort);
        serverChannel.socket().bind(listenAddr);
        serverChannel.register(this.cSelector, SelectionKey.OP_ACCEPT);

        // Esto lo podemos colocar en el log nuestro
        log("Servidor esta Activo Ctrl-C para pararlo");

        // Procesamiento del Servidor
        while (true) {
            // Espera que ocurra un evento
            this.cSelector.select();

            // Identifica el evento ocurrido
            Iterator keys;
            keys = this.cSelector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();

                // Evitar repetición de teclas
                keys.remove();

                if (! key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    this.accept(key);
                }
                else if (key.isReadable()) {
                    this.read(key);
                }
                else if (key.isWritable()) {
                    this.write(key);
                }
                else if(key.isConnectable()) {
                    this.connect(key);
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        send_message(key, "Welcome."); //DEBUG

        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        log("Connected to: " + remoteAddr);

        // Registrar canal con selector para IO adicional.
        mapaDataSalida.put(channel, new ArrayList<byte[]>());
        channel.register(this.cSelector, SelectionKey.OP_READ);

        //Registrar canal con selector para IO adicional.
        SelectionKey put = keysID.put(0, key);
    }

    //Se verifica, se prueba
    public void init_connect(String addr, int port){
        try {
            SocketChannel channel = createSocketChannel(addr, port);
            channel.register(this.cSelector, channel.validOps());
        }
        catch (IOException e) {
            //Darle un manejo a esta excepción
        }
    }

    //Se verifica se prueba
    private void connect(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            channel.finishConnect(); //try to finish connection - if 'false' is returned keep 'OP_CONNECT' registered
            //store key in 'keys' to be accessable by ID from messenger thread //TODO first get ID
            SelectionKey put;
            put = keysID.put(0, key);
        }
        catch (IOException e0) {
            try {
                //Se puede adicionar manejo a la excepción
                channel.close();
            }
            catch (IOException e1) {
                //Adicionar manejo a la excepción
            }
        }

    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int numRead = -1;
        try {
            numRead = channel.read(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (numRead == -1) {
            this.mapaDataSalida.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            log("Connection closed by client: " + remoteAddr); //TODO handle
            channel.close();
            return;
        }

        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);
        msgEntrante.add(new String(data, "utf-8"));
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.mapaDataSalida.get(channel);
        Iterator<byte[]> items = pendingData.iterator();
        while (items.hasNext()) {
            byte[] item = items.next();
            items.remove();
            //TODO is this correct? -> re-doing write in loop with same buffer object
            ByteBuffer buffer = ByteBuffer.wrap(item);
            int bytes_to_write = buffer.capacity();
            while (bytes_to_write > 0) {
                bytes_to_write -= channel.write(buffer);
            }
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    public void queue_data(SelectionKey key, byte[] data) {
        SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.mapaDataSalida.get(channel);
        key.interestOps(SelectionKey.OP_WRITE);

        pendingData.add(data);
    }

    public void send_message(int id, String msg) {
        SelectionKey key;
        key = keysID.get(id);
        if (key != null)
            send_message(key, msg);
        //else
            //Implementar que podemos hacer aqui
    }

    public void send_message(SelectionKey key, String msg) {
        try {
            queue_data(key, msg.getBytes("utf-8"));
        }
        catch (UnsupportedEncodingException ex) {
            //Cuando hay una excepción se debe manejar
        }
    }

    public String get_message() {
        return msgEntrante.poll();
    }

    private static void log(String s) {
        System.out.println(s);
    }

    @Override
    public void run() {
        try {
            start_server();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
            //Darle manejo a esta excepción
        }
    }    


    // Se crea un canal de socket no bloqueante para el nombre de host y el puerto especificados.
    // se llama a connect() en el nuevo canal antes de que se devuelva.
    public static SocketChannel createSocketChannel(String hostName, int port) throws IOException {
        // Crear Canal no bloqueante
        SocketChannel sChannel = SocketChannel.open();
        sChannel.configureBlocking(false);

        // Se envía la conección al Servidor; Método no Bloqueante
        sChannel.connect(new InetSocketAddress(hostName, port));
        return sChannel;
    }    
    
}
