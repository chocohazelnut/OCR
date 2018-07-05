
package ocr_3;

import com.sun.javafx.binding.Logging;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Guess {
        
    public static void guessImg() throws SQLException
    {
        char guess = 0;
        int bestNdx = 10000;
        try {
            int c;
            int[] arr = {1,2,3};
            int[] conf = new int[26];
            int count = 65 + 26;
            for(c = 65; c < count; c++){
                String url = "jdbc:mysql://localhost/ocr";
                try (Connection conn = DriverManager.getConnection(url,"root","root")){
                    String query = "SELECT AVG(`row1`), AVG(`row2`), "
                            + "AVG(`row3`) FROM `alphabet` WHERE "
                            + "`letter` = '"+(char)c+"' ";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while(rs.next()){
                        arr[0] = rs.getInt(1);
                        arr[1] = rs.getInt(2);
                        arr[2] = rs.getInt(3);
                    }
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
            
                        float row1 = 0, row2 = 0, row3 = 0, black = 0;
                        int j = (int) (result.getWidth()*0.10);
                        for (int i = 0; i < result.getHeight(); i++) {
                            Color color = new Color(result.getRGB(j, i));
                            if(color.getBlue() < 80){
                                black++;
                            }
                        }
                        row1 = (black/result.getHeight())*100;
                        black = 0;
                        j = (int) (result.getWidth()*0.50);
                        for (int i = 0; i < result.getHeight(); i++) {
                            Color color = new Color(result.getRGB(j, i));
                            if(color.getBlue() < 80)
                            {
                                black++;
                            }
                        }
                        row2 = (black/result.getHeight())*100;
                        black = 0;
                        j = (int) (result.getWidth()*0.90);
                        for (int i = 0; i < result.getHeight(); i++) {
                            Color color = new Color(result.getRGB(j, i));
                            if(color.getBlue() < 80)
                            {
                                black++;
                            }
                        }
                        row3 = (black/result.getHeight())*100;     
                        int row1i = (int)row1;
                        int row2i = (int)row2;
                        int row3i = (int)row3;
                        conf[c - 65] = Math.abs(row1i - arr[0]) + Math.abs(row2i - arr[1]) + Math.abs(row3i - arr[2]);
                    int best = 10000;
                    int bestNdx2 = 66;
                    for(int i = 0; i < 26; i++){
                        if(conf[i] < best){
                            bestNdx2 = bestNdx;
                            best = conf[i];
                            bestNdx = i + 65;
                        }
                    }
                    
                }
                
            }
            guess = (char)bestNdx;
            System.out.println("GUESS - " +guess);
        }   catch (IOException ex) {
            Logger.getLogger(TrainingSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
