package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Apple extends Entity{
    public Apple(GamePanel gp){
        super(gp);
        name = "Apple";
        down1 = setup("/objects/apple", gp.tileSize, gp.tileSize);


    }
    @Override
    public void getImage(){
    }

}