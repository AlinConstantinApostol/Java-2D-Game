package monster;

import entity.Entity;
import main.GamePanel;
import object.Ball;

import java.util.Random;

public class Fulger extends Entity {
    GamePanel gp;
    public Fulger(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Fulger";
        speed = 5;
        maxLife = 10;
        life = maxLife;
        projectile = new Ball(gp);

        solidArea.setBounds(24, 16, 60, 60);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/Fulger/Fulger_U1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/Fulger/Fulger_U2",gp.tileSize, gp.tileSize);
        up3 = setup("/monster/Fulger/Fulger_U3",gp.tileSize, gp.tileSize);
        up4 = setup("/monster/Fulger/Fulger_U4",gp.tileSize, gp.tileSize);
        up5 = setup("/monster/Fulger/Fulger_standU",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/Fulger/Fulger_D1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/Fulger/Fulger_D2",gp.tileSize, gp.tileSize);
        down3 = setup("/monster/Fulger/Fulger_D3",gp.tileSize, gp.tileSize);
        down4 = setup("/monster/Fulger/Fulger_D4",gp.tileSize, gp.tileSize);
        down5 = setup("/monster/Fulger/Fulger_standD",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/Fulger/Fulger_R1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/Fulger/Fulger_R2",gp.tileSize, gp.tileSize);
        right3 = setup("/monster/Fulger/Fulger_R3",gp.tileSize, gp.tileSize);
        right4 = setup("/monster/Fulger/Fulger_R4",gp.tileSize, gp.tileSize);
        right5 = setup("/monster/Fulger/Fulger_standR",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/Fulger/Fulger_L1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/Fulger/Fulger_L2",gp.tileSize, gp.tileSize);
        left3 = setup("/monster/Fulger/Fulger_L3",gp.tileSize, gp.tileSize);
        left4 = setup("/monster/Fulger/Fulger_L4",gp.tileSize, gp.tileSize);
        left5 = setup("/monster/Fulger/Fulger_standL",gp.tileSize, gp.tileSize);
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
        if(i > 40 && !projectile.alive){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        isChasingPlayer = true;
    }


}
