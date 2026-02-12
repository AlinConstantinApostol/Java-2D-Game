package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Ball extends Projectile{ // subclasa la Projectile, si care mosteneste Entity

    GamePanel gp;
    public Ball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Ball";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/projectile/ball1", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/ball1", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/ball2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/ball2", gp.tileSize, gp.tileSize);
    }


}