package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EntryDoor3 extends Entity{
    public EntryDoor3(GamePanel gp) {
        super(gp);
        name = "EntryDoor3";
        down1 = setup("/objects/entrydoor3", gp.tileSize, gp.tileSize);
        solidArea.setBounds(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
        collision = true;
    }
        @Override
        public void getImage(){
        }

    }