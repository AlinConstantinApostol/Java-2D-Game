package entity;

import main.GamePanel;
import main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Water4 extends Entity{
    GamePanel gp;

    public Water4(GamePanel gp) {
        super(gp);
        this.gp = gp;
        direction = "up";
        speed = 0;

        solidArea.x = 40;
        solidArea.y = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 80;
        solidArea.height = 80;

        setAction();
        getImage();
    }
    public void getImage(){
        up1 = setup("/tiles/wet_floor4", gp.tileSize, gp.tileSize);
        up2 = setup("/tiles/floor_bath", gp.tileSize, gp.tileSize);
    }

    @Override
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            actionLockCounter = 0;
        }
    }

    public void update(){
        spriteCounter++;
        if(spriteCounter > 25){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = up1;
        } else if (spriteNum == 2) {
            image = up2;
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (image != null) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
