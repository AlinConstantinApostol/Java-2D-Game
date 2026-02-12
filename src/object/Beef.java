package object;

import main.GamePanel;
import entity.Entity;

public class Beef extends Entity{
    public Beef(GamePanel gp){
        super(gp);
        name = "Beef";
        down1 = setup("/objects/beef", gp.tileSize, gp.tileSize);
    }
    @Override
    public void getImage(){
    }

}