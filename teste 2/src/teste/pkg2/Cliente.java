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
    
    public Cliente(String IP, int port){
        try {
            cliente = new Socket(IP,port);
            
            msg = new String();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "erro: " + ex, "janela de erro", JOptionPane.ERROR_MESSAGE);
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    
    @Override
    public void run(){
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
            while(cliente.isConnected()){
                try{
                    msg = (String)entrada.readObject();
                    //System.out.println("msg = " + msg);
                }
                catch(IOException | ClassNotFoundException ex){         //Evita que não ultrapasse o tamanho do arquivo.
                    
                }
            }
            entrada.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMsg(){
        return msg;
    }
}
