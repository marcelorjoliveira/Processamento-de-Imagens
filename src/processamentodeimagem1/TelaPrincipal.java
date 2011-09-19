/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaPrincipal.java
 *
 * Created on 20/07/2011, 17:56:03
 */

package processamentodeimagem1;

import com.pearsoneduc.ip.gui.ImageView;
import com.pearsoneduc.ip.op.*;
import com.pearsoneduc.ip.op.LinearOp;
import com.pearsoneduc.ip.op.ThresholdOp;
import com.sun.media.jai.widget.DisplayJAI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author marcelo
 */
public class TelaPrincipal extends javax.swing.JFrame implements JanelaBrilhoEContrasteObserver {

     //To select the path of image file
    private File file;
    //To load the image file
    public PlanarImage srcImage = null;
    //To display the image file
    private DisplayJAI srcdj = null;
    private JScrollPane srcScrollPaneImage = null;
    //To choose file by browsing
    private JFileChooser FileChooser = new JFileChooser();
    //This flag will be set once image is loaded for the first time
    public boolean flag = false;
    private Histogram histograma;
    private HistogramView visualizacao;
    private HistogramInfoPane informacoesPainel;

    /** Creates new form TelaPrincipal */
    public TelaPrincipal() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        itemAbrir = new javax.swing.JMenuItem();
        itemSair = new javax.swing.JMenuItem();
        menuProcessamento = new javax.swing.JMenu();
        subMenuHistograma = new javax.swing.JMenu();
        histogramaEscaladeCinza = new javax.swing.JMenuItem();
        itemBrilhoeContraste = new javax.swing.JMenuItem();
        itemEqualizacao = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Processamento de Imagem 1");

        menuArquivo.setText("Arquivo");

        itemAbrir.setText("Abrir");
        itemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirActionPerformed(evt);
            }
        });
        menuArquivo.add(itemAbrir);

        itemSair.setText("Sair");
        itemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSairActionPerformed(evt);
            }
        });
        menuArquivo.add(itemSair);

        jMenuBar1.add(menuArquivo);

        menuProcessamento.setText("Processamento");

        subMenuHistograma.setText("Histograma");

        histogramaEscaladeCinza.setText("Escala de Cinza");
        histogramaEscaladeCinza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histogramaEscaladeCinzaActionPerformed(evt);
            }
        });
        subMenuHistograma.add(histogramaEscaladeCinza);

        menuProcessamento.add(subMenuHistograma);

        itemBrilhoeContraste.setText("Brilho e Contraste");
        itemBrilhoeContraste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBrilhoeContrasteActionPerformed(evt);
            }
        });
        menuProcessamento.add(itemBrilhoeContraste);

        itemEqualizacao.setText("Equalização");
        itemEqualizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEqualizacaoActionPerformed(evt);
            }
        });
        menuProcessamento.add(itemEqualizacao);

        jMenuBar1.add(menuProcessamento);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        int returnVal = FileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            //Initialise file object
            file = FileChooser.getSelectedFile();
            //Display the image
            displayImage(file);
        }
    }//GEN-LAST:event_itemAbrirActionPerformed

    private void itemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSairActionPerformed

    public void atualizar(PlanarImage srcImageBrilhoEContraste)
    {
        srcImage = srcImageBrilhoEContraste;
        srcdj.set(srcImage);
    }
    
    private void histogramaEscaladeCinzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histogramaEscaladeCinzaActionPerformed
        try {
            JanelaHistograma janela = new JanelaHistograma();
            histograma = new Histogram(srcImage.getAsBufferedImage());
            informacoesPainel = new HistogramInfoPane(histograma);
            
            JPanel painel = new JPanel(new BorderLayout()); 
            visualizacao = new HistogramView(histograma,informacoesPainel);
            painel.add(visualizacao, BorderLayout.CENTER);
            painel.add(informacoesPainel, BorderLayout.SOUTH);
            
            janela.add(painel);
            janela.setContentPane(painel);
            janela.pack();
            janela.setVisible(true);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_histogramaEscaladeCinzaActionPerformed

    private void itemBrilhoeContrasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBrilhoeContrasteActionPerformed
        JanelaBrilhoEContraste janela = new JanelaBrilhoEContraste(srcImage,this);
        janela.setVisible(true);
    }//GEN-LAST:event_itemBrilhoeContrasteActionPerformed

    private void itemEqualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEqualizacaoActionPerformed
        try {
            histograma = new Histogram(srcImage.getAsBufferedImage());
            EqualiseOp equalise = new EqualiseOp(histograma);
            BufferedImage imagemEqualisada = equalise.filter(srcImage.getAsBufferedImage(), null);
            srcImage = PlanarImage.wrapRenderedImage(imagemEqualisada);
            srcdj.set(srcImage);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_itemEqualizacaoActionPerformed

    public void displayImage (File path)
    {
        //Load the image which file name was passed as the first argument to the application
        srcImage = JAI.create("fileload", path.getPath());
        
        //When image is loaded for the first time
        if (flag == false)
        {
            // Get the JFrame's ContentPane.
            Container contentPane = this.getContentPane();
            contentPane.setLayout(new BorderLayout());
            //Create an instance of DisplayJAI.
            srcdj = new DisplayJAI(srcImage);
            //Add to the JFrame's ContentPane an instance of JScrollPane containing
            //the DisplayJAI instance.
            srcScrollPaneImage = new JScrollPane(srcdj);
            //scrScrollPaneImage
            contentPane.add(srcScrollPaneImage,BorderLayout.CENTER);
            this.setVisible(true);
            //O que é
            this.validate();
            flag = true;
        }
        else
        {
            srcdj.set(srcImage);
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
                new TelaPrincipal().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem histogramaEscaladeCinza;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemBrilhoeContraste;
    private javax.swing.JMenuItem itemEqualizacao;
    private javax.swing.JMenuItem itemSair;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenu menuProcessamento;
    private javax.swing.JMenu subMenuHistograma;
    // End of variables declaration//GEN-END:variables

}
