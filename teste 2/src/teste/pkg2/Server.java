/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
        server = new ServerSocket(port);        //cria um socket com o IP do pc e a porta
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
    
    public void sendMsg(ArrayList<Integer> alg) throws IOException{
        if(cliente != null){
            if(cliente.isConnected()){
                saida.flush();
                saida.writeObject(alg);
            }
        }
    }
    
    public void closeClient() throws IOException{
        cliente.close();
        saida.close();
    }
}