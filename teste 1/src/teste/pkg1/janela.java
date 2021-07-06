/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg1;

import javax.swing.JFrame;

/**
 *
 * @author lucia
 */
public class janela extends JFrame{
    
    public janela(){
        setTitle("Nome");
        setSize(1000, 500);
        setLocation(150, 50);
        setResizable(false);
    }
    public static void main(String args[]) {
        janela p = new janela();
        p.setVisible(true);
    }
}