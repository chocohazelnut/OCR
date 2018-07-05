
package ocr_3;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class WebcamUI extends javax.swing.JFrame {
    
    Webcam webcam;
    public static String letter_train;
    public WebcamUI() {
        initComponents();
        setLocationRelativeTo(null);
        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(320, 240));
        webcam.setViewSize(new Dimension(640, 480));
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
        new VideoFeedTaker().start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageHolder = new javax.swing.JLabel();
        trainButton = new javax.swing.JButton();
        recogButton = new javax.swing.JButton();
        letterChoice = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        trainButton.setText("TRAIN");
        trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });

        recogButton.setText("RECOGNIZE");
        recogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recogButtonActionPerformed(evt);
            }
        });

        letterChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
        letterChoice.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        letterChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                letterChoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(letterChoice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(recogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(trainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(letterChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(recogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainButtonActionPerformed
        try {
            String File = "C:/Users/Hazel Cavite/Documents/"
                    + "NetBeansProjects/OCR_3/img/CamCap/test.jpg";
            ImageIO.write(webcam.getImage(),"JPG", new File(File));
            Binarize.binarizeImg();
            Skew.skewImg();
            Crop.cropImg();
            letter_train = (String) letterChoice.getSelectedItem();
            TrainingSet.trainImg(letter_train);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(WebcamUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_trainButtonActionPerformed

    private void recogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recogButtonActionPerformed
        try {
            String File = "C:/Users/Hazel Cavite/Documents/"
                    + "NetBeansProjects/OCR_3/img/CamCap/test.jpg";
            ImageIO.write(webcam.getImage(),"JPG", new File(File));
            Binarize.binarizeImg();
            Skew.skewImg();
            Crop.cropImg();
            Guess.guessImg();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(WebcamUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_recogButtonActionPerformed

    private void letterChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_letterChoiceActionPerformed
       
    }//GEN-LAST:event_letterChoiceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WebcamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WebcamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WebcamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebcamUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WebcamUI().setVisible(true);
            }
        });
        
    }
    
    class VideoFeedTaker extends Thread{
        @Override
        public void run(){
            while(true){
                try {
                    Image image = webcam.getImage();
                    imageHolder.setIcon(new ImageIcon(image));
                    Thread.sleep(100);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(WebcamUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageHolder;
    private javax.swing.JComboBox<String> letterChoice;
    private javax.swing.JButton recogButton;
    private javax.swing.JButton trainButton;
    // End of variables declaration//GEN-END:variables
    
}
