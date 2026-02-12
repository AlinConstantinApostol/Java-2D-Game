package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Pioneze extends Entity{
    public Pioneze(GamePanel gp){
        super(gp);
        name = "Pioneze";
        down1 = setup("/objects/pioneze", gp.tileSize, gp.tileSize);

    }
    @Override
    public void getImage(){
    }

}