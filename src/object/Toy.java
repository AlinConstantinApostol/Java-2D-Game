package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Toy extends Projectile{ // subclasa la Projectile, si care mosteneste Entity

    GamePanel gp;
    public Toy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Toy";
        speed = 16;
        maxLife = 80;
        life = maxLife;
        attack = 1;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/projectile/toy_up", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/toy_down", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/toy_right", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/toy_left", gp.tileSize, gp.tileSize);
    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.manna >= useCost){
            haveResource = true;
        }
        return haveResource;
    }


    public void substractResource(Entity user){
        user.manna -= useCost;
    }


}