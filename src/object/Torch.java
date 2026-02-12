package object;

import main.GamePanel;
import entity.Entity;

public class Torch extends Entity{
    public Torch(GamePanel gp){
        super(gp);
        name = "Torch";
        down1 = setup("/objects/torch", gp.tileSize, gp.tileSize);
        solidArea.setBounds(12, 35, 75, 30);
        solidAreaDefaultX = 12;
        solidAreaDefaultY = 35;
    }
    @Override
    public void getImage(){
    }
}