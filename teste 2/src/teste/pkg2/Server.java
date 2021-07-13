/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucia
 */
public class Server extends Thread{
    private ServerSocket server;
    private ObjectOutputStream saida;
    private Socket cliente;
    
    public Server(int port) throws IOException{
        server = new ServerSocket(port);
    }
    
    @Override
    public void run(){
        try {
            cliente = server.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
            saida = new ObjectOutputStream(cliente.getOutputStream());
            saida.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMsg(String msg) throws IOException{
        if(cliente.isConnected()){
            saida.flush();
            saida.writeObject(msg);
        }
        else
        {
            System.out.println("nao conectado");
        }
    }
    
    public void closeClient() throws IOException{
        cliente.close();
        saida.close();
    }
}
