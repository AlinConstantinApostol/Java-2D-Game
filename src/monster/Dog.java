package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Dog extends Entity{
    GamePanel gp;
    public Dog(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Dog";
        speed = 2;
        maxLife = 6;
        life = maxLife;

        solidArea.x = 20;
        solidArea.y = 40;
        solidArea.width = 100;
        solidArea.height = 100;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    @Override
    public void getImage(){
        up1 = setup("/monster/dog_up_1", 600, 600);
        up2 = setup("/monster/dog_up_2", 600, 600);
        up3 = setup("/monster/dog_up_3", 600, 600);
        down1 = setup("/monster/dog_down_1", 600, 600);
        down2 = setup("/monster/dog_down_2", 600, 600);
        down3 = setup("/monster/dog_down_3", 600, 600);
        right1 = setup("/monster/dog_right_1", 600, 600);
        right2 = setup("/monster/dog_right_2", 600, 600);
        right3 = setup("/monster/dog_right_3", 600, 600);
        left1 = setup("/monster/dog_left_1", 600, 600);
        left2 = setup("/monster/dog_left_2", 600, 600);
        left3 = setup("/monster/dog_left_3", 600, 600);
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
