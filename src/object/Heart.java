package object;

import main.GamePanel;
import entity.Entity;

public class Heart extends Entity{
    public Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = setup("/objects/heart_full", gp.tileSize - 30, gp.tileSize - 30);
        image3 = setup("/objects/heart_blank", gp.tileSize - 30, gp.tileSize - 30);
    }
    @Override
    public void getImage(){
    }

}