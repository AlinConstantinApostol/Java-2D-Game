package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Soap2 extends Entity{
    public Soap2(GamePanel gp){
        super(gp);
        name = "Soap1";
        down1 = setup("/objects/soap2", gp.tileSize, gp.tileSize);

    }
    @Override
    public void getImage(){
    }

}