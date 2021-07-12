/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lucia
 */
public class Cliente extends Thread{
    private Socket cliente;
    private String msg;
    private boolean connected;
    
    public Cliente(String IP, int port){
        try {
            cliente = new Socket(IP,port);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "erro: " + ex, "janela de erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        msg = new String();
        connected = cliente.isConnected();
    }
    
    @Override
    public void run(){
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
            while(connected){
                msg = (String)entrada.readObject();
            }
            entrada.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMsg(){
        return msg;
    }
}
