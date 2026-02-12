package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Banana1 extends Entity{
    public Banana1(GamePanel gp){
        super(gp);
        name = "Banana1";
        down1 = setup("/objects/banana1", gp.tileSize, gp.tileSize);
    }
    @Override
    public void getImage(){
    }
}