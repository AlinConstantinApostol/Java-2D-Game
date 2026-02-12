package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EntryDoor2 extends Entity{
    public EntryDoor2(GamePanel gp){
        super(gp);
        name = "EntryDoor2";
        down1 = setup("/objects/entrydoor2", gp.tileSize, gp.tileSize);
        solidArea.setBounds(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
        collision = true;
    }
    @Override
    public void getImage(){
    }

}