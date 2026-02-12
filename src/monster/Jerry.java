package monster;

import entity.Entity;
import main.GamePanel;
import object.Dynamite;

import java.util.Random;

public class Jerry extends Entity {
    GamePanel gp;
    boolean hitJerry = false;
    public Jerry(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Jerry";
        speed = 4;
        maxLife = 20;
        life = maxLife;
        projectile = new Dynamite(gp);

        solidArea.setBounds(25, 15, 42, 60);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/Jerry/Jerry_U1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/Jerry/Jerry_U2",gp.tileSize, gp.tileSize);
        up3 = setup("/monster/Jerry/Jerry_standU",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/Jerry/Jerry_D1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/Jerry/Jerry_D2",gp.tileSize, gp.tileSize);
        down3 = setup("/monster/Jerry/Jerry_standD",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/Jerry/Jerry_R1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/Jerry/Jerry_R2",gp.tileSize, gp.tileSize);
        right3 = setup("/monster/Jerry/Jerry_standR",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/Jerry/Jerry_L1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/Jerry/Jerry_L2",gp.tileSize, gp.tileSize);
        left3 = setup("/monster/Jerry/Jerry_standL",gp.tileSize, gp.tileSize);
    }

    public void setAction()
    {
        actionLockCounter ++;

        if(actionLockCounter == 60)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // genereaza numere de la 1 la 100

            if(i <= 15)
            {
                direction = "up";
            }
            if(i > 15 && i <= 30)
            {
                direction = "down";
            }
            if(i > 30 && i <= 45)
            {
                direction = "left";
            }
            if(i > 45 && i <= 60)
            {
                direction = "right";
            }
            actionLockCounter = 0;


        }
        if(gp.monster[gp.currentMap][1].invincible == true) {
            hitJerry = true;
        }
            if(hitJerry == true) {
                int i = new Random().nextInt(100) + 1;
                if (i > 40 && projectile.alive == false && shotAvailableCounter == 30) {
                    projectile.set(worldX, worldY, direction, true, this);
                    gp.projectileList.add(projectile);
                    shotAvailableCounter = 0;
                }
            }

    }
    public void damageReaction(){
        actionLockCounter = 0;
        isChasingPlayer = true;
    }


}
