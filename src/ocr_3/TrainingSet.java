/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocr_3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.sql.*;


public class TrainingSet {
    public static void trainImg(String letter) throws SQLException
    {
        try {
            String File = "C:/Users/Hazel Cavite/Documents/NetBeansProjects/"
                    + "OCR_3/img/Crop/test.jpg";
            File input = new File(File);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage result = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_RGB);
            
            Graphics2D graphic = (Graphics2D)result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            
            String url = "jdbc:mysql://localhost/ocr";
            try (Connection conn = DriverManager.getConnection(url,"root","root")) {
                Statement st = conn.createStatement();
                
                float row1 = 0, row2 = 0, row3 = 0, black = 0;
                int j = (int) (result.getWidth()*0.10);
                for (int i = 0; i < result.getHeight(); i++) {
                    Color c = new Color(result.getRGB(j, i));
                    if(c.getBlue() < 80){
                        black++;
                    }
                }
                row1 = (black/result.getHeight())*100;
                black = 0;
                j = (int) (result.getWidth()*0.50);
                for (int i = 0; i < result.getHeight(); i++) {
                    Color c = new Color(result.getRGB(j, i));
                    if(c.getBlue() < 80)
                    {
                        black++;
                    }
                }
                row2 = (black/result.getHeight())*100;
                black = 0;
                j = (int) (result.getWidth()*0.90);
                for (int i = 0; i < result.getHeight(); i++) {
                    Color c = new Color(result.getRGB(j, i));
                    if(c.getBlue() < 80)
                    {
                        black++;
                    }
                }
                row3 = (black/result.getHeight())*100;     
                int row1i = (int)row1;
                int row2i = (int)row2;
                int row3i = (int)row3;
                st.executeUpdate("INSERT INTO `alphabet`(`letter`, `row1`,"
                        + "`row2`,`row3`) VALUES ('"+letter+"', '"+row1i+"',"
                        + "'"+row2i+"','"+row3i+"')");
            }
        }   catch (IOException ex) {
            Logger.getLogger(TrainingSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
