package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); // precum o panza alba
        Graphics2D g2 = scaledImage.createGraphics(); // creaza un Graphics2D, care poate fi folosit pentru a desena
        // in BufferedImage, va fi salvat in scaledImage
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

}