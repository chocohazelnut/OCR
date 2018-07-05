package ocr_3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Binarize {
    public static void binarizeImg()
    {
        try {
            String File = "C:/Users/Hazel Cavite/Documents/NetBeansProjects/"
                    + "OCR_3/img/CamCap/test.jpg";
            File input = new File(File);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = (Graphics2D)result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            
            Color black = new Color(0,0,0);
            Color white = new Color(255,255,255);
            for (int i = result.getHeight() - 1; i > 0 ; i--) {
                for (int j = result.getWidth() - 1; j > 0 ; j--) {
                    Color c = new Color(result.getRGB(j, i));
                    if(c.getBlue() < 80 && c.getRed() < 80 && c.getGreen() < 80){
                        result.setRGB(j, i, black.getRGB());
                    }else{
                        result.setRGB(j, i, white.getRGB());
                    }
                }
            }
            String File2 = "C:/Users/Hazel Cavite/Documents/NetBeansProjects/"
                    + "OCR_3/img/Binarize/test.jpg";
            File output = new File(File2);
            ImageIO.write(result, "jpg", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
