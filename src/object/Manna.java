package object;

import entity.Entity;
import main.GamePanel;

public class Manna extends Entity{
    GamePanel gp;
    public Manna(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Manna";
        image = setup("/objects/Manna_blank", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/Manna_full", gp.tileSize, gp.tileSize);
    }
    @Override
    public void getImage(){
    }
}