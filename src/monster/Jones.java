package monster;

import entity.Entity;
import main.GamePanel;
import object.Iron;

import java.util.Random;

public class Jones extends Entity {
    GamePanel gp;
    public Jones(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Jones";
        speed = 3;
        maxLife = 6;
        life = maxLife;
        projectile = new Iron(gp);

        solidArea.x = 24;
        solidArea.y = 16;
        solidArea.width = 60;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/Jones/Jones_U1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/Jones/Jones_U2",gp.tileSize, gp.tileSize);
        up3 = setup("/monster/Jones/Jones_U3",gp.tileSize, gp.tileSize);
        up4 = setup("/monster/Jones/Jones_U4",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/Jones/Jones_D1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/Jones/Jones_D2",gp.tileSize, gp.tileSize);
        down3 = setup("/monster/Jones/Jones_D3",gp.tileSize, gp.tileSize);
        down4 = setup("/monster/Jones/Jones_D4",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/Jones/Jones_R1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/Jones/Jones_R2",gp.tileSize, gp.tileSize);
        right3 = setup("/monster/Jones/Jones_R3",gp.tileSize, gp.tileSize);
        right4 = setup("/monster/Jones/Jones_R4",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/Jones/Jones_L1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/Jones/Jones_L2",gp.tileSize, gp.tileSize);
        left3 = setup("/monster/Jones/Jones_L3",gp.tileSize, gp.tileSize);
        left4 = setup("/monster/Jones/Jones_L4",gp.tileSize, gp.tileSize);
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
        int i = new Random().nextInt(100) + 1;
        if(i > 40 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        isChasingPlayer = true;
    }


}
