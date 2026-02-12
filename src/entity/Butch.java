package entity;

import main.GamePanel;
import java.util.Random;

public class Butch extends Entity{
    public Butch(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        solidArea.x = 20;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 60;
        solidArea.height = 80;

        // AICI SE SCHIMBA ZONA SOLIDA IN CAZ CA SE DORESTE, CUM E LA TOM
        getImage();
        setDialogue();
    }
        public void getImage () {

            up1 = setup("/npc/Butch/Butch_U1", gp.tileSize, gp.tileSize);
            up2 = setup("/npc/Butch/Butch_U2", gp.tileSize, gp.tileSize);
            up3 = setup("/npc/Butch/Butch_U3", gp.tileSize, gp.tileSize);
            up4 = setup("/npc/Butch/Butch_U4", gp.tileSize, gp.tileSize);
            up5 = setup("/npc/Butch/Butch_standU", gp.tileSize, gp.tileSize);
            down1 = setup("/npc/Butch/Butch_D1", gp.tileSize, gp.tileSize);
            down2 = setup("/npc/Butch/Butch_D2", gp.tileSize, gp.tileSize);
            down3 = setup("/npc/Butch/Butch_D3", gp.tileSize, gp.tileSize);
            down4 = setup("/npc/Butch/Butch_D4", gp.tileSize, gp.tileSize);
            down5 = setup("/npc/Butch/Butch_standD", gp.tileSize, gp.tileSize);
            right1 = setup("/npc/Butch/Butch_R1", gp.tileSize, gp.tileSize);
            right2 = setup("/npc/Butch/Butch_R2", gp.tileSize, gp.tileSize);
            right3 = setup("/npc/Butch/Butch_R3", gp.tileSize, gp.tileSize);
            right4 = setup("/npc/Butch/Butch_R4", gp.tileSize, gp.tileSize);
            right5 = setup("/npc/Butch/Butch_standR", gp.tileSize, gp.tileSize);
            left1 = setup("/npc/Butch/Butch_L1", gp.tileSize, gp.tileSize);
            left2 = setup("/npc/Butch/Butch_L2", gp.tileSize, gp.tileSize);
            left3 = setup("/npc/Butch/Butch_L3", gp.tileSize, gp.tileSize);
            left4 = setup("/npc/Butch/Butch_L4", gp.tileSize, gp.tileSize);
            left5 = setup("/npc/Butch/Butch_standL", gp.tileSize, gp.tileSize);

    }
    public void setDialogue()
    {
        dialogues[0] = "Hello, neighbor!";
        dialogues[1] = "So, you've decided to go back to get rid of \\nMammy Two Shoes from Jerry";
        dialogues[2] = "It won't be easy, here's a bottle of milk to give you \\nenergy, the road is long and arduous";
        dialogues[3] = "Here's a basket of dirty laundry thrown away by\\nMammy Two Shoes that she no longer uses." ;
        dialogues[4] = "Among them you'll also find your old toys.\\nUse them! Throwing them at your opponents will make them go away!";
        dialogues[5] = "Use the F key to throw toys!";
        dialogues[6] = "May you have all the luck in the world!";

    }
    public void setAction() {

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

    }
    public void speak() {
        // Nu vorbi daca deja ai vorbit
        if (gp.player.hasTalkedToButch) {
            gp.ui.currentDialogue = "We've already talked, neighbor!";
            return;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];

        if (dialogueIndex == 5) {
            Player.hasToy = true;
            Player.activeWeapon = 1;
            gp.player.db.updateToy(true);
        }

        if (dialogueIndex < 6) {
            dialogueIndex++;
        } else {
            gp.player.hasTalkedToButch = true;
            gp.player.db.updateHasTalkedToButch(true); // Marchez in DB
        }

        super.speak();
    }
}



