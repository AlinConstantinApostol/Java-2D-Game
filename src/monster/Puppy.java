package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Puppy extends Entity {
    GamePanel gp;
    public Puppy(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Puppy";
        speed = 2;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 24;
        solidArea.y = 16;
        solidArea.width = 60;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/puppy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/puppy_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("/monster/puppy_up_3", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/puppy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/puppy_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("/monster/puppy_down_3", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/puppy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/puppy_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("/monster/puppy_right_3", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/puppy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/puppy_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("/monster/puppy_left_3", gp.tileSize, gp.tileSize);
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
//        int i = new Random().nextInt(100) + 1;
//        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
//            projectile.set(worldX, worldY, direction, true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        isChasingPlayer = true;
    }


}
