package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Banana2 extends Entity{
    public Banana2(GamePanel gp){
        super(gp);
        name = "Banana2";
        down1 = setup("/objects/banana2", gp.tileSize, gp.tileSize);
    }
    @Override
    public void getImage(){
    }

}
