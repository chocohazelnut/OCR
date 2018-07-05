package ocr_3;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Crop {
    public static int cropImg()
    {
        int retval = 1;
        try {
            String File = "C:/Users/Hazel Cavite/Documents/NetBeansProjects/"
                    + "OCR_3/img/Skew/test.jpg";
            File input = new File(File);
            BufferedImage image = ImageIO.read(input);
            
            int width = image.getWidth();
            int height = image.getHeight();
            
            BufferedImage result = new BufferedImage(
                    width, height,
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = (Graphics2D)result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            
            /* GETTING THE LEFT, BOTTOM, TOP and RIGHT POINTS */
            
            //left coordinates
            int x, y, flag;
            x = y = flag = 0;
            
            for(x = 0; x < width && flag == 0; x++){
                for(y = 0; y < height && flag == 0; y++){
                    Color c = new Color(result.getRGB(x, y));
                    int R = c.getRed();
                    int G = c.getGreen();
                    int B = c.getBlue();
                    
                    if(R == 0 && G == 0 && B == 0){
                        flag = 1;
                    }
                }
            }
            
            int left, right, bottom, top;
            left = right = bottom = top = 0;
            
            left = x;
            
            //bottom coordinates
            int x2, y2;
            x2 = y2 = flag = 0;
            
            for(y2 = height-1; y2 >= 0 && flag == 0; y2--){
                for(x2 = width-1; x2 >= 0 && flag == 0; x2--){
                    Color c = new Color(result.getRGB(x2, y2));
                    int R = c.getRed();
                    int G = c.getGreen();
                    int B = c.getBlue();
                    
                    if(R == 0 && G == 0 && B == 0){
                        flag = 1;
                    }
                }
            }
            
            bottom = y2;
            
            //right coordinates
            int x3, y3;
            x3 = y3 = flag = 0;
            for(x3 = width-1; x3 >= 0 && flag == 0; x3--){
                for(y3 = height-1; y3 >= 0 && flag == 0; y3--){
                    Color c = new Color(result.getRGB(x3, y3));
                    int R = c.getRed();
                    int G = c.getGreen();
                    int B = c.getBlue();
                    
                    if(R == 0 && G == 0 && B == 0){
                        flag = 1;
                    }
                }
            }
            
            right = x3;
            
            //top coordinates
            int x4, y4;
            x4 = y4 = flag = 0;
            
            for(y4 = 0; y4 < height && flag == 0; y4++){
                for(x4 = 0; x4 < width && flag == 0; x4++){
                    Color c = new Color(result.getRGB(x4, y4));
                    int R = c.getRed();
                    int G = c.getGreen();
                    int B = c.getBlue();
                    
                    if(R == 0 && G == 0 && B == 0){
                        flag = 1;
                    }
                }
            }
            
            top = y4;
            
            BufferedImage croppedImage = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            croppedImage = result.getSubimage(left, top, (right - left) , (bottom - top));

            String File2 = "C:/Users/Hazel Cavite/Documents/NetBeansProjects/"
                    + "OCR_3/img/Crop/test.jpg";
            File output = new File(File2);
            ImageIO.write(croppedImage, "jpg", output);
            
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return retval;
    }
}
