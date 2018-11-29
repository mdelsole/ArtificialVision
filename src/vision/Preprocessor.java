package vision;

/*
Inputs:
    A picture or video
Outputs:
    Organized stream of data for simple cell layer 1 (image as a grayscale array)
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Preprocessor {

    //Take in an image path, produce a 2D array of the grayscale pixel values
    public double [][] convertImg(String inputImg){
        BufferedImage img = null;
        File f = null;

        //read image
        try{
            f = new File(inputImg);
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println("Couldn't find it");
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
        double [][] values = new double [width][height];

        //convert to grayscale
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = img.getRGB(x,y);

                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                //calculate average
                int avg = (r+g+b)/3;

                //replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                values[x][y] = p;
            }
        }
        return values;
    }
}