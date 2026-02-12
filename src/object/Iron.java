package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Iron extends Projectile{ // subclasa la Projectile, si care mosteneste Entity

    GamePanel gp;
    public Iron(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Iron";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("/projectile/fier_de_calcat", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/fier_de_calcat", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/fier_de_calcat", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/fier_de_calcat", gp.tileSize, gp.tileSize);
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
