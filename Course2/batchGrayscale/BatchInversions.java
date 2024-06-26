
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class BatchInversions {
    public ImageResource makeNegative(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth() , inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int red = 255 - inPixel.getRed();
            int blue = 255 - inPixel.getBlue();
            int green = 255 - inPixel.getGreen();
            pixel.setRed(red);
            pixel.setGreen(blue);
            pixel.setBlue(green);
        }
        return outImage;
    }
    
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource negImage = makeNegative(inImage);
            String fName = inImage.getFileName();
            String newName = "inverted-" + fName;
            negImage.setFileName(newName);
            negImage.draw();
            negImage.save();
        }
    }
}
