/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg1;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author lucia
 */
public class teste_cliente {
    public static void main(String[] args) {
    try {
        Socket cliente = new Socket("paulo",12345);
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        Date data_atual = (Date)entrada.readObject();
        JOptionPane.showMessageDialog(null,"Data recebida do servidor:" + data_atual.toString());
        entrada.close();
        System.out.println("Conexão encerrada");
    }
    catch(Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
    }
}
