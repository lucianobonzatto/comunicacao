/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class teste_servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket server = new ServerSocket(2620);
            if(!server.isBound()){
                server.bind(new InetSocketAddress("25.107.71.249", 0));
            }
            
            while(true) {
            // o método accept() bloqueia a execução até que
            // o servidor receba um pedido de conexão
                Socket cliente = server.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                saida.flush();
                String msg = new String();
                msg = "vai se fude joao";
                saida.writeObject(msg);
                saida.close();
                cliente.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(teste_servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
