
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth() , inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            String fName = inImage.getFileName();
            String newName = "copy-" + fName;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
