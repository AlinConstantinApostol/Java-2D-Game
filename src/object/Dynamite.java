package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Dynamite extends Projectile{ // subclasa la Projectile, si care mosteneste Entity

    GamePanel gp;
    public Dynamite(GamePanel gp) {
        super(gp);
        this.gp = gp;
        projectileType = 2;
        name = "Dynamite";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/dinamita_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/dinamita_up2", gp.tileSize, gp.tileSize);
        up3 = setup("/projectile/dinamita_up3", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/dinamita_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/dinamita_down2", gp.tileSize, gp.tileSize);
        down3 = setup("/projectile/dinamita_down3", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/dinamita_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/dinamita_left2", gp.tileSize, gp.tileSize);
        left3 = setup("/projectile/dinamita_left3", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/dinamita_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/dinamita_right2", gp.tileSize, gp.tileSize);
        right3 = setup("/projectile/dinamita_right3", gp.tileSize, gp.tileSize);
    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.ammo >= useCost){
            haveResource = true;
        }
        return haveResource;
    }

    public void substractResource(Entity user){
        user.ammo -= useCost;
    }

}