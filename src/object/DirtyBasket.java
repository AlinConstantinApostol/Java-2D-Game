package object;

import main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DirtyBasket extends Entity{
    public DirtyBasket(GamePanel gp){
        super(gp);
        name = "DirtyBasket";
        down1 = setup("/objects/dirty_clothes", gp.tileSize, gp.tileSize);
        solidArea.setBounds(12, 12, 75, 75);
        solidAreaDefaultX = 12;
        solidAreaDefaultY = 12;
        collision = true;
    }
    @Override
    public void getImage(){
    }

}
