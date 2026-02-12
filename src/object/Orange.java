package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Orange extends Entity{
    public Orange(GamePanel gp){
        super(gp);
        name = "Orange";
        down1 = setup("/objects/orange", gp.tileSize, gp.tileSize);
    }
    @Override
    public void getImage(){
    }

}