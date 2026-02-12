package object;

import main.GamePanel;
import entity.Entity;

public class Key extends Entity{
    public Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);

        solidArea.x = 24;
        solidArea.y = 20;
        solidArea.width = 48;
        solidArea.height = 48;
    }
    @Override
    public void getImage(){
    }
}