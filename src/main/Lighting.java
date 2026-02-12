package main;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.MultipleGradientPaint.CycleMethod.NO_CYCLE;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    public int width, height;
    public int screenX, screenY, centerX, centerY, circleSize;
    public Color[] color;
    public float[] fraction;

    public Lighting(GamePanel gp, int circleSize) {
        this.gp = gp;
        this.circleSize = circleSize;
        width = gp.tileSize * 17;
        height = gp.tileSize * 40;

        // Create a buffered image
        darknessFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        // Get the center x and y of the light circle
        centerX = gp.player.screenX + (gp.tileSize)/2;
        centerY = gp.player.screenY + (gp.tileSize)/2;

        // Create a gradation effect
        color = new Color[12];
        fraction = new float[12];


        color[0] = new Color(0,0,0,0.1f);
        color[1] = new Color(0,0,0,0.4f);
        color[2] = new Color(0,0,0,0.52f);
        color[3] = new Color(0,0,0,0.61f);
        color[4] = new Color(0,0,0,0.69f);
        color[5] = new Color(0,0,0,0.76f);
        color[6] = new Color(0,0,0,0.80f);
        color[7] = new Color(0,0,0,0.83f);
        color[8] = new Color(0,0,0,0.86f);
        color[9] = new Color(0,0,0,0.88f);
        color[10] = new Color(0,0,0,0.91f);
        color[11] = new Color(0,0,0,0.94f);

        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        int worldX = gp.tileSize * 38, worldY = gp.tileSize * 20; // coordonatele entitatii pe harta
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Create a gradation paint settings
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);
        LinearGradientPaint gPaint2 = new LinearGradientPaint(
                screenX, screenY,
                screenX, screenY + 200,
                new float[]{0f, 0.5f, 1f},
                new Color[]{new Color(0,0,0f), new Color(0, 0, 0, 0.2f), new Color(0, 0, 0, 0f)}
        );
        LinearGradientPaint gPaint3 = new LinearGradientPaint(screenX, screenY, screenX+200, screenY, fraction, color);

        g2.setColor(new Color(0,0, 0, 0.95f));
        if (gp.player.hasTorch) g2.setPaint(gPaint);
        g2.fillRect(screenX+100, screenY+100, width, height);

        g2.setColor(new Color(0,0, 0, 0.6f));
        g2.fillRect(screenX, screenY, width, gp.tileSize);
        g2.fillRect(screenX, screenY+100, gp.tileSize, height);
        g2.setColor(new Color(0,0, 0, 0.3f));
        g2.fillRect(screenX-100, screenY-100, width, gp.tileSize);
        g2.fillRect(screenX-100, screenY, gp.tileSize, height);

        g2.drawImage(darknessFilter, screenX, screenY, null);
    }
}