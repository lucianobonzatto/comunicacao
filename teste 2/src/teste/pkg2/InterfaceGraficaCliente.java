/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.pkg2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author lucia
 */
public class InterfaceGraficaCliente extends javax.swing.JFrame{
    
    private Cliente cliente;
    Thread teste = new Thread(new Runnable() {
        public void run() {
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(InterfaceGraficaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(!cliente.getBin().isEmpty())
                {
                    ArrayList<Integer> alg = cliente.getBin();
                    ArrayList<Boolean> bin = algoritmoToBin(alg);
                    String cript = binarioToStr(bin);
                    String msg = descriptografia(cript);
                    
                    criptografiaTextField.setText(cript);
                    mensagemTextField.setText(msg);
                    binarioTextField.setText(arrayToStr(bin));
                    algoritmoTextField.setText(alg.toString());
                    
                    criarGrafico(alg);
                }
            }
        }
    });
    /**
     * Creates new form InterfaceGraficaCliente
     */
    public InterfaceGraficaCliente()
    {
        initComponents();
    }
    
    public String descriptografia(String cript)
    {
        String output = "";

        for(int i = 0; i < cript.length(); i++){
            int letraAux = (int)cript.charAt(i) - cript.length()-90;
            if(letraAux >= 0)
                output += (char)(((int)cript.charAt(i) - cript.length()-90)%255);
            else{
                output += (char)((255 + letraAux)%255);
            }

        }
        return output;
    }
    
    private ArrayList<Boolean> algoritmoToBin(ArrayList<Integer> alg)
    {
        ArrayList<Boolean> bin = new ArrayList<>();
        
        for(int i=0; i<alg.size(); i++)
        {
            if(alg.get(i) > 0)
                bin.add(true);
            else
                bin.add(false);
        }
        
        return bin;
    }
    
    private String binarioToStr(ArrayList<Boolean> bin)
    {
        String output = "";
        int letra=0;
        int mult=128;
        int i;
        for(i=0; i<bin.size(); i++)
        {
            if (i%8 ==0){
                mult = 128;
                if(i!=0){
                    output += (char)letra;
                }
                if(bin.get(i)){
                   letra = mult;
                }
                else{
                    letra = 0;
                }
            }
            else{
                mult /= 2;
                if(bin.get(i)){
                    letra += mult;
                }
            }
        }
        if(!bin.isEmpty()){
            output += (char)letra;
        }
        return output;
    }
    
    private String arrayToStr(ArrayList<Boolean> bin)
    {
        String output = "";
        for(int i=0; i<bin.size();i++)
        {
            if(bin.get(i)){
                output += '1';
            }
            else{
                output += '0';
            }
        }
        return output;
    }
    
    public InterfaceGraficaCliente(String IP, int port) throws IOException
    {
        System.out.println("IP = " + IP);
        System.out.println("port = " + port);
        cliente = new Cliente(IP, port);
        
        initComponents();
        cliente.start();
        teste.start();
        
        mensagemTextField.setEditable(false);
        criptografiaTextField.setEditable(false);
        binarioTextField.setEditable(false);
        algoritmoTextField.setEditable(false);
        
    }

    private XYDataset createDataset(ArrayList<Integer> alg)
    {
        
        XYSeries s1 = new XYSeries("S1");
        
        for(int i = 0; i < alg.size(); i++)
        {
            s1.add(i, alg.get(i));
            s1.add(i+1, alg.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        dataset.addSeries(s1);
        return dataset;
    }
    
    private void criarGrafico(ArrayList<Integer> alg)
    {
        XYDataset cds = createDataset(alg);
        String titulo = "Código De Linha";
        String eixoy = "Tensão (V)";
        String txt_legenda = "Tempo";
        boolean legenda = false;
        boolean tooltips = false;
        boolean urls = true;
        JFreeChart graf = ChartFactory.createXYLineChart(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);
        
        ChartPanel myChartPanel = new ChartPanel(graf, true);
        myChartPanel.setSize(jPanelGrafico.getWidth(), jPanelGrafico.getHeight());
        myChartPanel.setVisible(true);
       // myChartPanel.setAutoscrolls(true);
        jPanelGrafico.removeAll();
        jPanelGrafico.add(myChartPanel);
        jPanelGrafico.revalidate();
        jPanelGrafico.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelGrafico = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mensagemTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        criptografiaTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        binarioTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        algoritmoTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelGraficoLayout = new javax.swing.GroupLayout(jPanelGrafico);
        jPanelGrafico.setLayout(jPanelGraficoLayout);
        jPanelGraficoLayout.setHorizontalGroup(
            jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelGraficoLayout.setVerticalGroup(
            jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(570, 120));

        jLabel1.setText("msg");

        jLabel2.setText("cript");

        jLabel4.setText("bin");

        jLabel3.setText("alg");

        algoritmoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algoritmoTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(criptografiaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addComponent(mensagemTextField)
                    .addComponent(binarioTextField)
                    .addComponent(algoritmoTextField))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mensagemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(criptografiaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(binarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(algoritmoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void algoritmoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algoritmoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_algoritmoTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField algoritmoTextField;
    private javax.swing.JTextField binarioTextField;
    private javax.swing.JTextField criptografiaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JTextField mensagemTextField;
    // End of variables declaration//GEN-END:variables
}
